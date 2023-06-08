import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.util.ArrayList;

public class VerTarjetas extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnEliminar;
    private JButton btnCrear;
    private JTable tablaTarjetas;
    private JTextField txtFldNumero;
    private JButton btnBuscar;
    private static final DBTarjetas tarjeta = new DBTarjetas();

    private int idUsuario;

    public VerTarjetas() {
        InfoUsuario info = new InfoUsuario();
        idUsuario = info.NumeroaPasar();
        setContentPane(contentPane);
        setVisible(true);
        setSize(700, 500);
        setTitle("Tarjetas");
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        UIManager.put("OptionPane.yesButtonText", "Confirmar");
        UIManager.put("OptionPane.noButtonText", "Cancelar");
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
                eliminarCampo();
            }
        });

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new CrearTarjetas(idUsuario);
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCampo();
            }
        });
        tablaTarjetas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarInsert(e);
            }
        });
    }

    public void eliminarCampo() {
        String codSinParsear = txtFldNumero.getText();
        BigInteger codigo;
        try {
            codigo = new BigInteger(txtFldNumero.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "¡El campo \"Nº Tarjeta\" debe ser un número!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (codSinParsear.equals("")) {
            JOptionPane.showMessageDialog(null, "¡El campo \"Nº Tarjeta\" no puede estar vacío!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!tarjeta.existsTarjeta(idUsuario, codigo)) {
                JOptionPane.showMessageDialog(null, "¡Este número no existe!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            } else {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer eliminar esta tarjeta?", "Confirmación de borrado", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    tarjeta.deleteTarjeta(idUsuario, codigo);
                    JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                    txtFldNumero.setText("");
                    crearTabla();
                }
            }
        }
    }
    public void buscarCampo() {
        String codSinParsear = txtFldNumero.getText();
        if (codSinParsear.isEmpty()) {
            crearTabla();
            return;
        }
        BigInteger codigo;
        try {
            codigo = new BigInteger(txtFldNumero.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "¡El campo \"Nº Tarjeta\" debe ser un número!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!tarjeta.existsTarjeta(idUsuario, codigo)) {
            JOptionPane.showMessageDialog(null, "¡Este número no existe!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        } else {
            crearTablaEsp(idUsuario, codigo);
        }
    }

    public void seleccionarInsert(MouseEvent e) {
        if (e.getClickCount() == 1) {
            JTable table = (JTable) e.getSource();
            int row = table.getSelectedRow();
            String numero = (String) table.getValueAt(row, 0);
            String caducidad = (String) table.getValueAt(row, 1);
            String titular = (String) table.getValueAt(row, 2);
            String CVV = (String) table.getValueAt(row, 3);
            String banco = (String) table.getValueAt(row, 4);
            ArrayList<String> datos = new ArrayList<>();
            datos.add(numero);
            datos.add(caducidad);
            datos.add(titular);
            datos.add(CVV);
            datos.add(banco);
            EditarTarjetas tarjeta = new EditarTarjetas(idUsuario);
            tarjeta.llenarCampos(datos);
            dispose();
        }
    }

    public void crearTabla() {
        String[][] tabla = Utilitis.getDataFromResultSet(tarjeta.verTarjetaEsp(idUsuario), 5);
        String[] columnasVisitas = {"Número tarjeta", "Caducidad", "Titular", "CVV", "Banco"};
        DefaultTableModel table = new DefaultTableModel(Utilitis.deleteNulls(tabla), columnasVisitas);
        tablaTarjetas.setModel(table);
        Utilitis.centerTable(tablaTarjetas);
    }

    public void crearTablaEsp(int codigo, BigInteger numero) {
        String[][] tabla = Utilitis.getDataFromResultSet(tarjeta.verTarjetaMuyEsp(codigo, numero), 5);
        String[] columnasVisitas = {"Número tarjeta", "Caducidad", "Titular", "CVV", "Banco"};
        DefaultTableModel table = new DefaultTableModel(Utilitis.deleteNulls(tabla), columnasVisitas);
        tablaTarjetas.setModel(table);
        Utilitis.centerTable(tablaTarjetas);
    }

    public static void main(String[] args) {
        VerTarjetas dialog = new VerTarjetas();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
