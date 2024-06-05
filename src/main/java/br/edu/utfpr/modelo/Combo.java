package br.edu.utfpr.modelo;

import java.util.ArrayList;
import java.util.List;

public class Combo extends Produto {
    private List<ItemCombo> itens;
    private double desconto;

    public Combo(int id, String nome, double desconto) {
        super(id, nome, 0.0);  // O preço será calculado com base nos itens e desconto
        this.desconto = desconto;
        this.itens = new ArrayList<>();
        recalcularPreco();
    }

    public void adicionarItem(Produto produto, int quantidade) {
        itens.add(new ItemCombo(produto, quantidade));
        recalcularPreco();
    }

    private void recalcularPreco() {
        double total = 0.0;
        for (ItemCombo item : itens) {
            total += item.getProduto().getPreco() * item.getQuantidade();
        }
        setPreco(total * (1 - desconto));
    }

    public List<ItemCombo> getItens() {
        return itens;
    }

    public void setItens(List<ItemCombo> itens) {
        this.itens = itens;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return "Combo{" +
                "nome='" + getNome() + '\'' +
                ", itens=" + itens +
                ", desconto=" + desconto +
                ", preço com desconto=" + getPreco() +
                '}';
    }
}
