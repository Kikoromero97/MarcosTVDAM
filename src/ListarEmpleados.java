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
    private JButton volverButton;
    private JTextField txtFldCodigo;
    private JTable tableEmpleados;
    private JPanel panelPrincipal;
    private JScrollPane scrollPanel;
    private JButton clearButton;

    private static final String[] COLUMNAS = {"Código", "NIF", "Nombre Completo"};
    private static final DBEmpleados db = new DBEmpleados();

    private static JTextField[] fields;

    /**
     * Constructor de ListarEmpleados.
     */
    public ListarEmpleados(){
        super("Empleados");
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(panelPrincipal);
        fields = new JTextField[]{txtFldCodigo, txtFldNif, txtFldNombre, txtFldTelefono, txtFldEmail, txtFldDepartamento};
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
                clearTxtFlds();
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTxtFlds();
                //VerDepartamento.mostrarDepartamento();
                dispose();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTxtFlds();
            }
        });
    }

    /**
     * Función que se encarga de vaciar todos los campos JTextField.
     */
    private void clearTxtFlds(){
        for (JTextField field : fields){
            field.setText("");
        }
    }

    /**
     * Función que se encarga de ver los parámetros rellenados y buscar por ellos.
     */
    private void search() {
        int count = 0;
        for (JTextField field : fields){
            if (hasParameter(field)){
                count++;
            }
        }
        if (count > 0){
            String where = "";
            if (hasParameter(fields[0])){
                where = plusAnd(where);
                where += "codigo = " + fields[0].getText() + " ";
            }
            if (hasParameter(fields[1])){
                where = plusAnd(where);
                where += "nif LIKE '" + fields[1].getText() + "' ";
            }
            if (hasParameter(fields[2])){
                where = plusAnd(where);
                where += "nombre LIKE '%" + fields[2].getText() + "%' OR primer_apellido LIKE '%" + fields[2].getText() + "%' OR segundo_apellido LIKE '%" + fields[2].getText() + "%' ";
            }
            if (hasParameter(fields[3])){
                where = plusAnd(where);
                where += "telefono = " + fields[3].getText() + " ";
            }
            if (hasParameter(fields[4])){
                where = plusAnd(where);
                where += "email LIKE '%" + fields[4].getText() + "%' ";
            }
            if (hasParameter(fields[5])){
                where = plusAnd(where);
                where += "codigo_departamento = " + fields[5].getText() + " ";
            }

            String[][] data = Utilitis.getDataFromResultSet(db.verEmpleadosCustom(where), 3);
            DefaultTableModel tableModel = new DefaultTableModel(data, COLUMNAS);
            tableEmpleados.setModel(tableModel);
            Utilitis.centerTable(tableEmpleados);
        } else {
            loadEmpleados();
        }
    }

    /**
     * Función que se encarga de añadir AND al final de una String pasada si no está vacía.
     *
     * @param where String al cual le queremos añadir AND.
     * @return String el String pasado + AND (en caso de que este vacío no suma AND)
     */
    private static String plusAnd(String where){
        if (!where.equals("")){
            where += "AND ";
        }
        return where;
    }


    /**
     * Función que se encarga de ver si el JTextField está vacía.
     *
     * @param fiel JTextField a comprobar.
     * @return boolean True si no está vacía y false si está vacía.
     */
    private boolean hasParameter(JTextField fiel){
        return !fiel.getText().equals("");
    }

    /**
     * Función que lee una Fila de una JTable.
     *
     * @param e MouseEvent de la tabla.
     */
    private void seleccionaEmpleado(MouseEvent e) {
        if (e.getClickCount() == 1){
            JTable table = (JTable) e.getSource();
            int row = table.getSelectedRow();
            int codigo = Integer.parseInt((String) table.getValueAt(row, 0));
            VerEmpleado.mostrarVerEmpleado(codigo);
            dispose();
        }
    }

    /**
     * Funcion que ejecuta la pantalla ListarEmpleados
     */
    public static void mostrarListarEmpleados(){
        JFrame frame = new ListarEmpleados();
    }

    /**
     * Función que carga todos los empleados.
     */
    public void loadEmpleados(){
        String[][] datos = Utilitis.getDataFromResultSet(db.listarEmpleadosLimitado(), 3);
        DefaultTableModel table = new DefaultTableModel(datos, COLUMNAS);
        tableEmpleados.setModel(table);
        Utilitis.centerTable(tableEmpleados);
        tableEmpleados.setVisible(true);
    }

    public static void main(String[] args) {
        mostrarListarEmpleados();
    }
}
