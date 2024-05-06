package com.luiz.threads;

public class TesteThread {
    public static void main(String[] args) {
        var contador = new MyThread("Thread 1", 650);
        contador.run();
    }
}