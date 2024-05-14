package com.luiz.threads.concurrency.semaforo_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSemaforo implements Runnable {
    private CorSemaforo cor;
    private boolean executando;
    private boolean corMudou;
    private ReentrantLock l = new ReentrantLock();
    // Pra usar wait e notify com locks, adapta-se o código para condition
    // Condition é referente a um lock
    private Condition condition = l.newCondition();

    public enum CorSemaforo {
        VERMELHO(2000), AMARELO(300), VERDE(1000);

        private int tempo;

        CorSemaforo(int tempo) {
            this.tempo = tempo;
        }

        public int getTempo() {
            return this.tempo;
        }
    }

    public ThreadSemaforo() {
        this.cor = CorSemaforo.VERMELHO;
        this.executando = true;
        this.corMudou = false;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (this.executando) {
            try {
                Thread.sleep(this.cor.getTempo());
                this.mudarCor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void mudarCor() {
        l.lock();
        try {
            switch (this.cor) {
                case VERMELHO -> this.cor = CorSemaforo.VERDE;
                case AMARELO -> this.cor = CorSemaforo.VERMELHO;
                case VERDE -> this.cor = CorSemaforo.AMARELO;
            }
            this.corMudou = true;
            condition.signal();
        } finally {
            l.unlock();
        }
    }

    public void esperaMudar() {
        l.lock();
        try {
            while (!this.corMudou) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.corMudou = false;
        } finally {
            l.unlock();
        }
    }

    public void parar() {
        l.lock();
        try {
            this.executando = false;
        } finally {
            l.unlock();
        }
    }

    public CorSemaforo getCor() {
        return cor;
    }
}