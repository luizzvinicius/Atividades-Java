package com.luiz.server2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Cliente implements AutoCloseable {
    private Mesa mesa;
    private ArrayBlockingQueue<Item> itens = null;
    private boolean parar;
    private Socket client;
    private DataOutputStream saida;
    private ObjectOutputStream saidaObj;
    private ObjectInputStream entradaObj;
    private final String[] opcoes = new String[]{"Cadastrar mesa", "Listar itens", "Adicionar item", "Fechar conta", "Sair do programa"};

    public static void main(String[] args) {
        try (var client = new Cliente()) {
            client.start();
        } catch (Exception e) {
            System.out.printf("Erro ao inicar cliente: %s%n", e.getMessage());
        }
    }

    public void start() throws IOException {
        client = new Socket("localhost", Servidor.PORT);
        this.saida = new DataOutputStream(client.getOutputStream());
        this.entradaObj = new ObjectInputStream(client.getInputStream());
        this.saidaObj = new ObjectOutputStream(client.getOutputStream());
        messageLoop();
    }

    @SuppressWarnings("unchecked")
    private void messageLoop() {
        try (var utils = new Utils()) {
            var exec = Executors.newScheduledThreadPool(1);
            exec.scheduleAtFixedRate(() -> {
                if (mesa != null) {
                    try {
                        saida.writeInt(1);
                        this.itens = (ArrayBlockingQueue<Item>) entradaObj.readObject();
                    } catch (Exception e) {
                        System.out.println("Erro ao buscar Itens");
                    }
                }
                if (parar) {
                    exec.shutdown();
                }
            }, 0, 2, TimeUnit.SECONDS);
            while (!parar) {
                utils.showArray(opcoes);
                int opt = utils.lerOption("Sua opção: ", 1, opcoes.length, "inválido");
                switch (opt) {
                    case 0 -> cadastrarMesa(utils, saida, saidaObj);
                    case 1 -> listarItens();
                    case 2 -> adicionarItem(utils, saida);
                    case 3 -> fecharConta(entradaObj, saida);
                    case 4 -> close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cadastrarMesa(Utils utils, DataOutputStream saida, ObjectOutputStream saidaObj) throws IOException {
        if (this.mesa != null) {
            System.out.printf("Mesa de %s já cadastrada%n", this.mesa.getNomeCliente());
            return;
        }
        saida.writeInt(0);
        var nome = utils.lerString("Digite seu nome: ", "Nome inválido");
        this.mesa = new Mesa(nome);
        saidaObj.writeObject(this.mesa);
    }

    public void listarItens() {
        verificaMesa();
        verificaItens();
        this.itens.forEach(i -> System.out.printf("Código: %d, %s %.2f Quantidade: %d %n", i.getCodigo(), i.getNome(), i.getPreco(), i.getQuantidade()));
        System.out.println();
    }

    public void adicionarItem(Utils utils, DataOutputStream saida) throws IOException {
        verificaMesa();
        verificaItens();
        this.itens.forEach(i -> System.out.printf("Código: %d, %s %.2f Quantidade: %d %n", i.getCodigo(), i.getNome(), i.getPreco(), i.getQuantidade()));
        var codItem = utils.lerOption("\nDigite o código do Item: ", 1, this.itens.size(), "Item inexistente") + 1;
        Item itemSelecionado = this.itens.stream().filter(i -> i.getCodigo() == codItem).toList().getFirst();
        var qtd = utils.lerOption("Digite a quantidade: ", 1, itemSelecionado.getQuantidade(), "Quantidade inválida") + 1;
        this.mesa.adicionaItem(itemSelecionado.getNome(), itemSelecionado.getPreco(), qtd);
        saida.writeInt(2);
        saida.writeUTF(codItem + " " + qtd);
    }

    public void fecharConta(ObjectInputStream entradaObj, DataOutputStream saida) throws Exception {
        verificaMesa();
        verificaItens();
        var totalConta = this.mesa.totalConta();
        this.mesa.mostraItens();
        System.out.printf("%nTotal da conta foi de R$ %.2f%n", totalConta);

        saida.writeInt(3);
        saida.writeUTF(this.mesa.getNomeCliente());
        var tempoPermanencia = ChronoUnit.MINUTES.between(LocalTime.parse(this.mesa.getHorarioEntrada()), LocalTime.now());
        System.out.printf("Você ficou no restaurante por %d minutos%n", tempoPermanencia);
        close();
    }

    private void verificaMesa() {
        if (mesa == null) {
            System.out.println("Mesa não cadastrada ainda");
            return;
        }
    }

    private void verificaItens() {
        if (this.itens == null) {
            System.out.println("Sem itens");
            return;
        }
    }

    @Override
    public void close() throws IOException {
        saida.writeInt(4);
        this.entradaObj.close();
        this.saida.close();
        this.saidaObj.close();
        this.client.close();
        parar = true;
    }
}