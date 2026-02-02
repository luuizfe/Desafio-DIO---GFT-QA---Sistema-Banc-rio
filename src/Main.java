public class Main {

    public static void main(String[] args) {

        // =========================
        // 1. Criação do cliente
        // =========================
        Cliente luiz = new Cliente("Luiz");

        // =========================
        // 2. Criação das contas
        // =========================
        Conta contaCorrente = new ContaCorrente(luiz);
        Conta contaPoupanca = new ContaPoupanca(luiz);

        // =========================
        // 3. Criação do banco
        // =========================
        Banco banco = new Banco();
        banco.setNome("Banco Digital Java");

        // =========================
        // 4. Abertura de contas no banco
        // =========================
        banco.abrirConta(contaCorrente);
        banco.abrirConta(contaPoupanca);

        // =========================
        // 5. Depósitos iniciais
        // =========================
        contaCorrente.depositar(500);
        contaPoupanca.depositar(500);

        // =========================
        // 6. Saque na conta corrente (com taxa)
        // =========================
        contaCorrente.sacar(100);

        // =========================
        // 7. Transferência da corrente para poupança
        // =========================
        contaCorrente.transferir(200, contaPoupanca);

        // =========================
        // 8. Tentativa de transferência inválida (poupança)
        // =========================
        try {
            contaPoupanca.transferir(1400, contaCorrente);
        } catch (IllegalStateException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        // =========================
        // 9. Aplicar rendimento mensal da poupança
        // =========================
        ((ContaPoupanca) contaPoupanca).aplicarRendimentoMensal();

        // =========================
        // 10. Cobrança de mensalidade da conta corrente
        // =========================
        ((ContaCorrente) contaCorrente).cobrarMensalidade(20);

        // =========================
        // 11. Aplicar juros do cheque especial (se houver)
        // =========================
        ((ContaCorrente) contaCorrente).aplicarJurosChequeEspecial();

        // =========================
        // 12. Impressão dos extratos
        // =========================
        contaCorrente.imprimirExtrato();
        contaPoupanca.imprimirExtrato();

        // =========================
        // 13. Relatório geral do banco
        // =========================
        banco.imprimirRelatorioContas();

        // =========================
        // 14. Total de dinheiro no banco
        // =========================
        double total = banco.calcularTotalEmDepositos();
        System.out.printf("Total em depósitos no banco: %.2f%n", total);
    }
}
