import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class Perfiles extends JDialog {
    private JPanel contentPane;
    private JButton brnEditar;
    private JButton btnCancelar;
    private JTextField txtBuscar;
    private JButton btnbuscar;
    private JButton btnNuevoPerfil;
    private JTable TablaPerfiles;

    public Perfiles() {
        setContentPane(contentPane);
        setTitle("Menú de usuarios y suscripciones");
        UserManager.loadSession();
        setVisible(true);
        setModal(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnCancelar);
        crearTabla();


        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SeleccionUser();
            }
        });

        btnbuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int numero = Integer.parseInt(txtBuscar.getText());
                    crearTablaSorted(numero);
                } catch (NumberFormatException exc){
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnNuevoPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CrearPerfUsuario();
            }
        });
        
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        
        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        TablaPerfiles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarInsert(e);
            }
        });
    }


    public void seleccionarInsert(MouseEvent e) {
        if (e.getClickCount() == 1) {
            JTable table = (JTable) e.getSource();
            int row = table.getSelectedRow();
            String codigo = (String) table.getValueAt(row, 0);
            String nombre = (String) table.getValueAt(row, 1);
            String codigoUsuario = (String) table.getValueAt(row, 2);
            ArrayList<String> datos = new ArrayList<>();
            datos.add(codigo);
            datos.add(nombre);
            datos.add(codigoUsuario);
            EditPerfil edit = new EditPerfil();
            edit.llenarCampos(datos);
            dispose();
        }
    }


    public void crearTabla() {
        DBUsuarios CuentUsers = new DBUsuarios();
        String[][] tabla = Utilitis.getDataFromResultSet(CuentUsers.verPerfiles() , 3);
        String[] columnasVisitas = {"codigoPerfil", "nombre", "idUsuario"};
        DefaultTableModel table = new DefaultTableModel(tabla, columnasVisitas);
        TablaPerfiles.setModel(table);
        Utilitis.centerTable(TablaPerfiles);
    }

    public void crearTablaSorted(int id) {
        DBUsuarios CuentUsers = new DBUsuarios();
        String[][] tabla = Utilitis.getDataFromResultSet(CuentUsers.verPerfilesSorted(id) , 3);
        String[] columnasVisitas = {"codigoPerfil", "nombre", "codigoUsuario"};
        DefaultTableModel table = new DefaultTableModel(tabla, columnasVisitas);
        TablaPerfiles.setModel(table);
        Utilitis.centerTable(TablaPerfiles);
    }

    
    public static void main(String[] args) {
        Perfiles dialog = new Perfiles();
        dialog.pack();
        dialog.setVisible(true);

    }
}
