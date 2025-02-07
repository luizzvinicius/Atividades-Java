package com.luiz.projeto_fsor_4bim;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConexaoCliente implements AutoCloseable, Runnable {
  private static final Requisicao[] FUNCOES_REQUISICOES = new Requisicao[] {
      ConexaoCliente::cadastrarMesa, ConexaoCliente::listarItens, ConexaoCliente::adicionarItem,
      ConexaoCliente::fecharConta
  };

  private final Socket socket;
  private final Dados dados;
  private DataInputStream sockEntrada;
  private DataOutputStream sockSaida;
  private Mesa mesa;

  public ConexaoCliente(Socket socket, Dados dados) {
    this.socket = socket;
    this.dados = dados;
  }

  @Override
  public void close() {
    try {
      this.socket.close();
    } catch (IOException e) {
      System.err.println("Erro de conexão com cliente: " + e.getMessage());
    }
  }

  @Override
  public void run() {
    try {
      this.sockEntrada = new DataInputStream(this.socket.getInputStream());
      this.sockSaida = new DataOutputStream(this.socket.getOutputStream());
      this.processarRequisicoes();
      this.socket.close();
    } catch (EOFException ignored) {
    } catch (IOException | InterruptedException e) {
      System.err.println("Erro de conexão com cliente: " + e.getMessage());
    }
  }

  private void processarRequisicoes() throws IOException, InterruptedException {
    while (true) {
      var tipoReq = this.sockEntrada.readByte();

      if (tipoReq >= 0 && tipoReq < FUNCOES_REQUISICOES.length) {
        FUNCOES_REQUISICOES[tipoReq].accept(this);
      } else {
        this.enviarErro("Tipo de requisição inexistente");
      }

      this.sockSaida.flush();
    }
  }

  private void enviarErro(String msg) throws IOException {
    this.sockSaida.writeBoolean(false);
    this.sockSaida.writeUTF(msg);
  }

  private void cadastrarMesa() throws IOException, InterruptedException {
    var codigoMesa = this.sockEntrada.readInt();
    var nomeCliente = this.sockEntrada.readUTF();

    if (this.mesa != null) {
      this.enviarErro("Já está em uma mesa");
      return;
    }

    var lock = this.dados.getLock().writeLock();
    lock.lockInterruptibly();
    try {
      if (this.dados.getMesaByCodigo(codigoMesa) != null) {
        this.enviarErro("Esse código já foi cadastrado");
        return;
      }

      this.mesa = new Mesa(codigoMesa, nomeCliente);
      this.dados.getMesas().add(this.mesa);
    } finally {
      lock.unlock();
    }

    this.sockSaida.writeBoolean(true);
  }

  private void listarItens() throws IOException, InterruptedException {
    var lock = this.dados.getLock().readLock();
    lock.lockInterruptibly();
    try {
      var itens = this.dados.getItens();
      this.sockSaida.writeBoolean(true);
      this.sockSaida.writeInt(itens.size());

      for (var item : itens) {
        this.sockSaida.writeInt(item.getCodigo());
        this.sockSaida.writeUTF(item.getDescricao());
        this.sockSaida.writeDouble(item.getPreco());
      }
    } finally {
      lock.unlock();
    }
  }

  private void adicionarItem() throws IOException, InterruptedException {
    var codigoItem = this.sockEntrada.readInt();

    if (this.mesa == null) {
      this.enviarErro("É necessário estar em uma mesa para fazer isso");
      return;
    }

    Item item;

    var lock = this.dados.getLock().writeLock();
    lock.lockInterruptibly();
    try {
      item = this.dados.getItemByCodigo(codigoItem);

      if (item == null) {
        this.enviarErro("Não existe item com esse código");
        return;
      }

      if (item.getQuantidade() < 1) {
        this.enviarErro("Esse item não está mais disponível");
        return;
      }

      item.diminuirQuantidade();
    } finally {
      lock.unlock();
    }

    this.mesa.adicionarTotalConta(item.getPreco());
    this.sockSaida.writeBoolean(true);
  }

  private void fecharConta() throws IOException, InterruptedException {
    if (this.mesa == null) {
      this.enviarErro("É necessário estar em uma mesa para fazer isso");
      return;
    }

    var horarioSaida = LocalTime.now().format(DateTimeFormatter.ofPattern("kk:mm:ss"));
    this.mesa.setHorarioSaida(horarioSaida);

    this.sockSaida.writeBoolean(true);
    this.sockSaida.writeDouble(this.mesa.getTotalConta());
    this.sockSaida.writeUTF(this.mesa.getHorarioEntrada().toString());
    this.sockSaida.writeUTF(horarioSaida.toString());

    this.mesa = null;
  }

  @FunctionalInterface
  private interface Requisicao {
    void accept(ConexaoCliente conexao) throws IOException, InterruptedException;
  }
}
