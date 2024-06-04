/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.menu;
import br.edu.utfpr.modelo.*;
import java.util.Map;
import br.edu.utfpr.factory.*;
import br.edu.utfpr.menu.*;
/**
 *
 * @author Garra pc
 */
public class MenuAdmin extends Menu {
    private GerenciadorDeUsuarios gerenciadorDeUsuarios;
    private Estoque estoque = Estoque.getInstancia();
    private Usuario usuarioAtual;

    public MenuAdmin(GerenciadorDeUsuarios gerenciadorDeUsuarios, Usuario usuarioAtual) {
        this.gerenciadorDeUsuarios = gerenciadorDeUsuarios;
        this.usuarioAtual = usuarioAtual;
    }

    @Override
    public void mostrarMenu() {
        while (true) {
            System.out.println("1. Modificar usuário para administrador");
            System.out.println("2. Adicionar produto ao estoque");
            System.out.println("3. Criar combo");
            System.out.println("4. Ver estoque");
            System.out.println("5. Logout");
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
                    System.out.println("Digite o tipo do produto (1. Lanche, 2. Pizza, 3. Bebida): ");
                    int tipoProduto = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha

                    Produto produto = GerenciadorDeProdutos.criarProduto(tipoProduto, nomeProduto, precoProduto);
                    estoque.adicionarProduto(produto, quantidadeProduto);
                    System.out.println("Produto adicionado ao estoque com sucesso!");
                    break;

                case 3:
                    System.out.println("Digite o nome do combo: ");
                    String nomeCombo = scanner.nextLine();
                    System.out.println("Digite a porcentagem de desconto do combo: ");
                    double descontoCombo = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a nova linha

                    GerenciadorDeCombos comboBuilder = new GerenciadorDeCombos(nomeCombo, descontoCombo);

                    while (true) {
                        System.out.println("Produtos disponíveis:");
                        for (Map.Entry<Produto, Integer> entry : estoque.getProdutos().entrySet()) {
                            Produto p = entry.getKey();
                            int quantidade = entry.getValue();
                            System.out.println(p + " - Quantidade em estoque: " + quantidade);
                        }
                        System.out.println("Digite o ID do produto para adicionar ao combo (ou 'sair' para finalizar): ");
                        int idProdutoCombo = scanner.nextInt();
                        if (idProdutoCombo == 0) {
                            break;
                        }

                        Produto produtoCombo = null;
                        for (Produto p : estoque.getProdutos().keySet()) {
                            if (p.getId() == idProdutoCombo) {
                                produtoCombo = p;
                                break;
                            }
                        }

                        if (produtoCombo == null) {
                            System.out.println("Produto não encontrado.");
                            continue;
                        }

                        System.out.println("Digite a quantidade do produto para o combo: ");
                        int quantidadeProdutoCombo = scanner.nextInt();
                        scanner.nextLine();  // Consumir a nova linha

                        comboBuilder.adicionarItem(produtoCombo, quantidadeProdutoCombo);
                        System.out.println("Produto adicionado ao combo.");
                    }

                    Combo combo = comboBuilder.build();
                    estoque.adicionarProduto(combo, 1);
                    System.out.println("Combo criado com sucesso!");
                    break;

                case 4:
                    System.out.println("Estoque atual:");
                    for (Map.Entry<Produto, Integer> entry : estoque.getProdutos().entrySet()) {
                        Produto p = entry.getKey();
                        int quantidade = entry.getValue();
                        System.out.println(p + " - Quantidade: " + quantidade);
                    }
                    break;

                case 5:
                    System.out.println("Logout bem-sucedido!");
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
