package com.luiz.classe_aninhada;

public class TesteAnonima {
    public void imprimeTexto() {
        System.out.println("Fora.");
    }
    public static void main(String[] args) {
        var anonima = new TesteAnonima() {
            public void imprimeTexto() {
                System.out.println("Sobrescrever texto");
            }
        };

        anonima.imprimeTexto();
    }
}