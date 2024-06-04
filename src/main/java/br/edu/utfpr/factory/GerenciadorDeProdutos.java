/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.factory;
import br.edu.utfpr.modelo.*;
import br.edu.utfpr.movimentacoes.*;
import br.edu.utfpr.payment.*;

/**
 *
 * @author Garra pc
 */
public class GerenciadorDeProdutos {
        public static Produto criarProduto(int tipoProduto, String nome, double preco) {
        switch (tipoProduto) {
            case 1:
                return new Lanche(nome, preco);
            case 2:
                return new Pizza(nome, preco);
            case 3:
                return new Bebida(nome, preco);
            default:
                throw new IllegalArgumentException("Tipo de produto inválido.");
        }
    }
}
