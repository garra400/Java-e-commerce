package br.edu.utfpr.menu;
import br.edu.utfpr.modelo.*;
import br.edu.utfpr.factory.*;
import br.edu.utfpr.movimentacoes.*;
import br.edu.utfpr.payment.*;

public class MenuUsuario extends Menu {
    private GerenciadorDeUsuarios gerenciadorDeUsuarios;
    private Estoque estoque = Estoque.getInstancia();
    private Usuario usuarioAtual;
    private Carrinho carrinhoAtual = new Carrinho();

    public MenuUsuario(GerenciadorDeUsuarios gerenciadorDeUsuarios, Usuario usuarioAtual) {
        this.gerenciadorDeUsuarios = gerenciadorDeUsuarios;
        this.usuarioAtual = usuarioAtual;
    }

    @Override
    public void mostrarMenu() {
        while (true) {
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
                    System.out.println("Digite o ID do produto: ");
                    int idProduto = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    Produto produto = null;

                    for (Produto p : estoque.getProdutos().keySet()) {
                        if (p.getId() == idProduto) {
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
                    System.out.println("Digite o ID do produto: ");
                    idProduto = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    produto = null;

                    for (Produto p : carrinhoAtual.getItens()) {
                        if (p.getId() == idProduto) {
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
                    Pagamento pagamento;
                    String numeroChave;
                    System.out.println("Escolha a forma de Pagamento:");
                    System.out.println("1. Cartão de Crédito");
                    System.out.println("2. Cartão de Débito");
                    System.out.println("3. Pix");
                    System.out.println("4. Carteira de criptomoedas");
                    int formaPagamento = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha

                    switch (formaPagamento) {
                        case 1:
                            System.out.println("Digite o número do cartão de crédito: ");
                            numeroChave = scanner.nextLine();
                            pagamento = new CartaoCredito(carrinhoAtual.getTotal(), numeroChave);
                            break;
                        case 2:
                            System.out.println("Digite o número do cartão de débito: ");
                            numeroChave = scanner.nextLine();
                            pagamento = new CartaoDebito(carrinhoAtual.getTotal(), numeroChave);
                            break;
                        case 3:
                            System.out.println("Digite a sua chave Pix: ");
                            numeroChave = scanner.nextLine();
                            pagamento = new Pix(carrinhoAtual.getTotal(), numeroChave);
                            break;
                        case 4:
                            System.out.println("Digite o endereço da sua carteira: ");
                            numeroChave = scanner.nextLine();
                            pagamento = new Wallet(carrinhoAtual.getTotal(), numeroChave);
                            break;
                        default:
                            System.out.println("Forma de pagamento inválida");
                            continue;
                    }
                    pagamento.realizarPagamento(); // Chama o método realizarPagamento sem passar argumentos
                    System.out.println("Pedido realizado com sucesso!");
                    carrinhoAtual = new Carrinho();
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
