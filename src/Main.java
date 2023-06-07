import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        if (UserManager.hasSession()){
            UserManager.loadSession();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new menuPrincipal();
                }
            });
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    //JFrame frame = new inicioSesion();
                }
            });
        }
    }
}