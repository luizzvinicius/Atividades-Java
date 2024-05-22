package com.luiz.java_net;

import java.io.*;
import java.net.Socket;
import java.util.List;

// cliente em si

public class ProgCliente implements AutoCloseable {
    public Socket socket;
    public static boolean parar;
    public DataInputStream sockEntrada;
    public DataOutputStream sockSaida;
    public static List<Item> itens;
    public static String[] opcoes = new String[] { "Cadastrar mesa", "Listar itens", "Adicionar item", "Fechar conta",
            "Sair do programa" };
    public static int lastOptionIndex = opcoes.length - 1;

    public static void main(String[] args) {
        try (var utils = new Utils();
                var socket = new Socket("localhost", 8000);
                var sockObjEntrada = new ObjectInputStream(socket.getInputStream());
                var sockObjSaida = new ObjectOutputStream(socket.getOutputStream());
                var sockSaida = new DataOutputStream(socket.getOutputStream())) {
            while (!parar) {
                utils.showArray(opcoes);
                int opcao = utils.lerOption("Sua opção: ", 1, lastOptionIndex, "Opção inválida.");
                switch (opcao) {
                    case 0:
                        Mesa mesa = cadastrarMesa(utils);
                        sockObjSaida.writeObject(mesa);
                        break;
                    case 1:
                        sockSaida.writeInt(1);
                        itens = (List<Item>) sockObjEntrada.readObject();
                        listarItems(itens);
                        break;
                    case 2:
                        sockSaida.writeInt(1);
                        itens = (List<Item>) sockObjEntrada.readObject();
                        listarItems(itens);
                        break;
                    case 3:
                        var codItem = utils.lerOption("Digite o código do item: ", 1, itens.size() - 1, "Item inexistente");
                        System.out.println(codItem);
                        // continuar implementação
                        break;
                    case 4:
                        // informar ao servidor q tá saindo ?
                        System.out.println("Cliente finalizado!");
                        sair();
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Mesa cadastrarMesa(Utils utils) {
        var nome = utils.lerString("Digite seu nome: ", "Nome inválido");
        return new Mesa(nome);
    }

    public static void listarItems(List<Item> itens) {
        itens.stream()
                .forEach(i -> System.out.printf("%d- %s R$%.2f, quantidade: %.0f%n", i.getCodigo(), i.getNome(),
                        i.getPreco(), i.getQuantidade()));
    }

    public static void adicionarItem(List<Item> itens) {

    }

    public static void sair() {
        parar = true;
    }

    // public void run() {
    //     while (!parar) {
    //         utils.showArray(opcoes);
    //         int opcao = utils.lerOption("Sua opção: ", 1, lastOptionIndex, "Opção inválida.");
    //         switch (opcao) {
    //             case 0:
    //                 var mesa = cadastrarMesa(utils);
    //                 sendObjt.writeObject(mesa);
    //                 break;
    //             case 1:
    //                 saida.writeInt(1);
    //                 itens = (List<Item>) receiveObj.readObject();
    //                 listarItems(itens);
    //                 break;
    //             case 2:
    //                 saida.writeInt(1);
    //                 itens = (List<Item>) receiveObj.readObject();
    //                 listarItems(itens);
    //                 break;
    //             case 3:

    //                 break;
    //             case 4:
    //                 System.out.println("Cliente finalizado!");
    //                 sair();
    //                 break;
    //         }
    //     }

    // }

    @Override
    public void close() throws Exception {
        socket.close();
    }
}