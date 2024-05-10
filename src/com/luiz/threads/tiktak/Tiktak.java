package com.luiz.threads.tiktak;

public class Tiktak {
    private boolean tique;

    public Tiktak() {
        this.tique = true;
    }

    // Só pode usar wait e notify em blocos sincronizados
    public synchronized void tique(boolean estaExecutando) {
        if (!estaExecutando) {
            this.tique = false;
            notify();
            return;
        }
        System.out.println(Thread.currentThread().getName() + " Tique");
        this.tique = true;
        notify();

        while (tique) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void taque(boolean estaExecutando) {
        if (!estaExecutando) {
            this.tique = false;
            notify();
            return;
        }
        System.out.println(Thread.currentThread().getName() + " Taque");
        this.tique = false;
        notify();

        while (!this.tique) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}