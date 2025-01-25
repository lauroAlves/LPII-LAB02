import java.util.ArrayList;

public class Agencia {
    private String nome;
    private ArrayList<Conta> contas;

    public Agencia(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    @Override
    public String toString() {
        return nome;
    }
}
