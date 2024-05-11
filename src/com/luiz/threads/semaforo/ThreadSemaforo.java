package com.luiz.threads.semaforo;

public class ThreadSemaforo implements Runnable {
    private CorSemaforo cor;
    private boolean executando;
    private boolean corMudou;

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

    private synchronized void mudarCor() {
        switch (this.cor) {
            case VERMELHO -> this.cor = CorSemaforo.VERDE;
            case AMARELO -> this.cor = CorSemaforo.VERMELHO;
            case VERDE -> this.cor = CorSemaforo.AMARELO;
        }
        this.corMudou = true;
        notify();
    }

    public synchronized void esperaMudar() {
        while (!this.corMudou) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.corMudou = false;
    }

    public synchronized void parar() {
        this.executando = false;
    }

    public CorSemaforo getCor() {
        return cor;
    }
}