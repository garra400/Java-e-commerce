/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.movimentacoes;
import java.util.ArrayList;
import java.util.List;
import br.edu.utfpr.modelo.Produto;
/**
 *
 * @author Garra pc
 */
public class Carrinho {
    private List<Produto> itens;

    public Carrinho() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(Produto produto) {
        itens.add(produto);
    }

    public void removerItem(Produto produto) {
        itens.remove(produto);
    }

    public List<Produto> getItens() {
        return itens;
    }

    public double getTotal() {
        return itens.stream().mapToDouble(Produto::getPreco).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Carrinho:\n");
        for (Produto item : itens) {
            sb.append(item).append("\n");
        }
        sb.append("Total: R$ ").append(getTotal());
        return sb.toString();
    }
}