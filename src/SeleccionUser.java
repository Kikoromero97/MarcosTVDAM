import javax.swing.*;
import java.awt.event.*;

public class SeleccionUser extends JDialog {
    private JPanel contentPane;
    private JButton VerUsers;
    private JButton CrearUsers;
    private JButton PerfilesUsers;
    private JButton VerSuscripciones;
    private JButton btnCancelar;
    private JButton buttonOK;
    private JButton buttonCancel;

    public SeleccionUser() {
        setContentPane(contentPane);
        setTitle("Menú de usuarios y suscripciones");
        setVisible(true);
        setModal(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        /*Descomentar esto cuando se vaya a hacer un push*/
        getRootPane().setDefaultButton(btnCancelar);

        VerSuscripciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Suscripciones subsdia = new Suscripciones();

            }
        });

        PerfilesUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Perfiles perfus = new Perfiles();
            }
        });


        CrearUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CrearUsuario CU = new CrearUsuario();

            }
        });

        VerUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CuentasUser CUSER = new CuentasUser();
               
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
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
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SeleccionUser dialog = new SeleccionUser();
        dialog.setVisible(true);

    }
}
