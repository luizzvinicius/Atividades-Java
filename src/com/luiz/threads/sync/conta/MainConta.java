package com.luiz.threads.sync.conta;

public class MainConta {
    public static void main(String[] args) {
        var t1 = new ContaThread("Thread 1");
        var t2 = new ContaThread("Thread 2");

        t1.t.start();
        t2.t.start();
    }
}