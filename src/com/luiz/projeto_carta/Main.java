package com.luiz.projeto_carta;

import com.luiz.projeto_carta.validacao.Entrada;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try (var scan = new Entrada()) {
            int nJogadores = scan.lerOption("Quantos jogadores? ", 2, 4, "Quantidade inválida (Máximo 4)");
            List<Jogador> jogadores = new ArrayList<>();
            
            for (int i = 1; i < nJogadores + 1; i++) {
                var nome = scan.lerString("Nome do " + i + "º jogador (sem acentos): ", "Nome inválido");
                jogadores.add(new Jogador(nome));
            }

            int qtdPartidas = scan.lerOption("\nQuantas partidas? ", 1, 5, "Quantidade inválida (Máximo 5)");
            int tipoCarta = scan.lerOption("[ 1 ] Carta Normal\n[ 2 ] Carta Naipe+\n[ 3 ] Carta Valor+\nSua opção: ", 1, 3, "Opção de carta inválida");
            System.out.println();
            
            var rodada = new Rodada(jogadores, tipoCarta, qtdPartidas);
            rodada.iniciar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}