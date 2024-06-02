/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.app;
import java.util.Scanner;
import br.edu.utfpr.modelo.*;
import br.edu.utfpr.movimentacoes.*;
import br.edu.utfpr.payment.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {
    public static void main(String[] args) {
        int formaPagamento = 0;
        Scanner scanner = new Scanner(System.in);
        GerenciadorDeUsuarios gerenciadorDeUsuarios = new GerenciadorDeUsuarios();
        Estoque estoque = new Estoque();
        Usuario usuarioAtual = null;
        Carrinho carrinhoAtual = new Carrinho();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lanchonete","root","root");
            System.out.println("Conexão estabelecida com sucesso: " + con);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            if (usuarioAtual == null) {
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
                        } else {
                            System.out.println("Nome de usuário ou senha inválidos.");
                        }
                        break;

                    case 3:
                        System.out.println("Saindo...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                if (usuarioAtual.isAdmin()) {
                    System.out.println("1. Modificar usuário para administrador");
                    System.out.println("2. Adicionar produto ao estoque");
                    System.out.println("3. Ver estoque");
                    System.out.println("4. Logout");
                    System.out.println("Escolha uma opção: ");
                    int escolha = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha

                    switch (escolha) {
                        case 1:
                            System.out.println("Digite o nome de usuário para tornar administrador: ");
                            String nomeDeUsuario = scanner.nextLine();

                            if (gerenciadorDeUsuarios.modificarUsuarioParaAdmin(nomeDeUsuario)) {
                                System.out.println("Usuário modificado para administrador com sucesso!");
                            } else {
                                System.out.println("Usuário não encontrado.");
                            }
                            break;

                        case 2:
                            System.out.println("Digite o nome do produto: ");
                            String nomeProduto = scanner.nextLine();
                            System.out.println("Digite o preço do produto: ");
                            double precoProduto = scanner.nextDouble();
                            scanner.nextLine();  // Consumir a nova linha
                            System.out.println("Digite a quantidade do produto: ");
                            int quantidadeProduto = scanner.nextInt();
                            scanner.nextLine();  // Consumir a nova linha
                            System.out.println("Digite o tipo do produto (1. Lanche, 2. Pizza, 3. Bebida, 4. Combo): ");
                            int tipoProduto = scanner.nextInt();
                            scanner.nextLine();  // Consumir a nova linha

                            Produto produto;
                            switch (tipoProduto) {
                                case 1:
                                    produto = new Lanche(nomeProduto, precoProduto);
                                    break;
                                case 2:
                                    produto = new Pizza(nomeProduto, precoProduto);
                                    break;
                                case 3:
                                    produto = new Bebida(nomeProduto, precoProduto);
                                    break;
                                case 4:
                                    produto = new Combo(nomeProduto, precoProduto, new Produto[]{});
                                    break;
                                default:
                                    System.out.println("Tipo de produto inválido.");
                                    continue;
                            }

                            estoque.adicionarProduto(produto, quantidadeProduto);
                            System.out.println("Produto adicionado ao estoque com sucesso!");
                            break;

                        case 3:
                            System.out.println("Estoque atual:");
                            for (Produto p : estoque.getProdutos().keySet()) {
                                System.out.println(p + " - Quantidade: " + estoque.getQuantidade(p));
                            }
                            break;

                        case 4:
                            System.out.println("Logout bem-sucedido!");
                            usuarioAtual = null;
                            break;

                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                } else {
                    System.out.println("1. Adicionar item ao carrinho");
                    System.out.println("2. Remover item do carrinho");
                    System.out.println("3. Ver carrinho");
                    System.out.println("4. Finalizar pedido");
                    System.out.println("5. Logout");
                    System.out.println("Escolha uma opção: ");
                    int escolha = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha

                    switch (escolha) {
                        case 1:
                            System.out.println("Digite o nome do produto: ");
                            String nomeProduto = scanner.nextLine();
                            Produto produto = null;

                            for (Produto p : estoque.getProdutos().keySet()) {
                                if (p.getNome().equals(nomeProduto)) {
                                    produto = p;
                                    break;
                                }
                            }

                            if (produto != null) {
                                carrinhoAtual.adicionarItem(produto);
                                System.out.println("Produto adicionado ao carrinho.");
                            } else {
                                System.out.println("Produto não encontrado.");
                            }
                            break;

                        case 2:
                            System.out.println("Digite o nome do produto: ");
                            nomeProduto = scanner.nextLine();
                            produto = null;

                            for (Produto p : carrinhoAtual.getItens()) {
                                if (p.getNome().equals(nomeProduto)) {
                                    produto = p;
                                    break;
                                }
                            }

                            if (produto != null) {
                                carrinhoAtual.removerItem(produto);
                                System.out.println("Produto removido do carrinho.");
                            } else {
                                System.out.println("Produto não encontrado no carrinho.");
                            }
                            break;

                        case 3:
                            System.out.println(carrinhoAtual);
                            break;

                        case 4:
                            Pedido pedido = new Pedido(usuarioAtual, carrinhoAtual);
                            Pagamento pagamento;
                            String numeroChave;
                            System.out.println("Escolha a forma de Pagamento:");
                            System.out.println("1. Cartão de Crédito");
                            System.out.println("2. Cartão de Débito");
                            System.out.println("3. Pix");
                            System.out.println("4. Carteira de criptomoedas");
                            formaPagamento = scanner.nextInt();
                            switch(formaPagamento){
                                case 1:
                                    System.out.println("Digite o número do cartão de crédito: ");
                                    numeroChave = scanner.nextLine();//Duplicado devido a algum bug que faz pular essa linha
                                    numeroChave = scanner.nextLine();
                                    pagamento = new CartaoCredito(carrinhoAtual.getTotal(), numeroChave);
                                    System.out.println("Pedido realizado com sucesso!");
                                    System.out.println(pedido);
                                    System.out.println(pagamento);
                                    carrinhoAtual = new Carrinho();
                                    break;
                                case 2:
                                    System.out.println("Digite o número do cartão de débito: ");
                                    numeroChave = scanner.nextLine();//Duplicado devido a algum bug que faz pular essa linha
                                    numeroChave = scanner.nextLine();
                                    pagamento = new CartaoDebito(carrinhoAtual.getTotal(), numeroChave);
                                    System.out.println("Pedido realizado com sucesso!");
                                    System.out.println(pedido);
                                    System.out.println(pagamento);
                                    carrinhoAtual = new Carrinho();
                                    break;
                                case 3:
                                    System.out.println("Digite a sua chave Pix: ");
                                    numeroChave = scanner.nextLine();//Duplicado devido a algum bug que faz pular essa linha
                                    numeroChave = scanner.nextLine();
                                    pagamento = new Pix(carrinhoAtual.getTotal(), numeroChave);
                                    System.out.println("Pedido realizado com sucesso!");
                                    System.out.println(pedido);
                                    System.out.println(pagamento);
                                    carrinhoAtual = new Carrinho();
                                    break;
                                case 4:
                                    System.out.println("Digite o endereço da sua carteira: ");
                                    numeroChave = scanner.nextLine();//Duplicado devido a algum bug que faz pular essa linha
                                    numeroChave = scanner.nextLine();
                                    pagamento = new Wallet(carrinhoAtual.getTotal(), numeroChave);
                                    System.out.println("Pedido realizado com sucesso!");
                                    System.out.println(pedido);
                                    System.out.println(pagamento);
                                    carrinhoAtual = new Carrinho();
                                    break;
                                default:
                                    System.out.println("Forma de pagamento inválida");
                                    break;
                            }
                            break;

                        case 5:
                            System.out.println("Logout bem-sucedido!");
                            usuarioAtual = null;
                            break;

                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                }
            }
        }
    }
}