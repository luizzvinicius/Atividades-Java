package com.luiz.server2;

import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.*;

public class Servidor {
    static boolean parar;
    static final Cozinha cozinha = new Cozinha("IFAL...");
    static ObjectInputStream entradaObj;
    static final String[] opcoes = new String[] { "Cadastrar item", "Listar mesas", "Encerrar cozinha" };
    static final String[] ClientOptions = new String[] { "Cadastrar Mesa", "Enviar itens", "Adicionar Item",
            "Fechar conta" };

    public static final int PORT = 8000;
    private ServerSocket server;
    private DataInputStream entrada;

    public static void main(String[] args) {
        try {
            var exec = Executors.newFixedThreadPool(5);
            var servidor = new Servidor();
            servidor.start(exec);
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }

        // var exec = Executors.newFixedThreadPool(5);
        // try (var servidor = new ServerSocket(8000); var utils = new Utils();) {
        //     // var conexao = new LidarClientes(servidor.accept(), cozinha);
        //     exec.execute(new LidarClientes(servidor.accept(), cozinha));
        //     new Thread(() -> {
        //         try {
        //             while (!parar) {
        //                 utils.showArray(opcoes);
        //                 int opt = utils.lerOption("Sua opção: ", 1, 3, "inválido");
        //                 switch (opt) {
        //                     case 0 -> cadastrarItem(utils);
        //                     case 1 -> listarMesas();
        //                     case 2 -> encerrar(exec, servidor);
        //                 }
        //             }
        //         } catch (Exception e) {
        //             System.out.println("Erro na thread cozinha");
        //         }
        //     }).start();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

    public void start(ExecutorService exec) throws IOException {
        System.out.println("Cozinha iniciada");
        server = new ServerSocket(PORT);
        exec.execute(() -> 
            {try (var utils = new Utils()) {
                while (!parar) {
                    utils.showArray(opcoes);
                    int opt = utils.lerOption("Sua opção: ", 1, 3, "inválido");
                    switch (opt) {
                        case 0 -> cadastrarItem(utils);
                        case 1 -> listarMesas();
                        case 2 -> encerrar(exec);
                    }
                }
            } catch (Exception e) {
                System.out.println("erro: " + e.getMessage());
            }}
        );
        clientConnectionLoop(exec);
    }

    private void clientConnectionLoop(ExecutorService exec) throws IOException {
        while (true) {
            var lidarCliente = new LidarClientes(server.accept(), cozinha);
            System.out.println("Cliente conectado");
            exec.execute(() -> clientMessageLoop(lidarCliente));
        }
    }

    public void clientMessageLoop(LidarClientes lidarClientes) {
        int msg;
        while ((msg = lidarClientes.getMessage()) != -1) {
            if (msg == -1 || msg == 4) {
                lidarClientes.close();
                return;
            }
            System.out.println("Mensagem cliente: " + msg);
        }
    }

    public static void cadastrarItem(Utils utils) {
        var nome = utils.lerString("Digite o nome do produto: ", "Nome inválido");
        var preco = utils.lerDouble("Digite o preço do produto: ");
        var quantidade = utils.lerInt("Digite o quantidade do produto: ");
        cozinha.adicionarItem(new Item(nome, preco, quantidade));
    }

    public static void listarMesas() {
        var mesas = cozinha.getMesas();
        if (mesas.size() == 0) {
            System.out.println("Nenhuma mesa cadastrada");
            return;
        }
        mesas.stream().forEach(mesa -> System.out.printf("Nome: %s | Entrada: %s | Total: %.2f", mesa.getNomeCliente(),
                mesa.getHorarioEntrada(), mesa.getTotalConta()));
    }

    public static void encerrar(ExecutorService exec) throws IOException {
        parar = true;
        exec.shutdownNow();
        System.out.println("encerrando");
    }

    public static void enviarItem() {

    }
}