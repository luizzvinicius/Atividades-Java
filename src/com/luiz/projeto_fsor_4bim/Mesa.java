package com.luiz.projeto_fsor_4bim;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Mesa {
  private final int codigo;
  private final String nomeCliente;
  private final String horarioEntrada;
  private String horarioSaida;
  private double totalConta;

  public Mesa(int codigo, String nomeCliente) {
    this.codigo = codigo;
    this.nomeCliente = nomeCliente;
    this.horarioEntrada = LocalTime.now().format(DateTimeFormatter.ofPattern("kk:mm:ss"));
  }

  public int getCodigo() {
    return this.codigo;
  }

  public String getNomeCliente() {
    return this.nomeCliente;
  }

  public String getHorarioEntrada() {
    return this.horarioEntrada;
  }

  public String getHorarioSaida() {
    return this.horarioSaida;
  }

  public double getTotalConta() {
    return this.totalConta;
  }

  public void setHorarioSaida(String horarioSaida) {
    this.horarioSaida = horarioSaida;
  }

  public void adicionarTotalConta(double preco) {
    this.totalConta += preco;
  }
}
