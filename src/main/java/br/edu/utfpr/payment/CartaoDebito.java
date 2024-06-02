/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.payment;

/**
 *
 * @author marco
 */
public class CartaoDebito extends Pagamento {
    private String numeroCartao;

    public CartaoDebito(double valor, String numeroCartao) {
        super(valor, "Cartão de Débito");
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
