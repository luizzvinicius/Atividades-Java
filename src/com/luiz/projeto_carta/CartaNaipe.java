package com.luiz.projeto_carta;

public final class CartaNaipe extends Carta {
    @Override
    public int calculaValor() {
        return this.numero;
    }
}