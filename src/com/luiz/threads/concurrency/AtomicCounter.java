package com.luiz.threads.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    // Não usa syncromized, mas garante uma única thread acessando
    static AtomicInteger atomicInt = new AtomicInteger(-1);
    // AtomicLong, AtomicBoolean, AtomicReference<T>
    public static void main(String[] args) {
        Runnable run = () -> {
            System.out.println(atomicInt.getAndAccumulate(2,  (a, b) -> a-b) + ": " + Thread.currentThread().getName());
        };

        new Thread(run, "Thread 1").start();
        new Thread(run, "Thread 2").start();
        new Thread(run, "Thread 3").start();
    }
}