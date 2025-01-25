import java.util.ArrayList;
import java.util.List;

public class Conta {
    private static int contadorDeContas = 0;
    private int numero;
    private double saldo;
    private Usuario usuario;
    private String tipo;
    private Agencia agencia;
    private List<String> historicoMovimentacoes;

    public Conta(Usuario usuario, double saldoInicial, String tipo, Agencia agencia) {
        this.numero = ++contadorDeContas;
        this.saldo = saldoInicial;
        this.usuario = usuario;
        this.tipo = tipo;
        this.agencia = agencia;
        this.historicoMovimentacoes = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public List<String> getHistoricoMovimentacoes() {
        return historicoMovimentacoes;
    }

    public boolean sacar(double quantia) {
        if (quantia <= saldo) {
            saldo -= quantia;
            historicoMovimentacoes.add("Saque: R$" + quantia);
            return true;
        }
        return false;
    }

    public boolean transferir(Conta contaDestino, double quantia) {
        if (quantia <= this.saldo) {
            this.saldo -= quantia;
            contaDestino.depositar(quantia);
            historicoMovimentacoes.add("Transferência: R$" + quantia + " para a conta " + contaDestino.getNumero());
            return true;
        }
        return false;
    }

    public void depositar(double quantia) {
        saldo += quantia;
        historicoMovimentacoes.add("Depósito: R$" + quantia);
    }
}
