import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class VerTarjetas extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnEliminar;
    private JButton btnCrear;
    private JTable tablaTarjetas;
    private JTextField txtFldNumero;
    private static final DBTarjetas tarjeta = new DBTarjetas();

    private int idUsuario;

    public VerTarjetas() {
        /*InfoUsuario info = new InfoUsuario();
        int idUsuario = info.NumeroaPasar();*/
        UserManager.loadSession();
        setContentPane(contentPane);
        setVisible(true);
        setSize(700, 500);
        setTitle("Tarjetas");
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        crearTabla();

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new InfoUsuario();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtFldNumero.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "El campo \"Nº tarjeta\" no puede estar vacío.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
                } else {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer eliminar esta tarjeta?", "Confirmación de borrado", JOptionPane.YES_NO_OPTION);
                    if (opcion == 0) {
                        JOptionPane.showMessageDialog(null, "Se ha borrado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new CrearTarjetas();
            }
        });
    }

    public void crearTabla () {
        int codigo = 1;
        String [][] tabla = Utilitis.getDataFromResultSet(tarjeta.verTarjetaEsp(codigo), 5);
        String[] columnasVisitas = {"numero", "caducidad", "titular", "cvv", "banco"};
        DefaultTableModel table = new DefaultTableModel(tabla, columnasVisitas);
        tablaTarjetas.setModel(table);
        System.out.println(Arrays.deepToString(tabla));
    }

    public static void main(String[] args) {
        VerTarjetas dialog = new VerTarjetas();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
