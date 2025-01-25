import java.util.*;

public class Banco {
    private Map<String, Usuario> usuarios = new HashMap<>();
    private Map<Integer, Conta> contas = new HashMap<>();
    private Map<String, Agencia> agencias = new HashMap<>();

    public boolean adicionarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getCpf())) {
            return false;
        }
        usuarios.put(usuario.getCpf(), usuario);
        return true;
    }

    public void adicionarConta(Conta conta) {
        contas.put(conta.getNumero(), conta);
    }

    public boolean existeContaComCpf(String cpf) {
        for (Conta conta : contas.values()) {
            if (conta.getUsuario().getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public Usuario autenticarUsuario(String cpf, String senha) {
        Usuario usuario = usuarios.get(cpf);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }

    public Conta buscarContaPorNumero(int numero) {
        return contas.get(numero);
    }

    public List<Conta> buscarContasPorUsuario(Usuario usuario) {
        List<Conta> resultado = new ArrayList<>();
        for (Conta conta : contas.values()) {
            if (conta.getUsuario().equals(usuario)) {
                resultado.add(conta);
            }
        }
        return resultado;
    }

    public boolean validarCpf(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    public void adicionarAgencia(Agencia agencia) {
        agencias.put(agencia.getNome(), agencia);
    }

    public Agencia buscarAgenciaPorNome(String nome) {
        return agencias.get(nome);
    }

    public boolean existeAgencia(String nome) {
        return agencias.containsKey(nome);
    }

    public Collection<Agencia> listarAgencias() {
        return agencias.values();
    }
}
