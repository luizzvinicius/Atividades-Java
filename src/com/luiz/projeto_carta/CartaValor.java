package com.luiz.projeto_carta;

public final class CartaValor extends Carta {
    @Override
    public int calculaValor() {
        return this.isPrime(this.numero) ? super.calculaValor() * 3 : super.calculaValor();
    }

    private boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }

        for (int i = 2; i < num / 2 + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}