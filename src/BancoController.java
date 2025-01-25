import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BancoController {
    private Banco banco;
    private BancoView view;
    private Usuario usuarioLogado;
    private Scanner scanner;

    public BancoController(Banco banco, BancoView view) {
        this.banco = banco;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            opcao = view.mostrarMenuPrincipal();
            if (opcao < 0 || opcao > 3) {
                view.mostrarMensagem("Opção inválida! Por favor, escolha novamente.");
                continue;
            }
            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    fazerLogin();
                    break;
                case 3:
                    criarAgencia();
                    break;
                case 0:
                    view.mostrarMensagem("Saindo...");
                    break;
            }
        } while (opcao != 0);
    }

    private void cadastrarUsuario() {
        Usuario usuario = view.lerDadosUsuario();
        if (banco.adicionarUsuario(usuario)) {
            view.mostrarMensagem("Usuário cadastrado com sucesso!");
        } else {
            view.mostrarMensagem("CPF já cadastrado.");
        }
    }

    private void fazerLogin() {
        System.out.print("CPF: ");
        String cpf = scanner.next();
        System.out.print("Senha: ");
        String senha = scanner.next();
        usuarioLogado = banco.autenticarUsuario(cpf, senha);
        if (usuarioLogado != null) {
            view.mostrarMensagem("Login bem-sucedido!");
            menuUsuario();
        } else {
            view.mostrarMensagem("CPF ou senha inválidos!");
        }
    }

    private void criarAgencia() {
        String nomeAgencia = view.lerNomeAgencia();
        if (banco.existeAgencia(nomeAgencia)) {
            view.mostrarMensagem("Agência com esse nome já existe.");
        } else {
            Agencia agencia = new Agencia(nomeAgencia);
            banco.adicionarAgencia(agencia);
            view.mostrarMensagem("Agência criada com sucesso!");
        }
    }

    private void menuUsuario() {
        int opcao;
        do {
            opcao = view.mostrarMenuUsuario();
            if (opcao < 0 || opcao > 6) {
                view.mostrarMensagem("Opção inválida! Por favor, escolha novamente.");
                continue;
            }
            switch (opcao) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    depositar();
                    break;
                case 3:
                    sacar();
                    break;
                case 4:
                    transferir();
                    break;
                case 5:
                    criarConta();
                    break;
                case 6:
                    gerarArquivoCSV();
                    break;
                case 0:
                    view.mostrarMensagem("Saindo...");
                    break;
            }
        } while (opcao != 0);
    }


    private void consultarSaldo() {
        int numeroConta = view.lerNumeroConta("para consulta de saldo");
        Conta conta = banco.buscarContaPorNumero(numeroConta);
        if (conta != null && conta.getUsuario().equals(usuarioLogado)) {
            view.mostrarSaldo(conta.getSaldo());
        } else {
            view.mostrarMensagem("Conta não encontrada ou não pertence ao usuário logado.");
        }
    }

    private void depositar() {
        int numeroConta = view.lerNumeroConta("para depósito");
        Conta conta = banco.buscarContaPorNumero(numeroConta);
        if (conta != null && conta.getUsuario().equals(usuarioLogado)) {
            double quantia = view.lerQuantia("depósito");
            conta.depositar(quantia);
            view.mostrarMensagem("Depósito realizado com sucesso!");
        } else {
            view.mostrarMensagem("Conta não encontrada ou não pertence ao usuário logado.");
        }
    }

    private void sacar() {
        int numeroConta = view.lerNumeroConta("para saque");
        Conta conta = banco.buscarContaPorNumero(numeroConta);
        if (conta != null && conta.getUsuario().equals(usuarioLogado)) {
            double quantia = view.lerQuantia("saque");
            if (conta.sacar(quantia)) {
                view.mostrarMensagem("Saque realizado com sucesso!");
            } else {
                view.mostrarMensagem("Saldo insuficiente ou limite de saques atingido!");
            }
        } else {
            view.mostrarMensagem("Conta não encontrada ou não pertence ao usuário logado.");
        }
    }

    private void transferir() {
        int numeroContaOrigem = view.lerNumeroConta("de origem para transferência");
        Conta contaOrigem = banco.buscarContaPorNumero(numeroContaOrigem);
        if (contaOrigem != null && contaOrigem.getUsuario().equals(usuarioLogado)) {
            int numeroContaDestino = view.lerNumeroConta("de destino para transferência");
            Conta contaDestino = banco.buscarContaPorNumero(numeroContaDestino);
            if (contaDestino != null) {
                double quantia = view.lerQuantia("transferência");
                if (contaOrigem.transferir(contaDestino, quantia)) {
                    view.mostrarMensagem("Transferência realizada com sucesso!");
                } else {
                    view.mostrarMensagem("Saldo insuficiente!");
                }
            } else {
                view.mostrarMensagem("Conta de destino não encontrada.");
            }
        } else {
            view.mostrarMensagem("Conta de origem não encontrada ou não pertence ao usuário logado.");
        }
    }

    private void criarConta() {
        double saldoInicial = view.lerQuantia("saldo inicial");
        String tipo = view.lerTipoConta();
        String nomeAgencia = view.lerNomeAgencia();
        Agencia agencia = banco.buscarAgenciaPorNome(nomeAgencia);

        if (agencia != null) {
            Conta conta;
            switch (tipo.toLowerCase()) {
                case "corrente":
                    double taxaManutencao = view.lerTaxaManutencao();
                    conta = new ContaCorrente(usuarioLogado, saldoInicial, taxaManutencao, agencia);
                    break;
                case "poupanca":
                    double taxaRendimento = view.lerTaxaRendimento();
                    conta = new ContaPoupanca(usuarioLogado, saldoInicial, taxaRendimento, agencia);
                    break;
                case "salario":
                    conta = new ContaSalario(usuarioLogado, saldoInicial, agencia);
                    break;
                default:
                    view.mostrarMensagem("Tipo de conta inválido. Escolha entre: corrente, poupança, ou salário.");
                    return;
            }
            banco.adicionarConta(conta);
            view.mostrarMensagem("Conta criada com sucesso! Número da conta: " + conta.getNumero());
        } else {
            view.mostrarMensagem("Agência não encontrada.");
        }
    }


    private void gerarArquivoCSV() {
        if (usuarioLogado != null) {
            try {

                int arquivoNumero = 1;
                String nomeUsuario = usuarioLogado.getNome().replaceAll("[^a-zA-Z0-9]", "");
                String nomeArquivo = "Historico_" + String.format("%02d", arquivoNumero) + "-" + nomeUsuario + ".txt";

                while (new java.io.File(nomeArquivo).exists()) {
                    arquivoNumero++;
                    nomeArquivo = "Historico_" + String.format("%02d", arquivoNumero) + "-" + nomeUsuario + ".txt";
                }

                // Criar o FileWriter para escrever no arquivo de texto
                FileWriter writer = new FileWriter(nomeArquivo);

                // Escrever cabeçalho no arquivo de texto
                writer.write("Histórico de Movimentações\n");
                writer.write("===================================\n");
                writer.write("Nome: " + usuarioLogado.getNome() + "\n");
                writer.write("CPF: " + usuarioLogado.getCpf() + "\n\n");

                writer.write("Contas Vinculadas:\n");

                // Listar as contas do usuário
                List<Conta> contas = banco.buscarContasPorUsuario(usuarioLogado);
                for (Conta conta : contas) {
                    writer.write("Conta " + conta.getNumero() + " - Tipo: " + conta.getTipo() + "\n");
                    writer.write("Agência: " + conta.getAgencia().getNome() + "\n");

                    // Adicionar movimentações
                    List<String> movimentacoes = conta.getHistoricoMovimentacoes();
                    writer.write("Movimentações:\n");
                    for (String movimentacao : movimentacoes) {
                        writer.write("  - " + movimentacao + "\n");
                    }

                    // Adicionar saldo atual
                    writer.write("Saldo Atual: R$ " + conta.getSaldo() + "\n");
                    writer.write("-----------------------------------\n\n");
                }

                // Fechar o FileWriter
                writer.close();

                // Exibir mensagem de sucesso
                view.mostrarMensagem("Arquivo de texto gerado com sucesso! Nome do arquivo: " + nomeArquivo);

            } catch (IOException e) {
                view.mostrarMensagem("Erro ao gerar arquivo de texto: " + e.getMessage());
            }
        } else {
            view.mostrarMensagem("Usuário não autenticado.");
        }
    }


}
