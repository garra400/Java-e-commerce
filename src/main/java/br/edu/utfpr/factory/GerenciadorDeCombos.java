package br.edu.utfpr.factory;

import br.edu.utfpr.modelo.Combo;
import br.edu.utfpr.modelo.ItemCombo;
import br.edu.utfpr.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeCombos {
    private static GerenciadorDeCombos instancia;
    private Connection conexao;
    private String nome;
    private double desconto;
    private List<ItemCombo> itens;

    private GerenciadorDeCombos(Connection conexao) {
        this.conexao = conexao;
        this.itens = new ArrayList<>();
    }

    public static synchronized GerenciadorDeCombos getInstancia(Connection conexao) {
        if (instancia == null) {
            instancia = new GerenciadorDeCombos(conexao);
        }
        return instancia;
    }

    public GerenciadorDeCombos configurarCombo(String nome, double desconto) {
        this.nome = nome;
        this.desconto = desconto;
        return this;
    }

    public GerenciadorDeCombos adicionarItem(Produto produto, int quantidade) {
        itens.add(new ItemCombo(produto, quantidade));
        return this;
    }

    public Combo criarCombo() {
        try {
            String query = "INSERT INTO combos (nome, desconto) VALUES (?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nome);
            stmt.setDouble(2, desconto);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                Combo combo = new Combo(id, nome, desconto);

                for (ItemCombo item : itens) {
                    adicionarItemAoCombo(combo, item.getProduto(), item.getQuantidade());
                }

                return combo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void adicionarItemAoCombo(Combo combo, Produto produto, int quantidade) {
        try {
            String query = "INSERT INTO itens_combo (combo_id, produto_id, quantidade) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, combo.getId());
            stmt.setInt(2, produto.getId());
            stmt.setInt(3, quantidade);
            stmt.executeUpdate();
            combo.adicionarItem(produto, quantidade);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
