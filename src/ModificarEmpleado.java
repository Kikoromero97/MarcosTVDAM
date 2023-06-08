import javax.swing.*;
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

    private static final DBEmpleados DB_EMPLEADOS = new DBEmpleados();

    public ModificarEmpleado(int codigo){
        setContentPane(panelPrincipal);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 500);

        cargarDatosEmpleado(codigo);
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
