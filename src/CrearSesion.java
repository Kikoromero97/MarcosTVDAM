import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearSesion extends JFrame{
    private JPanel panelPrincipal;
    private JTextField introUsuario;
    private JLabel logoMarcostv;
    private JPasswordField introContrasenya;
    private JTextField introNif;
    private JComboBox<UserRols> rolComboBox;
    private JButton crearButton;
    private JButton cancelarButton;

    private DBEmpleados db = new DBEmpleados();

    /**
     * Constructor de CrearSesion
     */
    public CrearSesion(){
        super("MARCOS TV");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 500);
        setVisible(true);

        for (UserRols rol : UserRols.values()){
            rolComboBox.addItem(rol);
        }

        rolComboBox.setSelectedItem(UserRols.rrhh);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InicioSesion.mostrarInicioSession();
                dispose();
            }
        });

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(createSesion()){
                    menuPrincipal.mostrarMenuPrincipal();
                }
            }
        });
    }

    /**
     * Función que se encarga de registrar una sesion nueva
     *
     * @return boolean True si lo registra y False si no lo registra.
     */
    private boolean createSesion(){
        String nif = introNif.getText();
        String nombre = introUsuario.getText();
        String contrasenya = introContrasenya.getText();
        String rol = rolComboBox.getSelectedItem().toString();

        int respuesta = db.procedureNewSesion(nif, nombre, contrasenya, rol);

        switch (respuesta){
            case 0 -> JOptionPane.showMessageDialog(null, "EL empleado ya tiene un usuario", "MARCOS TV", JOptionPane.ERROR_MESSAGE);
            case 1 -> {
                JOptionPane.showMessageDialog(null, "EL usuario se ha creado", "MARCOS TV", JOptionPane.INFORMATION_MESSAGE);
                UserManager.newUser(nombre, contrasenya, rol);
                return true;
            }
            case -1 -> JOptionPane.showMessageDialog(null, "EL empleado no está registrado", "MARCOS TV", JOptionPane.ERROR_MESSAGE);
            case -2 -> JOptionPane.showMessageDialog(null, "ERROR CON LA BASE DE DATOS", "MARCOS TV", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    /**
     * Funcion que ejecuta la pantalla CrearSesion
     */
    public static void mostrarCrearSesion(){
        JFrame frame = new CrearSesion();
    }
}
