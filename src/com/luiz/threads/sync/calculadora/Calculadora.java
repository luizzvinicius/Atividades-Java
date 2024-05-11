package com.luiz.threads.sync.calculadora;

public class Calculadora {
    public synchronized int somaArray(int[] nums) { // Apenas uma thread vai acessar esse método
        int soma = 0;
        for (int i = 0; i < nums.length; i++) {
            soma += nums[i];
            System.out.println("Somando atualmente: " + Thread.currentThread().getName() + " com valor " + soma);
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
        return soma;
    }
}