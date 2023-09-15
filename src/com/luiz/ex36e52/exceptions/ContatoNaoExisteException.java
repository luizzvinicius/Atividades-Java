package com.luiz.ex36e52.exceptions;

public class ContatoNaoExisteException extends Exception {
    private String nome;
    private String agendaNome;

    public ContatoNaoExisteException(String nome, String agendaNome) {
        this.nome = nome;
        this.agendaNome = agendaNome;
    }

    @Override
    public String getMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("Contato ").append(nome).append(" não existe na ").append(agendaNome);
        return builder.toString();
    }
}