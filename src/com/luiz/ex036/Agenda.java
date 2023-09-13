package com.luiz.ex036;

import java.util.List;
import java.util.ArrayList;

public class Agenda {
    private List<Contato> contatos;
    private String nome;

    public Agenda() {
    };

    public Agenda(String nome) {
        this.nome = nome;
        this.contatos = new ArrayList<>();
    }

    public String mostrarContatos() {
        if (this.contatos.size() == 0) {
            return "Agenda vazia!";
        }
        StringBuilder builder = new StringBuilder(this.nome);
        builder.append(" ");
        contatos.forEach(contato -> builder.append(contato).append("\n"));

        return builder.toString();
    }

    public String mostrarContatos(String nome) {
        for (Contato contato : this.contatos) {
            if (contato.nome().equals(nome)) {
                return contato.toString();                
            }
        }
        
        return String.format("Contato %s não existe na %s", nome, this.nome);
    }

    public void addContato(Contato contato) {
        this.contatos.add(contato);
    }
    
    public Contato removeContato(String nome) {
        Contato excluido = null;
        for (Contato contato : this.contatos) {
            if (contato.nome().equals(nome)) {
                excluido = contato;
                break;
            }
        }
        this.contatos.remove(excluido);
        return excluido;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        String contatos = this.mostrarContatos();
        return contatos;
    }
}