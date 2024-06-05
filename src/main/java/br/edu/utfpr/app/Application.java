package br.edu.utfpr.app;

import br.edu.utfpr.factory.GerenciadorDeCombos;
import br.edu.utfpr.factory.GerenciadorDeUsuarios;
import br.edu.utfpr.factory.GerenciadorDeProdutos;
import br.edu.utfpr.menu.MenuPrincipal;
import br.edu.utfpr.modelo.Combo;
import br.edu.utfpr.modelo.Produto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {
    public static void main(String[] args) {
        Connection conexao = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lanchonete", "root", "root");
            System.out.println("Conexão estabelecida com sucesso: " + conexao);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (conexao != null) {
            GerenciadorDeUsuarios gerenciadorDeUsuarios = GerenciadorDeUsuarios.getInstancia(conexao);
            GerenciadorDeProdutos gerenciadorDeProdutos = GerenciadorDeProdutos.getInstancia(conexao);
            GerenciadorDeCombos gerenciadorDeCombos = GerenciadorDeCombos.getInstancia(conexao);

            // Exemplo de uso
            Produto produto = gerenciadorDeProdutos.criarProduto(1, "Hamburguer", 10.0);
            Combo combo = gerenciadorDeCombos.configurarCombo("Combo 1", 0.1)
                                             .adicionarItem(produto, 2)
                                             .criarCombo();

            MenuPrincipal menuPrincipal = new MenuPrincipal(conexao);
            menuPrincipal.mostrarMenu();
        }
    }
}
