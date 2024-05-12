package com.luiz.threads.sync.conta;

public class ContaThread implements Runnable {
    private static Conta contaCompartilhada = new Conta(50);
    private String nome;
    public Thread t;

    public ContaThread(String nome) {
        this.nome = nome;
        t = new Thread(this, this.nome);
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            this.sacar(10);
        }
    }
    // Não sei porque, mas há difenrença entre os dois métodos sacar
    private synchronized void sacar(double valor) { // Tornar código atômico, usa synchronized
        System.out.println("----------------------------------");
        var saldo = ContaThread.contaCompartilhada.getSaldo();
        if (valor > saldo) { // sem ele, outra thread poderia passar nesse if
            System.out.println(threadAtual() + " não sacou");
            System.out.println("Saldo insuficiente. Valor atual: " + saldo);
        } else {
            ContaThread.contaCompartilhada.sacar(valor);
            System.out.println(threadAtual() + " sacando. Saldo atual: " + ContaThread.contaCompartilhada.getSaldo());
        }
        System.out.println("----------------------------------");
    }

    // private void sacar(double valor) {
    //     System.out.println("----------------------------------");
    //     synchronized (ContaThread.contaCompartilhada) {
    //         var saldo = ContaThread.contaCompartilhada.getSaldo();
    //         if (valor > saldo) {
    //             System.out.println(threadAtual() + " não sacou");
    //             System.out.println("Saldo insuficiente. Valor atual: " + saldo);
    //         } else {
    //             ContaThread.contaCompartilhada.sacar(valor);
    //             System.out.println(threadAtual() + " sacando. Saldo atual: " + ContaThread.contaCompartilhada.getSaldo());
    //         }
    //     }
    //     System.out.println("----------------------------------");
    // }

    private String threadAtual() {
        return Thread.currentThread().getName();
    }
}