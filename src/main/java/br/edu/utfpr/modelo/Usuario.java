package br.edu.utfpr.modelo;

public class Usuario {
    private static int nextId = 1;
    private int id;
    private String nomeDeUsuario;
    private String senha;
    private boolean isAdmin;

    // Construtor para criar um novo usuário com ID auto incrementado
    public Usuario(String nomeDeUsuario, String senha, boolean isAdmin) {
        this.id = nextId++;
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.isAdmin = isAdmin;
    }

    // Construtor para criar um usuário com um ID específico (usado na desserialização)
    public Usuario(int id, String nomeDeUsuario, String senha, boolean isAdmin) {
        this.id = id;
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.isAdmin = isAdmin;
        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    public int getId() {
        return id;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return id + "," + nomeDeUsuario + "," + senha + "," + (isAdmin ? 1 : 0);
    }

    public static Usuario fromString(String dadosDoUsuario) {
        String[] dados = dadosDoUsuario.split(",");
        if (dados.length == 4) {
            return new Usuario(Integer.parseInt(dados[0]), dados[1], dados[2], "1".equals(dados[3]));
        }
        return null;
    }
}