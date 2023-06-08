import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModificarEmpleado extends JFrame{
    private JPanel panelPrincipal;
    private JTextField txtFldNif;
    private JTextField txtFldNombre;
    private JTextField txtFldApellido1;
    private JTextField txtFldApellido2;
    private JTextField txtFldDireccion;
    private JTextField txtFldEmail;
    private JTextField txtFldDepartamento;
    private JTextField txtFldTelefono;
    private JLabel logoMARCOSTV;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JButton volverButton;

    private static final DBEmpleados DB_EMPLEADOS = new DBEmpleados();
    private static final DBDepartamento DB_DEPARTAMENTO  = new DBDepartamento();


    public ModificarEmpleado(int codigo){
        setContentPane(panelPrincipal);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 500);

        cargarDatosEmpleado(codigo);
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int op = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas modificar?", "MARCOS TV", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == 0){
                    modificarEmpleado(codigo);
                }
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerEmpleado.mostrarVerEmpleado(codigo);
                dispose();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverButton.doClick();
            }
        });
    }

    private void modificarEmpleado(int codigo) {

        String nif = txtFldNif.getText();
        String nombre = txtFldNombre.getText();
        String primer_apellido = txtFldApellido1.getText();
        String segundo_apellido = txtFldApellido2.getText();
        String direccion = txtFldDireccion.getText();
        String email = txtFldEmail.getText();
        int telefono = Integer.parseInt(txtFldTelefono.getText());
        int departamento = Integer.parseInt(txtFldDepartamento.getText());

        if (DB_EMPLEADOS.modify(codigo, nif, nombre, primer_apellido, segundo_apellido, direccion, email, departamento, telefono)){
            JOptionPane.showMessageDialog(null, "Empleado " + codigo + " se ha modificado correctamente.", "MARCOS TV", JOptionPane.INFORMATION_MESSAGE);
            volverButton.doClick();
        } else {
            JOptionPane.showMessageDialog(null, "Empleado " + codigo + " no se ha modificado.", "MARCOS TV", JOptionPane.ERROR_MESSAGE);
            cargarDatosEmpleado(codigo);
        }
    }

    public static void mostrarModificarEmpleado(int codigo) {
        JFrame frame = new ModificarEmpleado(codigo);
    }
    public void cargarDatosEmpleado(int codigo) {
        try {
            ResultSet rs = DB_EMPLEADOS.verEmpleado(codigo);
            while (rs.next()) {
                txtFldNif.setText(rs.getString("nif"));
                txtFldNombre.setText(rs.getString("nombre"));
                txtFldApellido1.setText(rs.getString("primer_apellido"));
                txtFldApellido2.setText(rs.getString("segundo_apellido"));
                txtFldDireccion.setText(rs.getString("direccion"));
                txtFldEmail.setText(rs.getString("email"));
                txtFldDepartamento.setText(rs.getString("codigo_departamento"));
                txtFldTelefono.setText(rs.getString("telefono"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
