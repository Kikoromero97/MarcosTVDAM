import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EditarUser extends JDialog {
    private JPanel contentPane;
    private JButton btnguardar;
    private JButton btnCancelar;
    private JTextField idUsuario;
    private JTextField txtname;
    private JTextField txtTel;
    private JTextField txtEmail;
    private JTextField txtedad;
    private JComboBox txtNacion;

    private ArrayList<String> infor = new ArrayList<>();


    public EditarUser() {
        setContentPane(contentPane);
        setTitle("Menú de usuarios y suscripciones");
        setVisible(true);
        setModal(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnCancelar);
        
        btnguardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = idUsuario.getText();
                String name = txtname.getText();
                String tlf = txtTel.getText();
                String mail = txtEmail.getText();
                String edad = txtedad.getText();
                String localidad = (String) txtNacion.getSelectedItem();
                ArrayList<String> ChangedData = new ArrayList<>();

                UsuariosClientes usu;

                if (name.equals("") || tlf.equals("") ||  mail.equals("") || edad.equals("") || txtNacion.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(null, "Campo vacio detectado, rellene todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);

                } else {
                    try{
                        usu = new UsuariosClientes(name, tlf, mail,edad, localidad);
                        ChangedData.add(code);
                        ChangedData.add(name);
                        ChangedData.add(tlf);
                        ChangedData.add(mail);
                        ChangedData.add(edad);
                        ChangedData.add(localidad);
                    } catch(IncorrectMailException exc){
                        exc.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al editar el usuario, e-mail inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(null, "Usuario Editado Correctamente", "Creado", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    InfoUsuario IU = new InfoUsuario();
                    IU.llenarCampos(ChangedData);

                    /*TODO OPERACION DB MANAGER*/
                }
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                InfoUsuario IU = new InfoUsuario();
                IU.llenarCampos(infor);
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

    public void llenarCampos(ArrayList<String> datos) {
        infor = datos;
        idUsuario.setText(datos.get(0));
        txtname.setText(datos.get(1));
        txtTel.setText(datos.get(2));
        txtEmail.setText(datos.get(3));
        txtedad.setText(datos.get(4));
        txtNacion.setSelectedItem(datos.get(5));

    }
    public static void main(String[] args) {
        EditarUser dialog = new EditarUser();
        dialog.pack();
        dialog.setVisible(true);
    }
}
