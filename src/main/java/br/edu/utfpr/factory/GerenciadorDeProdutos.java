package br.edu.utfpr.factory;

import br.edu.utfpr.modelo.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorDeProdutos {
    private static GerenciadorDeProdutos instancia;
    private Connection conexao;

    private GerenciadorDeProdutos(Connection conexao) {
        this.conexao = conexao;
    }

    public static synchronized GerenciadorDeProdutos getInstancia(Connection conexao) {
        if (instancia == null) {
            instancia = new GerenciadorDeProdutos(conexao);
        }
        return instancia;
    }

    public Produto criarProduto(int tipoProduto, String nome, double preco) {
        try {
            String query = "INSERT INTO produtos (nome, preco, tipoProduto) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setInt(3, tipoProduto);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                switch (tipoProduto) {
                    case 1:
                        return new Lanche(id, nome, preco);
                    case 2:
                        return new Pizza(id, nome, preco);
                    case 3:
                        return new Bebida(id, nome, preco);
                    default:
                        throw new IllegalArgumentException("Tipo de produto inválido");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Combo criarCombo(String nome, double desconto) {
        try {
            String query = "INSERT INTO combos (nome, desconto) VALUES (?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nome);
            stmt.setDouble(2, desconto);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return new Combo(id, nome, desconto);
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

    public Map<Produto, Integer> getProdutos() {
        Map<Produto, Integer> produtos = new HashMap<>();
        try {
            String query = "SELECT * FROM produtos";
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int tipoProduto = rs.getInt("tipoProduto");
    
                Produto produto;
                switch (tipoProduto) {
                    case 1:
                        produto = new Lanche(id, nome, preco);
                        break;
                    case 2:
                        produto = new Pizza(id, nome, preco);
                        break;
                    case 3:
                        produto = new Bebida(id, nome, preco);
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo de produto inválido");
                }
                produtos.put(produto, 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        try {
            String query = "INSERT INTO estoque (produtoId, quantidade) VALUES (?, ?) ON DUPLICATE KEY UPDATE quantidade = quantidade + ?";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, produto.getId());
            stmt.setInt(2, quantidade);
            stmt.setInt(3, quantidade);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
