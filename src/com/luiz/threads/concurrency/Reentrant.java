package com.luiz.threads.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class Reentrant {
    // Lock é uma interface | parâmetro fair tenta passar o lock para a thread mais ociosa
    // Código muito verboso
    // Vantagem é o fair (Não é garantido), mas nem conta tanto
    // Compete com o syncronized

    static class Worker implements Runnable {
        private String nome;
        private ReentrantLock l;

        public Worker(String nome, ReentrantLock l) {
            this.nome = nome;
            this.l = l;
        }

        @Override
        public void run() {
            l.lock();
            try {
                if (l.isHeldByCurrentThread()) {
                    System.out.println(this.nome + " Entrou no lock");
                    System.out.println("Estimativa de threads esperando: " + l.getQueueLength());
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // liberar objeto
                System.out.println(this.nome + " Saiu do lock");
                l.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock l = new ReentrantLock(true);
        new Thread(new Worker("Thread 1", l)).start();
        new Thread(new Worker("Thread 2", l)).start();
        new Thread(new Worker("Thread 3", l)).start();
    }
}