import javax.swing.*;
import java.awt.event.*;

public class CrearUsuario extends JDialog {
    private JPanel contentPane;
    private JButton btnCrear;
    private JButton btnCancelar;
    private JTextField txtname;
    private JTextField txtTel;
    private JTextField txtEmail;
    private JTextField txtedad;
    private JComboBox txtNacion;

    
    public CrearUsuario() {
        setContentPane(contentPane);
        setTitle("Menú de usuarios y suscripciones");
        setVisible(true);
        setModal(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnCancelar);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SeleccionUser();
            }
        });

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtname.getText();
                String tlf = txtTel.getText();
                String mail = txtEmail.getText();
                String edad = txtedad.getText();
                String localidad = (String) txtNacion.getSelectedItem();
                DBUsuarios usuariosDB = new DBUsuarios();

                if (name.equals("") || tlf.equals("") ||  mail.equals("") || edad.equals("") || txtNacion.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(null, "Campo vacio detectado, rellene todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    try{
                      UsuariosClientes usu = new UsuariosClientes(name, tlf, mail,edad, localidad);
                      //Solo si crea el usuario te devuelve al menu principal
                     if (usuariosDB.crearUsuario(usu)){
                         dispose();
                         new SeleccionUser();
                     }
                    } catch(IncorrectMailException  exc){
                        exc.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Mail inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
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

    public static void main(String[] args) {
        CrearUsuario dialog = new CrearUsuario();
        dialog.pack();
        dialog.setVisible(true);

    }
}
