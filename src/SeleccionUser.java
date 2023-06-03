import javax.swing.*;
import java.awt.event.*;

public class SeleccionUser extends JDialog {
    private JPanel contentPane;
    private JButton VerUsers;
    private JButton CrearUsers;
    private JButton PerfilesUsers;
    private JButton VerSuscripciones;
    private JButton cancelarButton;
    private JButton buttonOK;
    private JButton buttonCancel;

    public SeleccionUser() {
        setContentPane(contentPane);
        setModal(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        getRootPane().setDefaultButton(buttonOK);

        VerSuscripciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog subsdia = new Suscripciones();
                subsdia.setSize(700, 500);
                subsdia.setLocationRelativeTo(null);
                subsdia.setVisible(true);

            }
        });

        PerfilesUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog perfus = new Perfiles();
                perfus.setSize(700, 500);
                perfus.setLocationRelativeTo(null);
                perfus.setVisible(true);
            }
        });


        CrearUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog crearU = new CrearUsuario();
                crearU.setSize(700, 500);
                crearU.setLocationRelativeTo(null);
                crearU.setVisible(true);
            }
        });

        VerUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog VerU = new CuentasUser();
               
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
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
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
