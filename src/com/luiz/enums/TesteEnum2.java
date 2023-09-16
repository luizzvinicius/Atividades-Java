package com.luiz.enums;

public class TesteEnum2 {
    public static void main(String[] args) {
        Constantes[] valores = Constantes.values();
        for (Constantes valor : valores) {
            System.out.println(valor + " " + valor.getValor());
        }
    }
    
    enum Constantes {
        PI(3.14), EULER(2.71);
        
        private final double valor;
        
        Constantes(double valor) {
            this.valor = valor;
        }

        public double getValor() {
            return this.valor;
        }
    }
}