package com.luiz.threads;

public class Priority {
    public static void main(String[] args) {
        var thread1 = new MyThread("#1", 500);
        var thread2 = new MyThread("#2", 500);
        var thread3 = new MyThread("#3", 500);

        // thread1.t.setPriority(0); // prioridade de 0 a 10
        // Prioridade é definida antes da execução óbvio
        thread1.t.setPriority(Thread.MIN_PRIORITY);
        thread2.t.setPriority(Thread.NORM_PRIORITY);
        thread3.t.setPriority(Thread.MAX_PRIORITY);

        // Define prioridades de execução, mas não é garantido que vai ser respeitado
        thread1.t.start();
        thread2.t.start();
        thread3.t.start();
    }
}