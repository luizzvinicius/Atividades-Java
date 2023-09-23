package com.luiz.projeto_carta;

public final class Jogador {
    private String nome;
    private int pontuacao;

    public Jogador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Jogador [nome=");
        builder.append(nome).append(", pontuacao=").append(pontuacao).append("]");
        return builder.toString();
    }
}