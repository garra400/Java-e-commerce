package br.edu.utfpr.app;

import br.edu.utfpr.factory.GerenciadorDeUsuarios;
import br.edu.utfpr.menu.*;

public class Application {
    public static void main(String[] args) {
        GerenciadorDeUsuarios gerenciadorDeUsuarios = GerenciadorDeUsuarios.getInstancia();
        MenuPrincipal menuPrincipal = new MenuPrincipal(gerenciadorDeUsuarios);
        menuPrincipal.mostrarMenu();
    }
}