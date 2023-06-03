import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearTarjetas extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnEliminar;
    private JButton btnEditar;
    private JTextField txtFldNum;
    private JTextField txtFldTitular;
    private JTextField txtFldCVV;
    private JTextField txtFldBanco;
    private JTextField txtFldCaducidad;

    public CrearTarjetas() {
        setContentPane(contentPane);
        setTitle("Tarjetas del usuario");
        setVisible(true);
        setSize(700, 500);
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new InfoUsuario();
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new EditarTarjetas();
            }
        });
    }



    public static void main(String[] args) {
        CrearTarjetas dialog = new CrearTarjetas();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
