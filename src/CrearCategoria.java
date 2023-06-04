import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearCategoria extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnCrear;
    private JTextField txtFldCodigo;
    private JTextField txtFldNombre;
    private JTextField txtFldDescrip;

    public CrearCategoria() {
        setContentPane(contentPane);
        setVisible(true);
        setSize(700, 500);
        setTitle("Creando categoría");
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
                JDialog dialog = new VerCategoria();
            }
        });
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer crear esta categoría?", "Confirmación de creación", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    JOptionPane.showMessageDialog(null, "Se ha creado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    JDialog dialog = new VerCategoria();
                }
            }
        });
    }

    public static void main(String[] args) {
        CrearCategoria dialog = new CrearCategoria();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
