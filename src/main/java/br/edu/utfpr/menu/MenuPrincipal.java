/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.menu;
import br.edu.utfpr.modelo.*;
import br.edu.utfpr.factory.*;
import br.edu.utfpr.menu.*;
/**
 *
 * @author Garra pc
 */
public class MenuPrincipal extends Menu {
    private GerenciadorDeUsuarios gerenciadorDeUsuarios;
    private Usuario usuarioAtual;

    public MenuPrincipal(GerenciadorDeUsuarios gerenciadorDeUsuarios) {
        this.gerenciadorDeUsuarios = gerenciadorDeUsuarios;
    }

    @Override
    public void mostrarMenu() {
        while (usuarioAtual == null) {
            System.out.println("1. Registrar");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            System.out.println("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (escolha) {
                case 1:
                    System.out.println("Digite o nome de usuário: ");
                    String nomeDeUsuario = scanner.nextLine();
                    System.out.println("Digite a senha: ");
                    String senha = scanner.nextLine();

                    if (gerenciadorDeUsuarios.registrar(nomeDeUsuario, senha)) {
                        System.out.println("Registro bem-sucedido!");
                    } else {
                        System.out.println("Nome de usuário já existe.");
                    }
                    break;

                case 2:
                    System.out.println("Digite o nome de usuário: ");
                    nomeDeUsuario = scanner.nextLine();
                    System.out.println("Digite a senha: ");
                    senha = scanner.nextLine();

                    if (gerenciadorDeUsuarios.login(nomeDeUsuario, senha)) {
                        System.out.println("Login bem-sucedido!");
                        usuarioAtual = new Usuario(nomeDeUsuario, senha, gerenciadorDeUsuarios.isAdmin(nomeDeUsuario));
                        if (usuarioAtual.isAdmin()) {
                            new MenuAdmin(gerenciadorDeUsuarios, usuarioAtual).mostrarMenu();
                        } else {
                            new MenuUsuario(gerenciadorDeUsuarios, usuarioAtual).mostrarMenu();
                        }
                    } else {
                        System.out.println("Nome de usuário ou senha inválidos.");
                    }
                    break;

                case 3:
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
