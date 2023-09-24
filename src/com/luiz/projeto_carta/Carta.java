package com.luiz.projeto_carta;

import java.util.Objects;
import java.util.Random;

public sealed class Carta permits CartaValor, CartaNaipe {
    protected final String[] NAIPES = { "Paus", "Ouros", "Copas", "Espadas" };
    protected final String[] NUMEROS = { "Ás", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valete", "Dama", "Rei" };

    protected int numero, naipe, valor;

    public Carta() {
        this.numero = new Random().nextInt(1, 13);
        this.naipe = new Random().nextInt(2, 5);
        this.valor = this.calculaValor();
    }

    public int calculaValor() {
        return this.numero * this.naipe;
    }

    public int getNumero() {
        return numero;
    }

    public int getNaipe() {
        return naipe;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, naipe);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Carta))
            return false;
        Carta other = (Carta) obj;
        return numero == other.numero && naipe == other.naipe;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(NUMEROS[getNumero() - 1]);
        builder.append(" de ").append(NAIPES[getNaipe() - 2]);
        return builder.toString();
    }
}