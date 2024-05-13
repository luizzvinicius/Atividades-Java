package com.luiz.threads.tiktak;

public class MainTiktak {
    public static void main(String[] args) {
        TikTak tiktak = new TikTak();
        var tik = new MyThread("Tik", tiktak);
        var tak = new MyThread("Tak", tiktak);

        try {
            tik.t.join();
            tak.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }   
}