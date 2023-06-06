import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarDepartamento extends JFrame {
    private JPanel panelPrincipal;
    private JComboBox comboBoxDepartamentos;
    private JTextField txtFldNombre;
    private JTextField txtFldCodigo;
    private JTextArea txtDescripcion;
    private JLabel logoMARCOSTV;
    private JButton eliminarButton;
    private JButton confirmarButton;
    private JButton cancelarButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ModificarDepartamento");
        frame.setContentPane(new ModificarDepartamento().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



    public ModificarDepartamento() {
        super("ModificarDepartamento");
        setContentPane(panelPrincipal);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int eliminar =JOptionPane.showConfirmDialog(null,"¿Seguro que quiere eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION);
                if (eliminar== 0){
                    JOptionPane.showMessageDialog(null, "Se ha eliminado con éxito", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarEmpleados empleados = new ListarEmpleados();
                empleados.setVisible(true);
                empleados.setSize(700, 500);
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarEmpleados empleados = new ListarEmpleados();
                empleados.setVisible(true);
                empleados.setSize(700, 500);
            }
        });
    }
}
