import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

public class EditarTarjetas extends JDialog {
    private JPanel contentPane;
    private JTextField txtFldNum;
    private JTextField txtFldCaducidad;
    private JTextField txtFldTitular;
    private JTextField txtFldCVV;
    private JTextField txtFldBanco;
    private JButton BtnEditar;
    private JButton BtnVolver;
    private static int idUsuario;

    public EditarTarjetas(int idUsuario) {
        EditarTarjetas.idUsuario = idUsuario;
        setContentPane(contentPane);
        setTitle("Editando tarjeta");
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
                JDialog dialog = new VerTarjetas();
            }
        });

        BtnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarTarjeta();
            }
        });
    }

    public void editarTarjeta () {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres editar con estos datos?", "Confirmación de editado", JOptionPane.YES_NO_OPTION);
        if (opcion == 0) {
            DBTarjetas tarj = new DBTarjetas();
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
            tarj.editarTarjeta(tarjetaNew, idUsuario);
            JOptionPane.showMessageDialog(null, "Se ha editado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            JDialog dialog = new VerTarjetas();
        }
    }

    public void llenarCampos(ArrayList<String> datos) {
        txtFldNum.setText(datos.get(0));
        txtFldCaducidad.setText(datos.get(1));
        txtFldTitular.setText(datos.get(2));
        txtFldCVV.setText(datos.get(3));
        txtFldBanco.setText(datos.get(4));
    }

    public static void main(String[] args) {
        EditarTarjetas dialog = new EditarTarjetas(idUsuario);
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
