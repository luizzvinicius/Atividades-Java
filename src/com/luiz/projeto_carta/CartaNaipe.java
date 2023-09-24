package com.luiz.projeto_carta;

public final class CartaNaipe extends Carta {
    public CartaNaipe() {
        super();
    }

    @Override
    public int calculaValor() {
        return this.numero;
    }
}