import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class CuentasUser extends JDialog {
    private JPanel contentPane;
    private JButton btnmasinfo;
    private JButton btnCancelar;
    private JTable TablaUsuarios;
    private JTextField textField1;
    private JButton btnbuscar;
    
    public CuentasUser() {
        setContentPane(contentPane);
        UserManager.loadSession();
        setTitle("Menú de usuarios y suscripciones");
        setVisible(true);
        setModal(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnCancelar);
        crearTabla();
        
        Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagen/iconoMarcosVentana.png"))).getImage();
        setIconImage(icon);
        btnmasinfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new InfoUsuario();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SeleccionUser();

            }
        });

        btnbuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*TODO Busqueda*/
            }
        });
        
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);




        TablaUsuarios.addMouseListener(new MouseAdapter() {
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
            String telefono = (String) table.getValueAt(row, 2);
            String email = (String) table.getValueAt(row, 3);
            String edad = (String) table.getValueAt(row, 4);
            String localidad = (String) table.getValueAt(row, 5);
            ArrayList<String> datos = new ArrayList<>();
            datos.add(codigo);
            datos.add(nombre);
            datos.add(telefono);
            datos.add(email);
            datos.add(edad);
            datos.add(localidad);
            InfoUsuario edit = new InfoUsuario();
            edit.llenarCampos(datos);
            dispose();
        }
    }

    public void crearTabla() {
        DBUsuarios CuentUsers = new DBUsuarios();
        String[][] tabla = Utilitis.getDataFromResultSet(CuentUsers.verUsuarios() , 6);
        String[] columnasVisitas = {"codigo", "nombre", "telefono", "email", "edad", "lcalidad"};
        DefaultTableModel table = new DefaultTableModel(tabla, columnasVisitas);
        TablaUsuarios.setModel(table);
        Utilitis.centerTable(TablaUsuarios);
    }
    
    public static void main(String[] args) {
        JDialog frame = new CuentasUser();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
