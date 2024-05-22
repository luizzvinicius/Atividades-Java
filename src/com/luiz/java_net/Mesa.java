package com.luiz.java_net;

import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Mesa {
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
}