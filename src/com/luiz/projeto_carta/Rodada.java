package com.luiz.projeto_carta;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Rodada {
    private List<Jogador> jogadores;
    private int tipoCarta;
    private int qtdPartidas;
    private Set<Carta> cartasPartida;

    public Rodada(List<Jogador> jogadores, int tipoCarta, int qtdPartidas) {
        this.jogadores = jogadores;
        this.tipoCarta = tipoCarta;
        this.qtdPartidas = qtdPartidas;
        this.cartasPartida = new LinkedHashSet<>(jogadores.size());
    }

    public void iniciar() {
        for (int i = 0; i < qtdPartidas; i++) {
            this.geraCartas();
            this.mostraCartas();
            this.cartasPartida.clear();
        }
    }

    public void geraCartas() {
        while (this.cartasPartida.size() < this.jogadores.size()) {
            switch (this.tipoCarta) {
                case 1 -> this.cartasPartida.add(new Carta());
                case 2 -> this.cartasPartida.add(new CartaNaipe());
                case 3 -> this.cartasPartida.add(new CartaValor());
                default -> throw new IllegalArgumentException("Tipo de carta inválido");
            };
        }
    }

    public void mostraCartas() {
        List<Carta> cartasArray = new ArrayList<>(this.cartasPartida);
        System.out.printf("%-10s %-25s %10s%n", "Nome", "Carta", "Pontos");
        Carta carta;
        Jogador jogador;
        for (int i = 0; i < jogadores.size(); i++) {
            carta = cartasArray.get(i);
            jogador = jogadores.get(i);
            System.out.printf("%-10s recebeu um(a) %-17s %d%n", jogador.getNome(), carta, carta.getValor());
        }
        System.out.println();
    }
}