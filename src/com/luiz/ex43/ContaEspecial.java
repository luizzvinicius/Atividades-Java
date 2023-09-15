package com.luiz.ex43;

public class ContaEspecial extends ContaBancaria {
    protected double limite;

    public ContaEspecial() {
    }

    public ContaEspecial(String nomeCliente, long numConta, double saldo) {
        super(nomeCliente, numConta, saldo);
    }

    @Override
    public boolean sacar(double quantia) {
        this.limite = this.saldo * 0.5;
        if (quantia >= this.limite) {
            return false;
        }
        setSaldo(getSaldo() - quantia);
        return true;
    }

    public double getLimite() {
        return limite;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(" ContaEspecial [limite=").append(limite).append("]");
        return builder.toString();
    }
}