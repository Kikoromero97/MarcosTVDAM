import javax.swing.*;
import java.awt.event.*;

public class InfoUsuario extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField idUsuario;
    private JTextField txtname;
    private JTextField txtDir;
    private JTextField txtTel;
    private JTextField txtEmail;
    private JTextField txtedad;
    private JComboBox txtNacion;
    private JButton btnTarjeta;
    
    public InfoUsuario() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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
        
        
        btnTarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*JDialog frame = new CrearTarjetas();
                frame.setSize(500, 500);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);*/
                
            }
        });
    }
    
    private void onOK() {
        // add your code here
        dispose();
    }
    
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    
    public static void main(String[] args) {
        InfoUsuario dialog = new InfoUsuario();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
