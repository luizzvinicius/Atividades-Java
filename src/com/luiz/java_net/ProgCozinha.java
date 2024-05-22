package com.luiz.java_net;

import java.net.*;
import java.util.concurrent.*;

public class ProgCozinha {
    public static final int PORT = 8000;
    public static boolean parar;
    private ServerSocket server;
    private static ExecutorService exec;
    static final String[] opcoes = new String[] { "Cadastrar item", "Listar mesas", "Encerrar cozinha" };
    static final int lastOptionIndex = opcoes.length - 1;
    private static Cozinha cozinha = new Cozinha("Restaurante");
    private static Utils utils = new Utils(); 

    // As threads ficam no servidor
    public static void main(String[] args) {
        exec = Executors.newFixedThreadPool(5);
        System.out.println("servidor ligado");
       
        try (var utils = new Utils(); var server = new ServerSocket(PORT);) {
            System.out.println("Iniciando servidor");
            var conexao = new Server(server.accept());
            exec.execute(conexao);
    
           
            run();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void run() {
        while (true) {
            utils.showArray(opcoes);
            int opcao = utils.lerOption("Sua opção: ", 1, lastOptionIndex, "Opção inválida");
            System.out.println("opcao servidor "  + opcao);
            switch (opcao) {
                case 0 -> cadastrarItem(utils);
                case 1 -> listarMesas();
                case 2 -> { break; }
            }
        }
    }

    public static void cadastrarItem(Utils utils) {
        var nome = utils.lerString("Digite o nome do produto: ", "Nome inválido");
        var preco = utils.lerDouble("Digite o preço do produto: ");
        var quantidade = utils.lerDouble("Digite a quantidade do produto: ");
        cozinha.adicionarItem(new Item(nome, preco, quantidade));
    }

    public static void listarMesas() {
        System.out.println(cozinha.getMesas());
        cozinha.getMesas().stream()
                .forEach(mesa -> System.out.printf("Nome: %s, Hora de entrada: %s%n", mesa.getNomeCliente(),
                        mesa.getHorarioEntrada()));
    }

    public void stop() {
        this.parar = true;
        this.exec.shutdown();
    }
}