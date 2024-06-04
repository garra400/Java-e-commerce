/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.payment;

/**
 *
 * @author julia
 */
public class CartaoCredito extends Pagamento {
    private String numeroCartao;

    public CartaoCredito(double valor, String numeroCartao) {
        super(valor, "Cartão de Crédito");
        this.numeroCartao = numeroCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    @Override
    public String toString() {
        return super.toString() + " (Número do Cartão: " + numeroCartao + ")";
    }
}
