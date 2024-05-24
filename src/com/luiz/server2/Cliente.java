package com.luiz.server2;

import java.io.*;
import java.util.*;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

public class Cliente implements AutoCloseable {
    private String nomeCliente;
    private ArrayBlockingQueue<Item> itens;
    private List<Item> itensConsumidos = new ArrayList<>();
    private boolean parar;
    private Socket client;
    private DataOutputStream saida;
    private ObjectInputStream entradaObj;
    private final String[] opcoes = new String[] { "Cadastrar mesa", "Listar itens", "Adicionar item", "Fechar conta", "Sair do programa" };

    public static void main(String[] args) {
        try (var client = new Cliente()) {
            client.start();
        } catch (Exception e) {
            System.out.println("Erro ao inicar cliente: " + e.getMessage());
        }
    }

    public void start() throws IOException {
        client = new Socket("localhost", Servidor.PORT);
        // rever a partir do minuto 1:17:00
        this.saida = new DataOutputStream(client.getOutputStream());
        this.entradaObj = new ObjectInputStream(client.getInputStream());
        messageLoop();
    }

    private void messageLoop() {
        try (var utils = new Utils();) {
            while (!parar) {
                utils.showArray(opcoes);
                int opt = utils.lerOption("Sua opção: ", 1, opcoes.length, "inválido");
                switch (opt) {
                    case 0 -> cadastrarMesa(utils, saida);
                    case 1 -> listarItens(entradaObj, saida);
                    case 2 -> adicionarItem(utils, entradaObj, saida);
                    case 3 -> fecharConta(entradaObj, saida);
                    case 4 -> close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cadastrarMesa(Utils utils, DataOutputStream saida) throws IOException {
        if (this.nomeCliente != null) {
            System.out.printf("Mesa de %s já cadastrada%n", this.nomeCliente);
            return;
        }
        saida.writeInt(0);
        var nome = utils.lerString("Digite seu nome: ", "Nome inválido");
        saida.writeUTF(nome);
    }

    // Não cadastre itens depois que já listou uma vez
    public void listarItens(ObjectInputStream entradaObj, DataOutputStream saida)
            throws IOException, ClassNotFoundException {
        saida.writeInt(1);
        this.itens = (ArrayBlockingQueue<Item>) entradaObj.readObject();
        if (this.itens.size() == 0) {
            System.out.println("Nenhum item cadastrado");
            return;
        }
        this.itens.stream()
                .forEach(i -> System.out.printf("Código: %d, %s %.2f Quantidade: %d %n", i.getCodigo(),
                        i.getNome(),
                        i.getPreco(), i.getQuantidade()));
        System.out.println();
    }

    public void adicionarItem(Utils utils, ObjectInputStream entradaObj, DataOutputStream saida)
            throws ClassNotFoundException, IOException {
        if (this.itens == null || this.itens.size() == 0) {
            System.out.println("Sem itens. Aperte 2 para listar itens");
            return;
        }

        var codItem = utils.lerOption("Digite o código do Item: ", 1, this.itens.size(), "Item inexistente") + 1;
        List<Item> itemSelecionado = this.itens.stream().filter(i -> i.getCodigo() == codItem).collect(Collectors.toList());
        Item item = itemSelecionado.get(0);
        var qtd = utils.lerOption("Digite a quantidade: ", 1, item.getQuantidade(), "Quantidade inválida") + 1;
        this.itensConsumidos.add(new Item(item.getNome(), item.getPreco(), qtd));
        saida.writeInt(2);
        saida.writeUTF(codItem + " " + qtd);
    }


    // terminar método fechar conta
    public void fecharConta(ObjectInputStream entradaObj, DataOutputStream saida)
            throws Exception {
        if (this.itens == null || this.itens.size() == 0) {
            System.out.println("Sem itens consumidos");
            return;
        }
        var totalConta = this.itensConsumidos
                .stream().map(i -> i.getPreco() * i.getQuantidade()).reduce(0d, (i, ii) -> i + ii);
        this.itensConsumidos.stream()
                .forEach(i -> System.out.printf("Código: %d, %s %.2f Quantidade disponível: %d %n", i.getCodigo(),
                        i.getNome(),
                        i.getPreco(), i.getQuantidade()));
        System.out.printf("%nTotal da conta foi de R$ %.2f%n", totalConta);

        saida.writeInt(3);
        // saida.writeUTF(this.nome);
        var tempoPermanencia = (long) entradaObj.readObject();
        System.out.printf("Você ficou no restaurante por %d minutos%n", tempoPermanencia);
        close();
    }

    @Override
    public void close() throws Exception {
        saida.writeInt(4);
        parar = true;
    }
}