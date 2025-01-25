
import java.util.Scanner;

public class BancoView {
    private Scanner scanner;

    public BancoView() {
        scanner = new Scanner(System.in);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Fazer Login");
        System.out.println("3. Criar Agência");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    public int mostrarMenuUsuario() {
        System.out.println("\n--- Menu do Usuário ---");
        System.out.println("1. Consultar Saldo");
        System.out.println("2. Depositar");
        System.out.println("3. Sacar");
        System.out.println("4. Transferir");
        System.out.println("5. Criar Conta");
        System.out.println("6. Gerar CSV de Contas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    public Usuario lerDadosUsuario() {
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        return new Usuario(nome, cpf, senha);
    }

    public double lerQuantia(String operacao) {
        System.out.print("Quantia para " + operacao + ": ");
        return scanner.nextDouble();
    }

    public int lerNumeroConta(String operacao) {
        System.out.print("Número da conta " + operacao + ": ");
        return scanner.nextInt();
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void mostrarSaldo(double saldo) {
        System.out.println("Saldo atual: R$" + saldo);
    }

    public String lerTipoConta() {
        System.out.print("Tipo da conta (corrente/poupanca/salario): ");
        return scanner.next();
    }

    public String lerNomeAgencia() {
        scanner.nextLine(); // Limpar o buffer
        System.out.print("Nome da agência: ");
        return scanner.nextLine();
    }

    public String lerNomeNovaAgencia() {
        scanner.nextLine();
        System.out.print("Digite o nome da nova agência: ");
        return scanner.nextLine();
    }


    public double lerTaxaManutencao() {
        System.out.print("Digite a taxa de manutenção para a conta corrente: ");
        return scanner.nextDouble();
    }

    // Novo método para ler a taxa de rendimento ao criar uma conta poupança
    public double lerTaxaRendimento() {
        System.out.print("Digite a taxa de rendimento para a conta poupança: ");
        return scanner.nextDouble();
    }

    // Novo método para gerar o arquivo CSV com as contas

}
