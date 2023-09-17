package com.luiz.classe_aninhada;

public class TesteExterna {
    public static void main(String[] args) {
        var externa = new Externa();
        System.out.println(externa.getTexto());

        var interna = externa.new Interna();
        System.out.println(interna.getTexto() + "\n");

        externa.imprimirTextos();
        interna.imprimirTextos();
    }
}