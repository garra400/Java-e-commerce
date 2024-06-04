package br.edu.utfpr.payment;

public class Pagamento {
    private double valor;
    private String metodo;

    public Pagamento(double valor, String metodo) {
        this.valor = valor;
        this.metodo = metodo;
    }

    public double getValor() {
        return valor;
    }

    public String getMetodo() {
        return metodo;
    }

    @Override
    public String toString() {
        return "Pagamento de R$ " + valor + " via " + metodo;
    }

    // Método para realizar o pagamento
    public void realizarPagamento() {
        // Lógica para realizar o pagamento aqui
        System.out.println("Pagamento de R$ " + valor + " realizado via " + metodo);
    }
}
