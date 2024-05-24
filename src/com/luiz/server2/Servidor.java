package com.luiz.server2;

import java.io.*;
import java.util.concurrent.*;
import java.net.ServerSocket;

public class Servidor {
    private ServerSocket server;
    private boolean parar;
    private final Cozinha cozinha = new Cozinha("IFAL");
    private final String[] opcoes = new String[] { "Cadastrar item", "Listar mesas", "Encerrar cozinha" };
    static final String[] ClientOptions = new String[] { "Cadastrar Mesa", "Enviar itens", "Adicionar Item", "Fechar conta" };

    public static final int PORT = 8000;

    public static void main(String[] args) {
        ExecutorService exec = null;
        try {
            exec = Executors.newFixedThreadPool(6); // 1 servidor + 5 clientes
            var servidor = new Servidor();
            servidor.start(exec);
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        } finally {
            if (exec != null) {
                exec.shutdownNow();
            }
        }
    }

    public void start(ExecutorService exec) throws IOException {
        server = new ServerSocket(PORT);
        System.out.printf("Bem-vindo ao restaurante %s%n", cozinha.getNome());
        exec.execute(() -> {
            try (var utils = new Utils()) {
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
            }
        });
        clientConnectionLoop(exec);
    }

    private void clientConnectionLoop(ExecutorService exec) throws IOException {
        while (!parar) {
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
            // System.out.println("Mensagem cliente: " + msg);
        }
    }

    public void cadastrarItem(Utils utils) {
        var nome = utils.lerString("Digite o nome do produto: ", "Nome inválido");
        var preco = utils.lerDouble("Digite o preço do produto: ");
        var quantidade = utils.lerInt("Digite o quantidade do produto: ");
        cozinha.adicionarItem(new Item(nome, preco, quantidade));
    }

    public void listarMesas() {
        var mesas = cozinha.getMesas();
        if (mesas.size() == 0) {
            System.out.println("Nenhuma mesa cadastrada");
            return;
        }
        mesas.stream()
        .forEach(mesa -> System.out.printf("Nome: %s | Entrada: %s | Total: %.2f%n",
        mesa.getNomeCliente(), mesa.getHorarioEntrada(), mesa.getTotalConta()));
    }

    public void encerrar(ExecutorService exec) throws IOException {
        parar = true;
        exec.shutdownNow();
        System.out.println("encerrando");
    }
}