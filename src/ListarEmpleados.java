import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListarEmpleados extends JFrame{
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

    private static final String[] COLUMNAS = {"codigo", "nif", "Nombre Completo"};
    private static final DBEmpleados db = new DBEmpleados();

    public ListarEmpleados(){
        super("Empleados");
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(panelPrincipal);
        loadEmpleados();
        UserManager.user.getRol();
    }
    public void loadEmpleados(){
        String[][] datos = Utilitis.getDataFromResultSet(db.listarEmpleadosLimitado(), COLUMNAS);
        DefaultTableModel table = new DefaultTableModel(datos, COLUMNAS);
        tableEmpleados.setModel(table);
        Utilitis.centerTable(tableEmpleados);
        tableEmpleados.setVisible(true);
    }
}
