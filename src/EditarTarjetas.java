import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarTarjetas extends JDialog {
    private JPanel contentPane;
    private JTextField txtFldNum;
    private JTextField txtFldCaducidad;
    private JTextField txtFldTitular;
    private JTextField txtFldCVV;
    private JTextField txtFldBanco;
    private JButton BtnEditar;
    private JButton BtnVolver;

    public EditarTarjetas() {
        setContentPane(contentPane);
        setTitle("Editando tarjeta");
        setVisible(true);
        setSize(700, 500);
        setModal(true);
        getRootPane().setDefaultButton(BtnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        BtnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new CrearTarjetas();
            }
        });
        BtnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Se ha editado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                JDialog dialog = new CrearTarjetas();
            }
        });
    }

    public static void main(String[] args) {
        EditarTarjetas dialog = new EditarTarjetas();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
