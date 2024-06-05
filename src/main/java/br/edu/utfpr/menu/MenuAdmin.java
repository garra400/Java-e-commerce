package br.edu.utfpr.menu;

import br.edu.utfpr.factory.*;
import br.edu.utfpr.modelo.*;
import java.util.Map;
import java.sql.Connection;
import java.util.Scanner;

public class MenuAdmin extends Menu {
    private GerenciadorDeUsuarios gerenciadorDeUsuarios;
    private GerenciadorDeProdutos gerenciadorDeProdutos;
    private Usuario usuarioAtual;
    private Connection conexao;

    public MenuAdmin(GerenciadorDeUsuarios gerenciadorDeUsuarios, GerenciadorDeProdutos gerenciadorDeProdutos, Usuario usuarioAtual) {
        this.gerenciadorDeUsuarios = gerenciadorDeUsuarios;
        this.gerenciadorDeProdutos = gerenciadorDeProdutos;
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

                    Produto produto = gerenciadorDeProdutos.criarProduto(tipoProduto, nomeProduto, precoProduto);
                    gerenciadorDeProdutos.adicionarProduto(produto, quantidadeProduto);
                    System.out.println("Produto adicionado ao estoque com sucesso!");
                    break;
                    
                case 3:
                    System.out.println("Digite o nome do combo: ");
                     String nomeCombo = scanner.nextLine();
                    System.out.println("Digite o desconto do combo (em %): ");
                    double descontoCombo = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a nova linha

                    GerenciadorDeCombos gerenciadorDeCombos = GerenciadorDeCombos.getInstancia(conexao);
                    gerenciadorDeCombos.configurarCombo(nomeCombo, descontoCombo);

                    boolean adicionandoItens = true;
                    while (adicionandoItens) {
                        System.out.println("Digite o nome do produto do combo (ou 'fim' para parar): ");
                        String nomeProdutoCombo = scanner.nextLine();
                        if (nomeProdutoCombo.equalsIgnoreCase("fim")) {
                            adicionandoItens = false;
                        break;
                    }
                    Produto produtoCombo = null;
                    if (produtoCombo == null) {
                        System.out.println("Produto não encontrado. Por favor, adicione o produto ao estoque primeiro.");
                    continue;
                    }

                    System.out.println("Digite a quantidade do produto no combo: ");
                    int quantidadeProdutoCombo = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha

                    gerenciadorDeCombos.adicionarItem(produtoCombo, quantidadeProdutoCombo);
                    }

                    Combo comboCriado = gerenciadorDeCombos.criarCombo();
                    if (comboCriado != null) {
                        System.out.println("Combo criado com sucesso!");
                    } else {
                    System.out.println("Erro ao criar combo. Verifique os dados e tente novamente.");
                    }
                    break;
             

                case 4:
                    System.out.println("Estoque atual:");
                    Map<Produto, Integer> produtos = gerenciadorDeProdutos.getProdutos();
                    for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {
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
