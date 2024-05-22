package com.luiz.java_net;

public class Item {
    private static int codigo = 0;
    private final String nome;
    private final double preco;
    private double quantidade;

    public Item(String nome, double preco, double quantidade) {
        Item.codigo++;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return Item.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public double getPreco() {
        return this.preco;
    }

    public double getQuantidade() {
        return this.quantidade;
    }

    public void diminuirQuantidade() {
        this.quantidade--;
    }
}