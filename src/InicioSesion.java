import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    private DBEmpleados db = new DBEmpleados();

    /**
     * Constructor de InicioSesion.
     */
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
                if(checkSession()){
                    JOptionPane.showMessageDialog(null, "Sesión iniciada correctamente", "MARCOS TV", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre o Contraseña incorrecto", "MARCOS TV", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Función que comprueba la sesión.
     *
     * @return boolean True si existe la sesión y la contraseña está bien, False en el caso contrario.
     */
    private boolean checkSession() {
        String usuario = introUsuario.getText();
        String pass = introContrasenya.getText();

        try{
            ResultSet rs = db.verSesiones();

            while (rs.next()){
                String nombre = rs.getString("nombre");
                String contrasenya = rs.getString("contrasenya");
                if (usuario.equals(nombre) && pass.equals(contrasenya)){
                    String rol = rs.getString("rol");
                    UserManager.newUser(nombre, contrasenya, rol);
                    return true;
                }
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Función que ejecuta la pantalla InicioSesion.
     */
    public static void mostrarInicioSession() {
        JFrame frame = new InicioSesion();
    }
}
