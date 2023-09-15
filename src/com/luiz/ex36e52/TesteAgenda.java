package com.luiz.ex36e52;

import com.luiz.ex36e52.exceptions.ContatoNaoExisteException;
import com.luiz.validacao.Entrada;

public class TesteAgenda {
    public static void main(String[] args) {
        try (Entrada scan = new Entrada()) {
            var nomeAgenda = scan.lerString("Digite o nome da sua agenda: ");
            var agenda1 = new Agenda(nomeAgenda);
            
            Contato contato;
            var nome = scan.lerString("Digite o nome do contato: ");
            var telefone = scan.lerString("Digite o telefone do contato: "); // não adaptei o lerString para ler números
            var email = scan.lerString("Digite o email do contato: ");
            agenda1.addContato(new Contato(nome, telefone, email));
            
            for (int i = 1; i < 7; i++) {
                contato = new Contato("Luiz"+i, "82 99333-908"+i, "luiz"+i+"@email.com");
                agenda1.addContato(contato);
            }
            
            try {
                agenda1.mostrarContatos("Gal Gadot");
            } catch (ContatoNaoExisteException e) {
                System.out.println(e.getMessage() + "\n");
            }
            
            System.out.println(agenda1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}