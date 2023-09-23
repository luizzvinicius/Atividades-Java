package com.luiz.projeto_carta;

import com.luiz.projeto_carta.validacao.Entrada;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try (var scan = new Entrada()) {
            // int nJogadores = scan.lerOption("Quantos jogadores? ", 2, 4, "Quantidade inválida de jogadores");
            // List<Jogador> jogadores = new ArrayList<>(nJogadores);
            
            // for (int i = 0; i < nJogadores; i++) {
            //     var nome = scan.lerString("Nome do " + (i+1) + "º jogador: ", "Nome inválido");
            //     jogadores.add(new Jogador(nome));
            // }

            // int qtdPartidas = scan.lerOption("Quantas partidas? ", 2, 5, "Quantidade inválida de partidas");
            int tipoCarta = scan.lerOption("""
            [ 1 ] Carta Normal
            [ 2 ] Carta Naipe+
            [ 3 ] Carta Valor+
            Sua opção: """ + " ", 1, 3, "Opção de carta inválida");
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}