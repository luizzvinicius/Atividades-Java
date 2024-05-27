package com.luiz.server2;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public void adicionaItem(String nome, double preco, int quantidade) {
        this.itens.add(new Item(nome, preco, quantidade));
    }

    public void mostraItens() {
        this.itens.forEach(i -> System.out.printf("Código: %d, %s %.2f Quantidade disponível: %d %n", i.getCodigo(), i.getNome(), i.getPreco(), i.getQuantidade()));
    }

    public double totalConta() {
        return this.itens.stream().map(i -> i.getPreco() * i.getQuantidade()).reduce(0d, (i, ii) -> i + ii);
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public String getHorarioEntrada() {
        return this.horarioEntrada;
    }

    public double getTotalConta() {
        return this.totalConta;
    }
}