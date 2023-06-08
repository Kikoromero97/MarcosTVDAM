import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static void mostrarModificarDepartamento(String[] args) {
        JFrame frame = new JFrame("ModificarDepartamento");
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
               // ListarEmpleados.mostrarListarEmpleados();
                dispose();
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // ListarEmpleados.mostrarListarEmpleados();
                dispose();

            }
        });

        comboBoxDepartamentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBoxDepartamentos.getSelectedItem();
                DBDepartamento deb = new DBDepartamento();
                ResultSet rs = deb.verDespecifico(selectedItem);

                try {
                    if (rs.next()) {
                        String value1 = rs.getString("columna1"); // Reemplaza "columna1" con el nombre real de la columna
                        String value2 = rs.getString("columna2"); // Reemplaza "columna2" con el nombre real de la columna
                        int value3 = rs.getInt("columna3"); // Reemplaza "columna3" con el nombre real de la columna

                        txtFldCodigo.setText(value1);
                        txtFldNombre.setText(value2);
                        txtDescripcion.setText(String.valueOf(value3));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
    public void editarDepartamento() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres editar con estos datos?", "Confirmación de editado", JOptionPane.YES_NO_OPTION);
        if (opcion == 0) {
            DBDepartamento cat = new DBDepartamento();
            int codigo = Integer.parseInt(txtFldCodigo.getText());
            String nombre = txtFldNombre.getText();
            String descrip = txtDescripcion.getText();
            JOptionPane.showMessageDialog(null, "Se ha editado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            JDialog dialog = new VerCategoria();
        }
    }
}
