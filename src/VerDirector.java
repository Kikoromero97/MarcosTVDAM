import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerDirector extends JDialog {
    private JPanel contentPane;
    private JButton BtnCrear;
    private JButton BtnEliminar;
    private JButton BtnVolver;
    private JTextField txtFldNombre;
    private JTextField txtFldApellidos;
    private JButton BtnBuscar;
    private JTable TablaDirectores;

    public VerDirector() {
        setContentPane(contentPane);
        setTitle("Directores");
        setVisible(true);
        setSize(700, 500);
        setModal(true);
        getRootPane().setDefaultButton(BtnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        UIManager.put("OptionPane.yesButtonText", "Confirmar");
        UIManager.put("OptionPane.noButtonText", "Cancelar");
        BtnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new menuPrincipal();
            }
        });

        BtnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new CrearDirector();
            }
        });

        BtnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtFldNombre.getText().equals("") || txtFldApellidos.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "¡Ambos campos deben estar completos para buscar!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
                } else {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer eliminar este director?", "Confirmación de borrado", JOptionPane.YES_NO_OPTION);
                    if (opcion == 0) {
                        JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                        txtFldNombre.setText("");
                        txtFldApellidos.setText("");
                    }
                }
            }
        });
        BtnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtFldNombre.getText().equals("") || txtFldApellidos.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "¡Ambos campos deben estar completos para buscar!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        VerDirector dialog = new VerDirector();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
