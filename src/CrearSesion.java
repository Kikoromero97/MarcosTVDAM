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
                createSesion();
            }
        });
    }

    private boolean createSesion(){
        introNif.getText();
        introUsuario.getText();
        introContrasenya.getText();
        rolComboBox.getSelectedItem();
        return true;
    }

    public static void main(String[] args) {
        mostrarCrearSesion();
    }

    public static void mostrarCrearSesion(){
        JFrame frame = new CrearSesion();
    }
}
