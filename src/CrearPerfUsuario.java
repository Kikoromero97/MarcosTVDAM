import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class CrearPerfUsuario extends JDialog {
    private JPanel contentPane;
    private JButton btnCrear;
    private JButton btnCancelar;
    private JTextField txtPerfil;
    private JTextField txtIDUsuario;

    public CrearPerfUsuario() {
        setContentPane(contentPane);
        setTitle("Menú de usuarios y suscripciones");

        setVisible(true);
        setModal(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnCancelar);
        
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String perfname = txtPerfil.getText();
                    int codigoUsuario = Integer.parseInt(txtIDUsuario.getText());
                    DBUsuarios Dbuser = new DBUsuarios();

                    if (perfname.length() < 2) {
                        JOptionPane.showMessageDialog(null, "Nombre de perfil demasiado corto", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (Dbuser.existeUsuario(codigoUsuario)){
                            PerfilesDeUsuario Perf = new PerfilesDeUsuario(perfname, codigoUsuario);
                            Dbuser.crearPerfil(Perf);
                            dispose();
                            new Perfiles();
                        } else {
                            JOptionPane.showMessageDialog(null, "No existe el usuario");
                        }
                    }
                }catch (NumberFormatException ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "El id del perfil no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Perfiles();
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
        CrearPerfUsuario dialog = new CrearPerfUsuario();
        dialog.pack();
        dialog.setVisible(true);

    }
}
