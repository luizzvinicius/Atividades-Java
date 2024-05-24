package com.luiz.server2;

import java.io.Serializable;

public class Item implements Serializable {
    private static int id;
    private int codigo;
    private final String nome;
    private final double preco;
    private int quantidade;

    public Item(String nome, double preco, int quantidade) {
        this.codigo = ++id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public double getPreco() {
        return this.preco;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void diminuirQuantidade(int qtd) {
        this.quantidade = this.quantidade - qtd;
    }
}