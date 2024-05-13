package com.luiz.threads.tiktak;

public class MyThread implements Runnable {
    private TikTak tiktak;
    public Thread t;

    public MyThread(String nome, TikTak tt) {
        this.tiktak = tt;
        t = new Thread(this, nome);
        t.start();
    }

    @Override
    public void run() {
        if (t.getName().equalsIgnoreCase("Tik")) {
            for (int i = 0; i < 5; i++) {
                tiktak.tik(true);
            }
            tiktak.tik(false);
        } else {
            for (int i = 0; i < 5; i++) {
                tiktak.tak(true);
            }
            tiktak.tak(false);
        }
    }
}