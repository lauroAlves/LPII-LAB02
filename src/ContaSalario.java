public class ContaSalario extends Conta {
    private static final int LIMITE_SAQUES = 3;
    private int saquesRealizados = 0;

    public ContaSalario(Usuario usuario, double saldoInicial, Agencia agencia) {
        super(usuario, saldoInicial, "salario", agencia);
    }

    @Override
    public boolean sacar(double quantia) {
        if (saquesRealizados >= LIMITE_SAQUES) {
            System.out.println("Limite de saques atingido para esta conta.");
            return false;
        }
        if (super.sacar(quantia)) {
            saquesRealizados++;
            System.out.println("Saque realizado. Saques restantes: " + (LIMITE_SAQUES - saquesRealizados));
            return true;
        }
        return false;
    }

    @Override
    public void depositar(double quantia) {
        if (quantia > 0) {
            super.depositar(quantia);
            System.out.println("Depósito de R$" + quantia + " realizado com sucesso!");
        } else {
            System.out.println("Depósito inválido.");
        }
    }

    public int getSaquesRealizados() {
        return saquesRealizados;
    }
}
