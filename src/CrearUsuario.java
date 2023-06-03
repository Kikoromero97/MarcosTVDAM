import javax.swing.*;
import java.awt.event.*;

public class CrearUsuario extends JDialog {
    private JPanel contentPane;
    private JButton btnCrear;
    private JButton btnCancelar;
    private JTextField txtname;
    private JTextField txtDir;
    private JTextField txtTel;
    private JTextField txtEmail;
    private JTextField txtedad;
    private JComboBox txtNacion;
    
    public CrearUsuario() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnCrear);

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            /*Insert usuario en la DB*/
                dispose();
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente", "Usuario", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CrearUsuario dialog = new CrearUsuario();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
