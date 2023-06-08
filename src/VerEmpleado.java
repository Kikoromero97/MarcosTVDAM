import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerEmpleado extends JFrame{
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
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton volverButton;

    private static final DBEmpleados DB_EMPLEADOS = new DBEmpleados();

    private static final DBDepartamento DB_DEPARTAMENTO  = new DBDepartamento();

    public VerEmpleado(int codigo){
        setContentPane(panelPrincipal);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 500);

        cargarDatosEmpleado(codigo);
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int op = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas eliminar?", "MARCOS TV", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == 0){
                    if (DB_EMPLEADOS.delete(codigo)){
                        JOptionPane.showMessageDialog(null, "Empleado " + codigo + " eliminado correctamente.", "MARCOS TV", JOptionPane.INFORMATION_MESSAGE);
                        volverButton.doClick();
                    } else {
                        JOptionPane.showMessageDialog(null, "Empleado " + codigo + " no eliminado.", "MARCOS TV", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarEmpleados.mostrarListarEmpleados();
                dispose();
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarEmpleado.mostrarModificarEmpleado(codigo);
                dispose();
            }
        });
    }

    public static void mostrarVerEmpleado(int codigo){
        JFrame frame = new VerEmpleado(codigo);
    }

    public void cargarDatosEmpleado(int codigo) {
        try{
            ResultSet rs = DB_EMPLEADOS.verEmpleado(codigo);
            while (rs.next()){
                txtFldNif.setText(rs.getString("nif"));
                txtFldNombre.setText(rs.getString("nombre"));
                txtFldApellido1.setText(rs.getString("primer_apellido"));
                txtFldApellido2.setText(rs.getString("segundo_apellido"));
                txtFldDireccion.setText(rs.getString("direccion"));
                txtFldEmail.setText(rs.getString("email"));
                setDepartamentoTxtFld(rs.getInt("codigo_departamento"));
                txtFldTelefono.setText(rs.getString("telefono"));
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void setDepartamentoTxtFld(int departamento) {
        try{
            ResultSet rs = DB_DEPARTAMENTO.verDepartamento(departamento);
            while (rs.next()){
                txtFldDepartamento.setText(rs.getString("nombre"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
