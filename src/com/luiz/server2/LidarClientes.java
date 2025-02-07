package com.luiz.server2;

import java.io.*;
import java.net.Socket;

public class LidarClientes {
    private final Socket socket;
    private final DataInputStream entrada;
    private final DataOutputStream saida;
    private final ObjectOutputStream saidaObj;
    private final ObjectInputStream entradaObj;
    private static Cozinha cozinha;

    public LidarClientes(Socket socket, Cozinha cozinha) throws IOException {
        LidarClientes.cozinha = cozinha;
        this.socket = socket;
        this.saida = new DataOutputStream(socket.getOutputStream());
        this.saidaObj = new ObjectOutputStream(socket.getOutputStream());
        this.entrada = new DataInputStream(socket.getInputStream());
        this.entradaObj = new ObjectInputStream(socket.getInputStream());
    }

    public int getMessage() {
        try {
            int opt = this.entrada.readInt();
            // System.out.println("Opt: " + opt);
            switch (opt) {
                case 0 -> cadastrarMesa();
                case 1 -> listarItens();
                case 2 -> adicionarItem();
                case 3 -> excluirMesa();
                case 4 -> encerrarConexao();
            }
            return opt;
        } catch (IOException e) {
            System.out.printf("Erro ao pegar mensagem: %s%n", e.getMessage());
            return -1;
        }
    }

//    public boolean sendMessage() {
//        try {
//            this.saida.writeInt(getMessage());
//            return true;
//        } catch (IOException e) {
//            System.out.printf("Erro ao enviar mensagem: %s%n", e.getMessage());
//            return false;
//        }
//    }

    public void cadastrarMesa() {
        try {
            var mesa = (Mesa) entradaObj.readObject();
            LidarClientes.cozinha.adicionarMesa(mesa);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Não foi possível ler mesa");
        }
    }

    public void listarItens() throws IOException {
        this.saidaObj.writeObject(LidarClientes.cozinha.getItens());
        this.saidaObj.reset();
    }

    public void adicionarItem() throws IOException {
        var codAndQtd = entrada.readUTF().split(" ");
        cozinha.diminuirQuantidade(Integer.parseInt(codAndQtd[0]), Integer.parseInt(codAndQtd[1]));
    }

    public void excluirMesa() throws IOException {
        var nomeCliente = entrada.readUTF();
        Mesa m = cozinha.excluirMesa(nomeCliente);
    }

    public void encerrarConexao() {
        System.out.println("Cliente encerrou conexão");
        this.close();
    }

    public void close() {
        try {
            this.entrada.close();
            this.saida.close();
            this.socket.close();
        } catch (IOException e) {
            System.out.printf("Erro ao fechar recursos: %s%n", e.getMessage());
        }
    }
}