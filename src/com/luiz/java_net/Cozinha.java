package com.luiz.java_net;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Cozinha {
    private List<Item> itens;
    private ArrayBlockingQueue<Mesa> mesas;
    private String nome;
    
    public Cozinha(String nome) {
        this.nome = nome;
        this.itens = new ArrayList<>();
        this.mesas = new ArrayBlockingQueue<>(10, false);
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public void adicionarMesa(Mesa mesa) {
        this.mesas.add(mesa);
    }

    public ArrayBlockingQueue<Mesa> getMesas() {
        return mesas;
    }

    public String getNome() {
        return nome;
    }

    public List<Item> getItens() {
        return itens;
    }
}