import javax.swing.*;
import java.awt.*;

public class inicioSesion extends JFrame {
    public JPanel JPanelprincipalInicioSesion;
    private JPanel JPanelRegistro;
    private JPanel JPanelInicioSesion;
    private JButton registrarseButton;
    private JPanel cuadroInterior;
    private JTextField introUsuario;
    private JTextField textField1;
    private JLabel logoMarcostv;
    private JButton iniciarSesionButton;

    public inicioSesion(){
        super("MARCOS TV");
        setSize(700, 500);
        setVisible(true);
        setContentPane(JPanelprincipalInicioSesion);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public static void mostrarInicioSession() {
        JFrame frame = new inicioSesion();
    }
}
