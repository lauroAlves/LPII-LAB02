public class ContaCorrente extends Conta {
    private double taxaManutencao;

    public ContaCorrente(Usuario usuario, double saldoInicial, double taxaManutencao, Agencia agencia) {
        super(usuario, saldoInicial, "corrente", agencia);
        this.taxaManutencao = taxaManutencao;
    }


    public void aplicarTaxa() {
        if (getSaldo() >= taxaManutencao) {
            double novoSaldo = getSaldo() - taxaManutencao;
            System.out.println("Aplicada taxa de manutenção de R$" + taxaManutencao);
            depositar(-taxaManutencao);
            System.out.println("Saldo após taxa: R$" + novoSaldo);
        } else {
            System.out.println("Saldo insuficiente para aplicar a taxa de manutenção.");
        }
    }

    public double getTaxaManutencao() {
        return taxaManutencao;
    }
}
