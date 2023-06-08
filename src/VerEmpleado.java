import javax.swing.*;

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

    public VerEmpleado(){

    }

    public static void mostrarVerEmpleado(int codigo){
        cargarDatosEmpleado(codigo);
        JFrame frame = new VerEmpleado();
    }

    private static void cargarDatosEmpleado(int codigo) {

    }
}
