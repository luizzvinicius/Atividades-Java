package com.luiz.validacao;

public class TesteValidacao {
    public static void main(String[] args) {
        try (var scan = new Entrada()) {
            var opt = scan.lerDouble("Digite um número: ");
            System.out.printf("Preço %.2f", opt);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}