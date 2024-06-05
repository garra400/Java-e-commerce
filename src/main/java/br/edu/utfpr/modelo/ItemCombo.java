package br.edu.utfpr.modelo;

public class ItemCombo {
    private Produto produto;
    private int quantidade;

    public ItemCombo(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ItemCombo{" +
                "produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}
