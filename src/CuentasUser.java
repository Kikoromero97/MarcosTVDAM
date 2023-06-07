import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class CuentasUser extends JDialog {
    private JPanel contentPane;
    private JButton btnmasinfo;
    private JButton btnCancelar;
    private JTable table1;
    private JTextField textField1;
    private JButton btnbuscar;
    
    public CuentasUser() {
        setContentPane(contentPane);
        setTitle("Menú de usuarios y suscripciones");
        setVisible(true);
        setModal(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnCancelar);
        
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
    }

    
    public static void main(String[] args) {
        JDialog frame = new CuentasUser();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
