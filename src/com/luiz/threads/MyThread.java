package com.luiz.threads;

public class MyThread implements Runnable { // melhor usar runnable
    private String nome;
    private long tempoEspera;

    public MyThread(String nome, long tempoEspera) {
        this.nome = nome;
        this.tempoEspera = tempoEspera;
        Thread t = new Thread(this); // Mesmmo que use runnable, é necessário passar para a classe Thread
        t.run(); // aqui ou no main
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 6; i++) {
                System.out.println(this.nome + " " + i);
                Thread.sleep(this.tempoEspera);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }
}