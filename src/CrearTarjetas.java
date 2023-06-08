import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CrearTarjetas extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnCrear;
    private JTextField txtFldNum;
    private JTextField txtFldCaducidad;
    private JTextField txtFldCVV;
    private JTextField txtFldTitular;
    private JTextField txtFldBanco;
    private static int idUsuario;

    public CrearTarjetas(int idUsuario) {
        CrearTarjetas.idUsuario = idUsuario;
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
                crearTarjeta();
            }
        });
    }

    public void crearTarjeta () {
        DBTarjetas tarj = new DBTarjetas();
        if (txtFldNum.getText().equals("") || txtFldCaducidad.getText().equals("") || txtFldTitular.getText().equals("")
            || txtFldCVV.getText().equals("") || txtFldBanco.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben de estar completos.", "Error al crear", JOptionPane.ERROR_MESSAGE);
        } else {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer crear esta tarjeta?", "Confirmación de creación", JOptionPane.YES_NO_OPTION);
            if (opcion == 0) {
                JOptionPane.showMessageDialog(null, "Se ha creado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                BigInteger numero = new BigInteger(txtFldNum.getText());
                String caducidad = txtFldCaducidad.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fechaCaducidad = null;
                try {
                    fechaCaducidad = sdf.parse(caducidad);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                java.sql.Date fechaCaducidadSql = new java.sql.Date(fechaCaducidad.getTime());
                String titular = txtFldTitular.getText();
                int cvv = Integer.parseInt(txtFldCVV.getText());
                String banco = txtFldBanco.getText();
                Tarjeta tarjetaNew = new Tarjeta(numero, fechaCaducidadSql, titular, cvv, banco);
                tarj.crearTarjeta(tarjetaNew, idUsuario);
                dispose();
                JDialog dialog = new VerTarjetas();
            }
        }
    }

    public static void main(String[] args) {
        CrearTarjetas dialog = new CrearTarjetas(idUsuario);
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
