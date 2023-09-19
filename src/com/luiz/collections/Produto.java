package com.luiz.collections;

import java.util.Objects;

public class Produto {
    private static int cont = 1;
    private int id = 0;
    private String nome;
    private double precoCompra;
    private double precoVenda;
    private int quantidadeComprada;

    // Não permitir que sejam cadastrados itens com preços inválidos, inclusive
    // preço devenda menor que o preço de compra
    // Mostrar resumo dos itens com quantidade baixa no estoque (abaixo de 50
    // itens).
    // Mostrar um resumo dos itens e suas quantidades disponíveis ordenado pela
    // descrição ou quantidade disponível, a critério do usuário.
    public Produto(String nome, double precoCompra, double precoVenda, int quantidadeComprada) {
        this.id = cont++;
        this.nome = nome;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.quantidadeComprada = quantidadeComprada;
    }

    public Produto() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    // public boolean equals(Produto outro) {
    // return this.id == outro.id && this.nome.equals(outro.nome);
    // }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        return id == other.id && Objects.equals(nome, other.nome);
    }

    public int getId() {
        return id;
    }

    public String getnome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Produto [");
        builder.append("id=").append(id).append(", nome=").append(nome).append(", precoCompra=").append(precoCompra)
                .append(", precoVenda=").append(precoVenda).append(", quantidadeComprada=").append(quantidadeComprada)
                .append("]");
        return builder.toString();
    }
}