package com.luiz.threads.tiktak;

public class MainTiktak {
    public static void main(String[] args) { // Não está funcionando 100%
        var t1 = new MyThread("tique");
        var t2 = new MyThread("taque");

        try {
            t1.t.join();
            t2.t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}