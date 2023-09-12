package com.luiz.fundamentos;

import com.luiz.validacao.Entrada;

public class App {
    public static void main(String[] args) {
        try (var scan = new Entrada()) {
            var opt = scan.lerOption("Digite um número: ", 1, 10);
            System.out.println(opt);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}