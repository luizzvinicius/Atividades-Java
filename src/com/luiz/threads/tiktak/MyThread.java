package com.luiz.threads.tiktak;

public class MyThread implements Runnable {
    private String nome;
    public Thread t;
    private static Tiktak tiktak = new Tiktak();

    public MyThread(String nome) {
        this.nome = nome;
        this.t = new Thread(this, nome);
        this.t.start();
    }

    @Override
    public void run() {
        if (t.getName().equalsIgnoreCase("tique")) {
            for (int i = 0; i < 5; i++) {
                tiktak.tique(true);
            }
            tiktak.tique(false);
        } else if (t.getName().equalsIgnoreCase("taque")) {
            for (int i = 0; i < 5; i++) {
                tiktak.taque(true);
            }
            tiktak.taque(false);
        }
    }
}