import javax.swing.*;
import java.awt.event.*;

public class EditarUser extends JDialog {
    private JPanel contentPane;
    private JButton btnguardar;
    private JButton btnCancelar;
    private JTextField idUsuario;
    private JTextField txtname;
    private JTextField txtDir;
    private JTextField txtTel;
    private JTextField txtEmail;
    private JTextField txtedad;
    private JComboBox txtNacion;
    
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
                JOptionPane.showMessageDialog(null, "usuario editado", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new InfoUsuario();
                /*TODO DB Manager editar un usuario*/

                /*
                * Si no se pudo editar
                * JOptionPane.showMessageDialog(null, "Error al editar el usuario", "Fail", JOptionPane.ERROR_MESSAGE);
                 * */
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new InfoUsuario();
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
        EditarUser dialog = new EditarUser();
        dialog.pack();
        dialog.setVisible(true);
    }
}
