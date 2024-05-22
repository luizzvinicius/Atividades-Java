package com.luiz.java_net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable, AutoCloseable {
    private final ServerSocket socketServidor;
    private final ExecutorService threadPool;

    public Server(Socket s) throws IOException {
        this.socketServidor = new ServerSocket(8000);
        this.threadPool = Executors.newFixedThreadPool(5);
    }

    @Override
    public void run() {
        try {
            while (true) {
                var socket = this.socketServidor.accept();
                this.threadPool.execute(new ConexaoCliente(socket));
            }
        } catch (IOException e) {
            System.err.println("Erro de conexão:" + e.getMessage());
        }
        this.threadPool.shutdown();
    }

    @Override
    public void close() throws Exception {
        this.socketServidor.close();
    }
}