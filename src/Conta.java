import lombok.Data;

@Data
public abstract class Conta implements IConta {

    protected static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    // Regras de negócio
    protected double taxaSaque = 1.50;
    protected double limiteSaqueDiario = 1000.0;
    protected double totalSacadoHoje = 0.0;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    protected void validarValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
    }

    @Override
    public void sacar(double valor) {
        validarValor(valor);

        double valorTotal = valor + taxaSaque;

        if (valorTotal > saldo) {
            throw new IllegalStateException("Saldo insuficiente para saque");
        }

        if (totalSacadoHoje + valor > limiteSaqueDiario) {
            throw new IllegalStateException("Limite diário de saque excedido");
        }

        saldo -= valorTotal;
        totalSacadoHoje += valor;
    }

    @Override
    public void depositar(double valor) {
        validarValor(valor);
        saldo += valor;
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        validarValor(valor);

        if (this == contaDestino) {
            throw new IllegalArgumentException("Não é possível transferir para a mesma conta");
        }

        if (valor < 10.0) {
            throw new IllegalArgumentException("Transferência mínima é de R$ 10,00");
        }

        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    protected void imprimirInfoConta() {
        System.out.println("Agência: " + agencia);
        System.out.println("Número: " + numero);
        System.out.println("Titular: " + cliente.getNome());
        System.out.printf("Saldo: %.2f%n", saldo);
    }

    public abstract void imprimirExtrato();
}
