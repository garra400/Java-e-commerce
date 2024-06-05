package br.edu.utfpr.factory;

import br.edu.utfpr.modelo.Usuario;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorDeUsuarios {
    private static GerenciadorDeUsuarios instancia;
    private Connection conexao;

    private GerenciadorDeUsuarios(Connection conexao) {
        this.conexao = conexao;
    }

    public static synchronized GerenciadorDeUsuarios getInstancia(Connection conexao) {
        if (instancia == null) {
            instancia = new GerenciadorDeUsuarios(conexao);
        }
        return instancia;
    }

    public boolean registrar(String nomeDeUsuario, String senha) {
        try {
            String query = "INSERT INTO usuarios (nomeDeUsuario, senha, isAdmin) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, nomeDeUsuario);
            stmt.setString(2, senha);
            stmt.setBoolean(3, false);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String nomeDeUsuario, String senha) {
        try {
            String query = "SELECT * FROM usuarios WHERE nomeDeUsuario = ? AND senha = ?";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, nomeDeUsuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isAdmin(String nomeDeUsuario) {
        try {
            String query = "SELECT isAdmin FROM usuarios WHERE nomeDeUsuario = ?";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, nomeDeUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("isAdmin");
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarUsuarioParaAdmin(String nomeDeUsuario) {
        try {
            String query = "UPDATE usuarios SET isAdmin = ? WHERE nomeDeUsuario = ?";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setBoolean(1, true);
            stmt.setString(2, nomeDeUsuario);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}