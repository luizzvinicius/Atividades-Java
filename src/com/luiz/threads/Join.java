package com.luiz.threads;

public class Join {
    public static void main(String[] args) {
        var thread1 = new MyThread("#1", 500);
        var thread2 = new MyThread("#2", 500);
        var thread3 = new MyThread("#3", 500);

        thread1.t.start();
        thread2.t.start();
        thread3.t.start();

        try {
            thread1.t.join(); // Espera a execução da thread terminar
            thread2.t.join(); // Se passar tempo como parâmetro, espera esse tempo para executar a próxima ou executa a próxima thread depois desse tempo
            thread3.t.join();
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }

        System.out.println("fim"); // É garantido que esse código será executado quando as threads terminarem
    }
}