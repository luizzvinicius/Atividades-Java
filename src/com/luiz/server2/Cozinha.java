package com.luiz.server2;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ArrayBlockingQueue;

public class Cozinha implements Serializable {
    private ArrayBlockingQueue<Item> itens;
    private ArrayBlockingQueue<Mesa> mesas;
    private String nome;

    public Cozinha(String nome) {
        this.nome = nome;
        this.itens = new ArrayBlockingQueue<>(10, false);
        this.mesas = new ArrayBlockingQueue<>(10, false);
    }

    public void diminuirQuantidade(int codItem, int qtd) {
        this.itens.forEach(i -> {
            if (i.getCodigo() == codItem) {
                i.diminuirQuantidade(qtd);
            }
        });
    }

    public Mesa excluirMesa(String nome) {
        Mesa mesa = null;
        for (Mesa m : this.mesas) {
            if (m.getNomeCliente().equals(nome)) {
                m.setHorarioSaida(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
                mesa = m;
                this.mesas.remove(m);
            }
        }
        return mesa;
    }

    public void adicionarItem(Item item) {
        this.itens.offer(item);
    }

    public void adicionarMesa(Mesa mesa) {
        this.mesas.offer(mesa);
    }

    public ArrayBlockingQueue<Mesa> getMesas() {
        return mesas;
    }

    public ArrayBlockingQueue<Item> getItens() {
        return itens;
    }

    public String getNome() {
        return nome;
    }
}