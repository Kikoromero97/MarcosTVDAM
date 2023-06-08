import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        tableEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                seleccionaEmpleado(e);
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
    }

    private void search() {
        JTextField[] fields = {txtFldCodigo, txtFldNif, txtFldNombre, txtFldTelefono, txtFldEmail, txtFldDepartamento};
        int count = 0;
        for (JTextField field : fields){
            if (hasParameter(field)){
                count++;
            }
        }
        searchByX(count);
    }

    private void searchByX(int x) {
        switch (x){
            case 1 ->
        }
    }

    private boolean hasParameter(JTextField fiel){
        return fiel.getText().equals("");
    }

    private void seleccionaEmpleado(MouseEvent e) {
        if (e.getClickCount() == 1){
            JTable table = (JTable) e.getSource();
            int row = table.getSelectedRow();
            int codigo = (Integer) table.getValueAt(row, 0);
            VerEmpleado.mostrarVerEmpleado(codigo);
            dispose();
        }
    }

    public static void mostrarListarEmpleados(){
        JFrame frame = new ListarEmpleados();
    }
    public void loadEmpleados(){
        String[][] datos = Utilitis.getDataFromResultSet(db.listarEmpleadosLimitado(), COLUMNAS);
        DefaultTableModel table = new DefaultTableModel(datos, COLUMNAS);
        tableEmpleados.setModel(table);
        Utilitis.centerTable(tableEmpleados);
        tableEmpleados.setVisible(true);
    }

    public static void main(String[] args) {
        mostrarListarEmpleados();
    }
}
