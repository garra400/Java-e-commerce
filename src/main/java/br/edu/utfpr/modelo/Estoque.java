/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.modelo;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author Garra pc
 */
public class Estoque {
    private Map<Produto, Integer> produtos;

    public Estoque() {
        produtos = new HashMap<>();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        produtos.put(produto, produtos.getOrDefault(produto, 0) + quantidade);
    }

    public boolean removerProduto(Produto produto, int quantidade) {
        int atual = produtos.getOrDefault(produto, 0);
        if (atual >= quantidade) {
            produtos.put(produto, atual - quantidade);
            return true;
        }
        return false;
    }

    public int getQuantidade(Produto produto) {
        return produtos.getOrDefault(produto, 0);
    }

    public Map<Produto, Integer> getProdutos() {
        return produtos;
    }
}