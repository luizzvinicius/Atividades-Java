package com.luiz.threads.deadlock;

public class Teste {
    public static void main(String[] args) {
        final String RECURSO1 = "Recurso 1";
        final String RECURSO2 = "Recurso 2";

        var thread1 = new Thread("Thread #1") {
            public void run() {
                synchronized (RECURSO1) {
                    System.out.println(Thread.currentThread().getName() + " acessando recurso 1");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " tentando recurso 2");

                    synchronized (RECURSO2) {
                        System.out.println(Thread.currentThread().getName() + " acessando recurso 2");
                    }
                }
            };
        };

        Runnable r = () -> {
            synchronized (RECURSO2) {
                System.out.println(Thread.currentThread().getName() + " acessando recurso 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " tentando recurso 1");
                synchronized (RECURSO1) {
                    System.out.println(Thread.currentThread().getName() + " acessando recurso 1");
                }
            }
        };

        thread1.start();
        new Thread(r).start();
    }
}