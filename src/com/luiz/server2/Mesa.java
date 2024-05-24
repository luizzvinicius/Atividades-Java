package com.luiz.server2;

import java.util.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Mesa implements Serializable {
    private final String nomeCliente;
    private final String horarioEntrada;
    private String horarioSaida;
    private List<Item> itens;
    private double totalConta;

    public Mesa(String nomeCliente) {
        this.nomeCliente = nomeCliente;
        this.horarioEntrada = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        this.itens = new ArrayList<>();
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
        this.totalConta = preco;
    }

    public List<Item> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Mesa [nomeCliente=").append(nomeCliente).append(", totalConta=").append(totalConta).append("]");
        return builder.toString();
    }
}