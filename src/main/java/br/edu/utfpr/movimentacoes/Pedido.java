/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.movimentacoes;
import java.util.Date;
import br.edu.utfpr.modelo.Usuario;
import br.edu.utfpr.state.EstadoPedido;
/**
 *
 * @author Garra pc
 */
public class Pedido {
    private Usuario usuario;
    private Carrinho carrinho;
    private EstadoPedido estado;
    private Date data;

    public Pedido(Usuario usuario, Carrinho carrinho) {
        this.usuario = usuario;
        this.carrinho = carrinho;
        this.estado = EstadoPedido.REALIZADO;
        this.data = new Date();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Date getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Pedido de " + usuario.getNomeDeUsuario() + " em " + data + " - Estado: " + estado;
    }
}