import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Banco {

    private String nome;
    private List<Conta> contas = new ArrayList<>();

    // Abrir conta
    public void abrirConta(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta inválida");
        }

        boolean contaExiste = contas.stream()
                .anyMatch(c -> c.getNumero() == conta.getNumero());

        if (contaExiste) {
            throw new IllegalStateException("Conta já cadastrada no banco");
        }

        contas.add(conta);
    }

    // Encerrar conta (somente se saldo zerado)
    public void encerrarConta(int numeroConta) {
        Conta conta = buscarConta(numeroConta);

        if (conta.getSaldo() != 0) {
            throw new IllegalStateException("Conta não pode ser encerrada com saldo diferente de zero");
        }

        contas.remove(conta);
    }

    // Buscar conta pelo número
    public Conta buscarConta(int numeroConta) {
        return contas.stream()
                .filter(c -> c.getNumero() == numeroConta)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("Conta não encontrada"));
    }

    // Listar contas de um cliente
    public List<Conta> listarContasPorCliente(Cliente cliente) {
        return contas.stream()
                .filter(c -> c.getCliente().equals(cliente))
                .toList();
    }

    // Total de dinheiro sob custódia do banco
    public double calcularTotalEmDepositos() {
        return contas.stream()
                .mapToDouble(Conta::getSaldo)
                .sum();
    }

    // Relatório simples
    public void imprimirRelatorioContas() {
        System.out.println("=== Banco: " + nome + " ===");
        contas.forEach(Conta::imprimirExtrato);
    }
}
