import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EditPerfil extends JDialog {
    private JPanel contentPane;
    private JButton btnguardar;
    private JButton btnCancelar;
    private JTextField txtPerfil;
    private JTextField txtIDPerf;
    private JTextField txtIDUsu;
    private JButton btnEliminar;
    private ArrayList<String> info = new ArrayList<>();
    
    public EditPerfil() {
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

                String perfname = txtPerfil.getText();

                if (perfname.length() < 2){
                    JOptionPane.showMessageDialog(null, "Nombre de perfil demasiado corto", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre de perfil modificado correctamente", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new Perfiles();
                    /*TODO Guardar Nombre Perfil en la BBDD*/
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



    public void llenarCampos(ArrayList<String> datos) {
        info = datos;
        txtIDPerf.setText(datos.get(0));
        txtPerfil.setText(datos.get(1));
        txtIDUsu.setText(datos.get(2));
    }

    
    public static void main(String[] args) {
        EditPerfil dialog = new EditPerfil();
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);

    }
}
