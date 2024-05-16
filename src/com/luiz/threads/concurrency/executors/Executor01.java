package com.luiz.threads.concurrency.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Executor01 {
    public static void main(String[] args) {
        var i = new AtomicInteger();
        Runnable r = () -> {
            synchronized (i) {
                System.out.println(Thread.currentThread().getName());
                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i.incrementAndGet();
            }
        };
        // Caso instancie mais Threads que o passado em parâmetro, ele reaproveita
        // Executors.newSingleThreadExecutor(); Executors.newCachedThreadPool();
        ExecutorService exec = Executors.newFixedThreadPool(4);
        
        exec.execute(r);
        exec.execute(r);
        exec.execute(r);
        exec.execute(r);
        exec.execute(r);

        exec.shutdown();
        while (!exec.isTerminated()) {}

        System.out.println("Finalizado");
    }
}