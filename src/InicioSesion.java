import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioSesion extends JFrame {
    public JPanel JPanelprincipalInicioSesion;
    private JPanel JPanelRegistro;
    private JPanel JPanelInicioSesion;
    private JButton registrarseButton;
    private JPanel cuadroInterior;
    private JTextField introUsuario;
    private JLabel logoMarcostv;
    private JButton iniciarSesionButton;
    private JPasswordField introContrasenya;

    public InicioSesion(){
        super("MARCOS TV");
        setSize(700, 500);
        setVisible(true);
        setContentPane(JPanelprincipalInicioSesion);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearSesion.mostrarCrearSesion();
                dispose();
            }
        });
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkSession();
            }
        });
    }

    private boolean checkSession() {
        String usuario = introUsuario.getText();
        String contrasenya = introContrasenya.getText();

        // Comprobar con la BD

        // Pasar a menuPrincipal o dar error

        // Si pasa tiene que crear el usuario en cache.

        return true;
    }

    public static void mostrarInicioSession() {
        JFrame frame = new InicioSesion();
    }
}
