package com.luiz.threads.sync;

public class Counter {
    static int i = -1;

    public static void main(String[] args) {
        Runnable run = () -> {
            increment();
        };

        new Thread(run, "#1").start();
        new Thread(run, "#2").start();
        new Thread(run, "#3").start();
        new Thread(run, "#4").start();
        new Thread(run, "#5").start();
    }

    private synchronized static void increment() {
        System.out.print(Thread.currentThread().getName() + " ");
        i++;
        System.out.println(i);
    }
}