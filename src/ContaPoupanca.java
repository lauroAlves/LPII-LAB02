public class ContaPoupanca extends Conta {
    private double rendimento;

    public ContaPoupanca(Usuario usuario, double saldoInicial, double rendimento, Agencia agencia) {
        super(usuario, saldoInicial, "poupanca", agencia);
        this.rendimento = rendimento;
    }


    public void aplicarRendimento() {
        double rendimentoAplicado = getSaldo() * rendimento;
        depositar(rendimentoAplicado); // Adiciona o rendimento ao saldo
        System.out.println("Rendimento de R$" + rendimentoAplicado + " aplicado. Saldo atual: R$" + getSaldo());
    }

    public double getRendimento() {
        return rendimento;
    }
}
