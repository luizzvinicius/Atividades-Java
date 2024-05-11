package com.luiz.threads.semaforo;

public class MainSemaforo {
    public static void main(String[] args) {
        var t = new ThreadSemaforo();

        for (int i = 0; i < 6; i++) {
            System.out.println(t.getCor());
            t.esperaMudar();
        }
        t.parar();
    }
}