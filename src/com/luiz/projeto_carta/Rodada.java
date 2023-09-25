package com.luiz.projeto_carta;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Rodada {
    private List<Jogador> jogadores;
    private List<Carta> cartasRodada;
    private int tipoCarta, qtdPartidas;

    public Rodada(List<Jogador> jogadores, int tipoCarta, int qtdPartidas) {
        this.jogadores = jogadores;
        this.tipoCarta = tipoCarta;
        this.cartasRodada = new ArrayList<>();
        this.qtdPartidas = qtdPartidas;
    }

    public void iniciar() {
        for (int i = 0; i < qtdPartidas; i++) {
            this.geraCartas();
            this.mostraCartas();
            this.cartasRodada.clear();
        }
        this.placar();
    }

    public void geraCartas() {
        Set<Carta> cartasUnicas = new HashSet<>();
        while (cartasUnicas.size() < this.jogadores.size()) {
            switch (this.tipoCarta) {
                case 1 -> cartasUnicas.add(new Carta());
                case 2 -> cartasUnicas.add(new CartaNaipe());
                case 3 -> cartasUnicas.add(new CartaValor());
            };
        }
        this.cartasRodada.addAll(cartasUnicas);
        for (int i = 0; i < jogadores.size(); i++) {
            this.jogadores.get(i).setCarta(this.cartasRodada.get(i));
        }
        cartasUnicas.clear();
    }

    public void mostraCartas() {
        // ordena em ordem decrescente
        this.jogadores.sort((item1, item2) -> Integer.compare(item2.getCarta().getValor(), item1.getCarta().getValor()));
        System.out.printf("%-10s %-25s %10s%n", "Nome", "Carta", "Pontos");
        for (Jogador jogador : this.jogadores) {
            System.out.printf("%-10s recebeu um(a) %-17s %d%n", jogador, jogador.getCarta(), jogador.getCarta().getValor());
        }

        var vencedor = this.jogadorVencedor();
        System.out.print("Vencedor da rodada: ");
        vencedor.forEach(jogador -> System.out.println(jogador + " "));
        System.out.println();
    }

    public List<Jogador> jogadorVencedor() {
        List<Jogador> maiorPontuacao = new ArrayList<>();
        var maiorCarta = this.jogadores.get(0).getCarta().getValor();
        
        int pontos = 3;
        for (Jogador jogador: this.jogadores) {
            if (jogador.getCarta().getValor() == maiorCarta) {
                jogador.setPontuacao(jogador.getPontuacao() + pontos);
                maiorPontuacao.add(jogador);
            } else {
                pontos--;
                if (pontos == 0) pontos = 1;
                jogador.setPontuacao(jogador.getPontuacao() + pontos);
                maiorCarta = jogador.getCarta().getValor();
            }
        }
        return maiorPontuacao;
    }

    public void placar() {
        System.out.println("----------------- Placar Final -----------------");
        this.jogadores.sort((item1, item2) -> Integer.compare(item2.getPontuacao(), item1.getPontuacao()));
        this.jogadores.forEach(jogador -> System.out.printf("%s com %d pontos%n", jogador, jogador.getPontuacao()));
    }
}