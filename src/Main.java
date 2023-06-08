public class Main {
    public static void main(String[] args) {
        if (UserManager.hasSession()){
            UserManager.loadSession();
            menuPrincipal.mostrarMenuPrincipal();
        } else {
            InicioSesion.mostrarInicioSession();
        }
    }
}