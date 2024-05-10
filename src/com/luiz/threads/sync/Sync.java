package com.luiz.threads.sync;

public class Sync {
    public static void main(String[] args) {
        // Importante em certificações
        int[] array = {1,2,3,4,5};

        new MyThread("#1", array);
        new MyThread("#2", array);
    }   
}