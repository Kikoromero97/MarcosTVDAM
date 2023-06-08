import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearDepartamento extends JFrame{

    private JPanel panelPrincipal;
    private JTextField txtFldNombre;
    private JTextField txtFldCodigo;
    private JTextArea txtDescripcion;
    private JLabel logoMARCOSTV;
    private JButton eliminarButton;
    private JButton confirmarButton;
    private JButton cancelarButton;

    public static void mostrarCrearDepartamento() {
        JFrame frame = new JFrame("CrearDepartamento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public CrearDepartamento() {
        super("CrearDepartamento");
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
              //  ListarEmpleados.mostrarListarEmpleados;
                dispose();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearDepartamento.mostrarCrearDepartamento();
                dispose();
            }
        });

    }
}
