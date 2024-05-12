package com.luiz.threads.sync.conta;

public class Conta {
    private double saldo;

    public Conta(double saldo) {
        this.saldo = saldo;
    }

    public void sacar(double valor) {
        this.saldo = this.saldo - valor;
    }

    public double getSaldo() {
        return saldo;
    }
}