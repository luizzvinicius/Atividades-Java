package com.luiz.banco;

import com.luiz.validacao.Entrada;
// ex 43 loiane
public class TesteConta {
    public static void main(String[] args) {
        String[] options = { "Criar conta poupança", "Criar conta especial", "Sacar", "Depositar", "Rendimento", "Dados da Conta", "Sair" };
        try (var scan = new Entrada()) {
            int opt;
            ContaPoupanca contaP = new ContaPoupanca();
            ContaEspecial contaEsp = new ContaEspecial();
            do {
                for (var i = 0; i < options.length; i++) {
                    System.out.printf("[ %d ] %s%n", i + 1, options[i]);
                }
                opt = scan.lerOption("Sua escolha: ", 1, options.length) - 1;
                System.out.println();
                if (opt == 0) {
                    var nome = scan.lerString("Digite seu nome: ");
                    contaP.setNomeCliente(nome);
                    contaP.setSaldo(50);
                    contaP.setDiaRendimento(9);
                    contaP.setNumConta(1);
                } else if (opt == 1) {
                    var nome = scan.lerString("Digite seu nome: ");
                    contaEsp.setNomeCliente(nome);
                    contaEsp.setSaldo(1000);
                    contaEsp.setNumConta(2);
                } else if (opt == 2) {
                    var quantia = scan.lerDouble("Quanto deseja sacar? ");
                    System.out.println(contaP.sacar(quantia) == true ? "Sacou " + quantia : "Erro ao sacar");
                    System.out.println(contaEsp.sacar(quantia) == true ? "Sacou " + quantia : "Limite de saque");
                } else if (opt == 3) {
                    var quantia = scan.lerDouble("Quanto deseja depositar? ");
                    System.out.println("Saldo contaP antes: " + contaP.getSaldo());
                    System.out.println("Saldo contaEsp antes: " + contaEsp.getSaldo());
                    contaP.depositar(quantia);
                    contaEsp.depositar(quantia);
                    System.out.println("Saldo contaP depois: " + contaP.getSaldo());
                    System.out.println("Saldo contaEsp depois: " + contaEsp.getSaldo());
                } else if (opt == 4) {
                    System.out.println("Saldo contaP antes: " + contaP.getSaldo());
                    contaP.calculaNovoSaldo();
                    System.out.println("Saldo contaP depois: " + contaP.getSaldo());
                } else if (opt == 5) {
                    System.out.println(contaP.toString());
                    System.out.println(contaEsp.toString());
                }
            } while (opt != options.length-1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}