/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.payment;

/**
 *
 * @author marco
 */
public class Pix extends Pagamento {
    private String chavePix;

    public Pix(double valor, String chavePix) {
        super(valor, "Pix");
        this.chavePix = chavePix;
    }

    public String getChavePix() {
        return chavePix;
    }

    @Override
    public String toString() {
        return super.toString() + " (Chave Pix: " + chavePix + ")";
    }
}
