package com.luiz.threads.sync.calculadora;

public class MyThread implements Runnable {
    private String nome;
    private int[] nums;
    private static Calculadora calc = new Calculadora();

    public MyThread(String nome, int[] nums) {
        this.nome = nome;
        this.nums = nums;
        new Thread(this, nome).start();
    }

    @Override
    public void run() {
        System.out.println(this.nome + " Começou");
        int soma = calc.somaArray(this.nums);
        System.out.println(soma + " para " + this.nome);
        System.out.println(this.nome + " Terminou");
    }
}