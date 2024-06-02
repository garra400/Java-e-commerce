/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.payment;

/**
 *
 * @author marco
 */
public class Wallet extends Pagamento {
    private String enderecoWallet;

    public Wallet(double valor, String enderecoWallet) {
        super(valor, "Carteira de Criptomoedas");
        this.enderecoWallet = enderecoWallet;
    }

    public String getEnderecoWallet() {
        return enderecoWallet;
    }

    @Override
    public String toString() {
        return super.toString() + " (Endereço da Carteira: " + enderecoWallet + ")";
    }
}
