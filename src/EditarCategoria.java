import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarCategoria extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnEditar;
    private JTextField txtFldCodigo;
    private JTextField txtFldNombre;
    private JTextField txtFldDescrip;

    public EditarCategoria() {
        setContentPane(contentPane);
        setVisible(true);
        setSize(700, 500);
        setTitle("Editando categoría");
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new VerCategoria();
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Se ha editado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                JDialog dialog = new VerCategoria();
            }
        });
    }

    public static void main(String[] args) {
        EditarCategoria dialog = new EditarCategoria();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
