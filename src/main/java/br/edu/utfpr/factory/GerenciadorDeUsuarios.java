package br.edu.utfpr.factory;

import br.edu.utfpr.modelo.Usuario;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorDeUsuarios {
    private static final String NOME_ARQUIVO = "usuarios.txt";
    private Map<String, Usuario> usuarios;
    private static GerenciadorDeUsuarios instancia;

    private GerenciadorDeUsuarios() {
        usuarios = new HashMap<>();
        carregarUsuarios();
    }

    public static synchronized GerenciadorDeUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorDeUsuarios();
        }
        return instancia;
    }

    private void carregarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Usuario usuario = Usuario.fromString(linha);
                if (usuario != null) {
                    usuarios.put(usuario.getNomeDeUsuario(), usuario);
                }
            }
        } catch (IOException e) {
            System.out.println("Não foi possível carregar os usuários: " + e.getMessage());
        }
    }

    private void salvarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Usuario usuario : usuarios.values()) {
                writer.write(usuario.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Não foi possível salvar os usuários: " + e.getMessage());
        }
    }

    public boolean registrar(String nomeDeUsuario, String senha) {
        if (usuarios.containsKey(nomeDeUsuario)) {
            return false; // Usuário já existe
        }
        Usuario novoUsuario = new Usuario(nomeDeUsuario, senha, false);
        usuarios.put(nomeDeUsuario, novoUsuario);
        salvarUsuarios();
        return true;
    }

    public boolean login(String nomeDeUsuario, String senha) {
        Usuario usuario = usuarios.get(nomeDeUsuario);
        return usuario != null && usuario.getSenha().equals(senha);
    }

    public boolean isAdmin(String nomeDeUsuario) {
        Usuario usuario = usuarios.get(nomeDeUsuario);
        return usuario != null && usuario.isAdmin();
    }

    public boolean modificarUsuarioParaAdmin(String nomeDeUsuario) {
        Usuario usuario = usuarios.get(nomeDeUsuario);
        if (usuario != null) {
            usuario = new Usuario(usuario.getNomeDeUsuario(), usuario.getSenha(), true);
            usuarios.put(nomeDeUsuario, usuario);
            salvarUsuarios();
            return true;
        }
        return false;
    }
}
