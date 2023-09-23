package com.luiz.projeto_carta;

public sealed class Carta permits CartaValor, CartaNaipe {
    enum Naipes {
        Paus(2), Ouros(3), Copas(4), Espadas(5);

        private int valor;

        Naipes(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }
    }

    protected int numero;
    protected Naipes naipe;

    public Carta() {
    }

    public int valor() {
        return this.numero * this.naipe.valor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Naipes getNaipe() {
        return naipe;
    }

    public void setNaipe(Naipes naipe) {
        this.naipe = naipe;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Carta [numero=");
        builder.append(numero).append(", naipe=").append(naipe).append("]");
        return builder.toString();
    }
}