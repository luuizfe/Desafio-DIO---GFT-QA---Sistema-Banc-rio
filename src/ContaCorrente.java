public class ContaCorrente extends Conta {

    private double limiteChequeEspecial = 500.0;
    private double taxaTransferencia = 0.50;
    private double jurosChequeEspecial = 0.02; // 2%

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void sacar(double valor) {
        validarValor(valor);

        double valorTotal = valor + taxaSaque;
        double saldoDisponivel = saldo + limiteChequeEspecial;

        if (valorTotal > saldoDisponivel) {
            throw new IllegalStateException("Limite do cheque especial excedido");
        }

        saldo -= valorTotal;
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        validarValor(valor);

        double valorTotal = valor + taxaTransferencia;
        double saldoDisponivel = saldo + limiteChequeEspecial;

        if (valorTotal > saldoDisponivel) {
            throw new IllegalStateException("Saldo + cheque especial insuficiente");
        }

        saldo -= valorTotal;
        contaDestino.depositar(valor);
    }

    // Simulação de fechamento mensal
    public void cobrarMensalidade(double valorMensalidade) {
        saldo -= valorMensalidade;
    }

    // Aplicar juros se estiver usando cheque especial
    public void aplicarJurosChequeEspecial() {
        if (saldo < 0) {
            saldo += saldo * jurosChequeEspecial; // saldo é negativo
        }
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("==== Extrato Conta Corrente ====");
        super.imprimirInfoConta();
        System.out.printf("Limite Cheque Especial: %.2f%n", limiteChequeEspecial);
    }
}
