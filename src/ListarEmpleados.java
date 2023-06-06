import javax.swing.*;

public class ListarEmpleados extends JFrame {
    private JPanel panelBusqueda;
    private JTextField txtFldNif;
    private JTextField txtFldNombre;
    private JTextField txtFldEmail;
    private JTextField txtFldDepartamento;
    private JTextField txtFldTelefono;
    private JLabel logoMARCOSTV;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JTextField txtFldCodigo;
    private JTable tableEmpleados;
    private JPanel panelPrincipal;
    private JScrollPane scrollPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ListarEmpleados");
        frame.setContentPane(new ListarEmpleados().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ListarEmpleados(){
        super("ListarEmpleados");
        setContentPane(panelPrincipal);

    }

}
