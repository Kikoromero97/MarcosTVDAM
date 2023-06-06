import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerDepartamento {
    private JPanel panelPrincipal;
    private JLabel logoMARCOSTV;
    private JComboBox comboBoxDepartamentos;
    private JTextField txtFldCodigo;
    private JTextField txtFldNombre;
    private JTextArea txtDescripcion;
    private JButton areaDeEmpleadosButton;
    private JButton modificarButton;
    private JButton volverButton;
    private JButton crearButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("VerDepartamento");
        frame.setContentPane(new VerDepartamento().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setVisible(true);
    }

    public VerDepartamento() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPrincipal menu = new menuPrincipal();
                menu.setVisible(true);
                menu.setSize(700, 500);
            }
        });
        areaDeEmpleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarEmpleados listar = new ListarEmpleados();
                listar.setVisible(true);
                listar.setSize(700, 500);
            }
        });
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarDepartamento modificar = new ModificarDepartamento();
                modificar.setVisible(true);
                modificar.setSize(700,500);
            }
        });
    }
}
