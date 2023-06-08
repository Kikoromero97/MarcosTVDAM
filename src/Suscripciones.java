import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Suscripciones extends JDialog {
    private JPanel contentPane;
    private JTable TablaSuscripciones;
    private JTextField textField1;
    private JButton btnbuscar;
    private JButton btnCancelar;
    private JButton buttonOK;
    private JButton buttonCancel;
    
    public Suscripciones() {
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
                /*TODO Filtro busqueda DBManager*/
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


    public void crearTabla() {
        DBUsuarios CuentUsers = new DBUsuarios();
        String[][] tabla = Utilitis.getDataFromResultSet(CuentUsers.verSuscripciones() , 6);
        String[] columnasVisitas = {"id", "nombre", "descripcion", "precio", "duracion_meses", "cantidaPersona"};
        DefaultTableModel table = new DefaultTableModel(tabla, columnasVisitas);
        TablaSuscripciones.setModel(table);
        Utilitis.centerTable(TablaSuscripciones);
    }
    
    public static void main(String[] args) {
        Suscripciones dialog = new Suscripciones();
        dialog.pack();
        dialog.setVisible(true);
    }
}
