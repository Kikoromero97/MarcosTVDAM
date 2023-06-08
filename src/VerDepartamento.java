import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("VerDepartamento");
        frame.setContentPane(new VerDepartamento().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void mostrarVerDepartamento() {
        JFrame frame = new VerDepartamento();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setVisible(true);
    }

    public VerDepartamento() {
        setContentPane(panelPrincipal);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPrincipal.mostrarMenuPrincipal();
                dispose();
            }
        });
        areaDeEmpleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarEmpleados.mostrarListarEmpleados();
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
                ModificarDepartamento.mostrarModificarDepartamento();
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
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        int codigo = rs.getInt("codigo");

                        txtFldCodigo.setText(String.valueOf(codigo));
                        txtFldNombre.setText(nombre);
                        txtDescripcion.setText(String.valueOf(descripcion));
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
