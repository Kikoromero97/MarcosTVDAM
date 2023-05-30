import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("inicioSesion");
        frame.setContentPane(new inicioSesion().JPanelprincipalInicioSesion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(700,500);
    }
}