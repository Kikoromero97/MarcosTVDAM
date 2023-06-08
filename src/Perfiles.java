import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Perfiles extends JDialog {
    private JPanel contentPane;
    private JButton brnEditar;
    private JButton btnCancelar;
    private JTextField textField1;
    private JButton btnbuscar;
    private JButton btnNuevoPerfil;
    private JTable TablaPerfiles;

    public Perfiles() {
        setContentPane(contentPane);
        setTitle("Menú de usuarios y suscripciones");
        setVisible(true);
        setModal(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnCancelar);
        
        brnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EditPerfil();
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
                /*TODO Buscar id users*/
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
    }



    /*public void crearTabla() {
        Perfiles per = new Perfiles();
        String[][] tabla = Utilitis.getDataFromResultSet( , 3);
        String[] columnasVisitas = {"Código", "Nombre", "Descripción"};
        DefaultTableModel table = new DefaultTableModel(tabla, columnasVisitas);
        TablaPerfiles.setModel(table);
        Utilitis.centerTable(TablaPerfiles);
    }*/

    /*public void crearTablaEsp(int codigo) {
        DBCategorias categoria = new DBCategorias();
        String[][] tabla = Utilitis.getDataFromResultSet(categoria.verCategoriaEsp(codigo), 3);
        String[] columnasVisitas = {"Código", "Nombre", "Descripción"};
        DefaultTableModel table = new DefaultTableModel(tabla, columnasVisitas);
        tablaCat.setModel(table);
        Utilitis.centerTable(tablaCat);
    }*/

    
    public static void main(String[] args) {
        Perfiles dialog = new Perfiles();
        dialog.pack();
        dialog.setVisible(true);

    }
}
