import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerDepartamento extends JFrame{
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

    public static void mostrarVerDepartamento() {
        JFrame frame = new JFrame("VerDepartamento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setVisible(true);
    }

    public VerDepartamento() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   menuPrincipal.mostrarmenuPrincipal;
                dispose();
            }
        });
        areaDeEmpleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  AreaDeEmpleados.mostrarAreaDeEmpleados();
                dispose();
            }
        });

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearDepartamento.mostrarCrearDepartamento();
                dispose();
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // ModificarDepartamento.mostrarModificarDepartamento();
                dispose();
            }
        });
    }
}
