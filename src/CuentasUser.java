import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class CuentasUser extends JDialog {
    private JPanel contentPane;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JTable table1;
    private JTextField textField1;
    private JButton btnbuscar;
    
    public CuentasUser() {
        setContentPane(contentPane);
        setModal(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        getRootPane().setDefaultButton(btnAceptar);
        
        Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagen/iconoMarcosVentana.png"))).getImage();
        setIconImage(icon);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
                JDialog usuarios = new SeleccionUser();
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
    
    private void onOK() {
        // add your code here
        dispose();
    }
    
    private void onCancel() {
        dispose();
    }
    
    public static void main(String[] args) {
        JDialog frame = new CuentasUser();
        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
        System.exit(0);
    }
}
