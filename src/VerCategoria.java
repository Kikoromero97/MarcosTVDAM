import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerCategoria extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnEliminar;
    private JButton btnCrear;
    private JTextField txtFldNombre;
    private JButton btnBuscar;
    private JTable table1;

    public VerCategoria() {
        setContentPane(contentPane);
        setVisible(true);
        setSize(700, 500);
        setTitle("Categorías");
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        UIManager.put("OptionPane.yesButtonText", "Confirmar");
        UIManager.put("OptionPane.noButtonText", "Cancelar");
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new PanelPrincipalContenido();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtFldNombre.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "¡El campo \"nombre\" no puede estar vacío!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
                } else {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer eliminar esta categoría?", "Confirmación de borrado", JOptionPane.YES_NO_OPTION);
                    if (opcion == 0) {
                        JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                        txtFldNombre.setText("");
                    }
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtFldNombre.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "¡El campo \"nombre\" no puede estar vacío!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new CrearCategoria();
            }
        });
    }

    public static void main(String[] args) {
        VerCategoria dialog = new VerCategoria();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
