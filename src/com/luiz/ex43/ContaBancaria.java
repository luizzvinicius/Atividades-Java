package com.luiz.ex43;

public abstract class ContaBancaria {
    protected String nomeCliente;
    protected long numConta;
    protected double saldo;

    public ContaBancaria() {
    }

    public ContaBancaria(String nomeCliente, long numConta, double saldo) {
        this.nomeCliente = nomeCliente;
        this.numConta = numConta;
        this.saldo = saldo;
    }

    public boolean sacar(double quantia) {
        if (quantia > this.saldo) {
            return false;
        }
        this.setSaldo(this.getSaldo() - quantia);
        return true;
    }

    public void depositar(double quantia) {
        this.setSaldo(this.saldo + quantia);
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public long getNumConta() {
        return numConta;
    }

    public void setNumConta(long numConta) {
        this.numConta = numConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ContaBancaria [nomeCliente:").append(nomeCliente).append(", numConta:").append(numConta)
                .append(", saldo:").append(saldo).append("]");
        return builder.toString();
    }
}