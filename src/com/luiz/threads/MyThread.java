package com.luiz.threads;

public class MyThread implements Runnable { // melhor usar runnable
    private String nome;
    private long tempoEspera;
    public Thread t;

    public MyThread(String nome, long tempoEspera) {
        this.nome = nome;
        this.tempoEspera = tempoEspera;
        // Forma #1
        this.t = new Thread(this);

        // Forma #2
        // this.run();

        // Forma #3
        // Chamar run() no main
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 6; i++) {
                System.out.println(this.nome + " " + i);
                Thread.sleep(this.tempoEspera);
            }
            System.out.println(this.nome + " Terminou");
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }
}