package com.luiz.server2;

import java.net.Socket;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.io.*;

public class LidarClientes {
    private final Socket socket;
    private final DataInputStream entrada;
    private final DataOutputStream saida;
    private final ObjectOutputStream saidaObj;
    static Cozinha cozinha;

    public LidarClientes(Socket socket, Cozinha cozinha) throws IOException {
        this.socket = socket;
        LidarClientes.cozinha = cozinha;
        this.saida = new DataOutputStream(socket.getOutputStream());
        this.saidaObj = new ObjectOutputStream(socket.getOutputStream());
        this.entrada = new DataInputStream(socket.getInputStream());
    }

    public int getMessage() {
        try {
            int opt = this.entrada.readInt();
            System.out.println("Opt: " + opt);
            switch (opt) {
                case 0:
                    var mesa = new Mesa(entrada.readUTF());
                    LidarClientes.cozinha.adicionarMesa(mesa);
                    break;
                case 1:
                    System.out.println(LidarClientes.cozinha.getItens());
                    saidaObj.writeObject(LidarClientes.cozinha.getItens());
                    break;
                case 2:
                    var codAndQtd = entrada.readUTF().split(" ");
                    cozinha.diminuirQuantidade(Integer.parseInt(codAndQtd[0]), Integer.parseInt(codAndQtd[1]));
                    break;
                case 3:
                    System.out.println("printa Nome ?");
                    var nomeCliente = entrada.readUTF();
                    System.out.println("Nome: " + nomeCliente);
                    Mesa m = cozinha.excluirMesa(nomeCliente);
                    long tempoPernencia = ChronoUnit.MINUTES.between(LocalTime.parse(m.getHorarioSaida()), LocalTime.parse(m.getHorarioEntrada()));
                    saidaObj.writeObject(tempoPernencia);
                    break;
                case 4:
                    System.out.println("Cliente encerrou conexão");    
                    this.close();
            }
            return opt;
        } catch (IOException e) {
            System.out.println("erro ao pegar mensagem: " + e.getStackTrace().toString());
            return -1;
        }
    }

    public boolean sendMessage() {
        try {
            this.saida.writeInt(getMessage());
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao enviar mensagem: " + e.getMessage());
            return false;
        }
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