import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearTarjetas extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnCrear;
    private JTextField txtFldNum;
    private JTextField txtFldCaducidad;
    private JTextField txtFldCVV;
    private JTextField txtFldTitular;
    private JTextField txtFldBanco;

    public CrearTarjetas() {
        setContentPane(contentPane);
        setTitle("Creando tarjeta");
        setVisible(true);
        setSize(700, 500);
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
                JDialog dialog = new VerTarjetas();
            }
        });
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas crearlo?", "Confirmación de creación", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    JOptionPane.showMessageDialog(null, "Se ha creado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        CrearTarjetas dialog = new CrearTarjetas();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
