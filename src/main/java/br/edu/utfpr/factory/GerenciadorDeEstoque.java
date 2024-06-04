/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.factory;
import br.edu.utfpr.modelo.*;
import br.edu.utfpr.movimentacoes.*;
import br.edu.utfpr.payment.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Garra pcs
 */
public class GerenciadorDeEstoque {
    private static Estoque instancia;
    private Map<Produto, Integer> produtos;

    private GerenciadorDeEstoque() {
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
