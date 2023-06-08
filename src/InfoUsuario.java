import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InfoUsuario extends JDialog {
    private JPanel contentPane;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JTextField idUsuario;
    private JTextField txtname;
    private JTextField txtTel;
    private JTextField txtEmail;
    private JTextField txtedad;
    private JComboBox txtNacion;
    private JButton btnTarjeta;
    private JButton btnBorrar;
    private ArrayList<String> info = new ArrayList<>();
    public static int num = 5;

    public InfoUsuario() {
        setContentPane(contentPane);
        setTitle("Menú de usuarios y suscripciones");
        setVisible(true);
        setModal(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnCancelar);

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                EditarUser eu = new EditarUser();
                eu.llenarCampos(info);
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CuentasUser();
            }
        });

        btnTarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num = Integer.parseInt(idUsuario.getText());
                new VerTarjetas();
                dispose();
            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opt = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere borrar el usuario con el id " + idUsuario.getText() + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (opt == 0) {
                    int code = Integer.parseInt(idUsuario.getText());
                    DBUsuarios DBUSERS = new DBUsuarios();
                    if (DBUSERS.deleteUsuario(code)) {
                        dispose();
                        new CuentasUser();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error inesperado");
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


    public void llenarCampos(ArrayList<String> datos) {
        info = datos;
        idUsuario.setText(datos.get(0));
        txtname.setText(datos.get(1));
        txtTel.setText(datos.get(2));
        txtEmail.setText(datos.get(3));
        txtedad.setText(datos.get(4));
        txtNacion.setSelectedItem(datos.get(5));

    }

    public static int NumeroaPasar() {
        return InfoUsuario.num;
    }

    public static void main(String[] args) {
        InfoUsuario dialog = new InfoUsuario();
        dialog.pack();
        dialog.setVisible(true);
    }
}
