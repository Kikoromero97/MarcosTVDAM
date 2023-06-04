import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerIdiomas extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnCrear;
    private JTextField txtFldBuscarNom;
    private JButton btnBuscar;
    private JTable tablaIdiomas;

    public VerIdiomas() {
        setContentPane(contentPane);
        setVisible(true);
        setSize(700, 500);
        setTitle("Idiomas");
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new PanelPrincipalContenido();
            }
        });
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = JOptionPane.showInputDialog(null, "Ingrese el código del idioma:", "Registro de usuario", JOptionPane.QUESTION_MESSAGE);
                if (codigo != null && !codigo.isEmpty()) {
                    String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del idioma:", "Registro de usuario", JOptionPane.QUESTION_MESSAGE);
                    if (nombre != null && !nombre.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Se ha creado el idioma correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes introducir un nombre.", "Campo vacío", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debes introducir un número.", "Campo vacío", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtFldBuscarNom.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "¡El campo \"nombre\" no puede estar vacío!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        VerIdiomas dialog = new VerIdiomas();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
