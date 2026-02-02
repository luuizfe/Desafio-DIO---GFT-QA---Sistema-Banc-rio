public class ContaPoupanca extends Conta {

    private static final int LIMITE_SAQUES_MES = 3;
    private static final double TAXA_RENDIMENTO_MENSAL = 0.005; // 0,5%

    private int saquesRealizadosNoMes = 0;

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void sacar(double valor) {
        validarValor(valor);

        if (saquesRealizadosNoMes >= LIMITE_SAQUES_MES) {
            throw new IllegalStateException("Limite mensal de saques excedido");
        }

        if (valor > saldo) {
            throw new IllegalStateException("Saldo insuficiente");
        }

        saldo -= valor;
        saquesRealizadosNoMes++;
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        validarValor(valor);

        if (valor > saldo) {
            throw new IllegalStateException("Saldo insuficiente para transferência");
        }

        saldo -= valor;
        contaDestino.depositar(valor);
    }

    // Simula o rendimento mensal da poupança
    public void aplicarRendimentoMensal() {
        if (saldo > 0) {
            saldo += saldo * TAXA_RENDIMENTO_MENSAL;
        }
    }

    // Simula virada de mês
    public void resetarSaquesMensais() {
        saquesRealizadosNoMes = 0;
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("==== Extrato Conta Poupança ====");
        super.imprimirInfoConta();
        System.out.println("Saques no mês: " + saquesRealizadosNoMes);
    }
}
