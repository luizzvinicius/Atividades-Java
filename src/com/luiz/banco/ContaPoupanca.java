package com.luiz.banco;

public class ContaPoupanca extends ContaBancaria {
    protected int diaRendimento;
    private int diaRenderContas = 9;

    public ContaPoupanca() {
    }

    public ContaPoupanca(String nomeCliente, long numConta, double saldo, int diaRendimento) {
        super(nomeCliente, numConta, saldo);
        this.diaRendimento = diaRendimento;
    }

    public void calculaNovoSaldo() {
        if (diaRenderContas == this.diaRendimento) {
            this.setSaldo(this.getSaldo() + (this.getSaldo() * 0.1));
        }
    }

    public int getDiaRendimento() {
        return diaRendimento;
    }

    public void setDiaRendimento(int diaRendimento) {
        this.diaRendimento = diaRendimento;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(" ContaPoupanca [diaRendimento: ").append(diaRendimento).append("]");
        return builder.toString();
    }
}