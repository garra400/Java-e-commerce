/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.factory;

/**
 *
 * @author Garra pc
 */
import br.edu.utfpr.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeCombos {
    private String nome;
    private double desconto;
    private List<ItemCombo> itens;

    public GerenciadorDeCombos(String nome, double desconto) {
        this.nome = nome;
        this.desconto = desconto;
        this.itens = new ArrayList<>();
    }

    public GerenciadorDeCombos adicionarItem(Produto produto, int quantidade) {
        itens.add(new ItemCombo(produto, quantidade));
        return this;
    }

    public Combo build() {
        Combo combo = new Combo(nome, desconto);
        for (ItemCombo item : itens) {
            combo.adicionarItem(item.getProduto(), item.getQuantidade());
        }
        return combo;
    }
}
