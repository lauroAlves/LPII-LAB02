public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        BancoView view = new BancoView();
        BancoController controller = new BancoController(banco, view);
        controller.iniciar();
    }
}
