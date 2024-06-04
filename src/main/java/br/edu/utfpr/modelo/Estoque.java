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
    private static Estoque instancia;
    private Map<Produto, Integer> produtos;

    public Estoque() {
        produtos = new HashMap<>();
    }

    public static synchronized Estoque getInstancia() {
        if (instancia == null) {
            instancia = new Estoque();
        }
        return instancia;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        produtos.put(produto, produtos.getOrDefault(produto, 0) + quantidade);
    }

    public Map<Produto, Integer> getProdutos() {
        return produtos;
    }
}