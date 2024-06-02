/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.modelo;

/**
 *
 * @author Garra pc
 */
public class Combo extends Produto {
    private Produto[] itens;

    public Combo(String nome, double preco, Produto[] itens) {
        super(nome, preco);
        this.itens = itens;
    }

    public Produto[] getItens() {
        return itens;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString() + " (Inclui: ");
        for (Produto item : itens) {
            sb.append(item.getNome()).append(", ");
        }
        sb.setLength(sb.length() - 2); // Remove a última vírgula
        sb.append(")");
        return sb.toString();
    }
}
