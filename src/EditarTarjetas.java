import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1); // Establecer día en 1
            try {
                calendar.setTime(sdf.parse(caducidad));
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }
            Date fechaCaducidad = (Date) calendar.getTime();
            String titular = txtFldTitular.getText();
            int cvv = Integer.parseInt(txtFldCVV.getText());
            String banco = txtFldBanco.getText();
            Tarjeta tarjetaNew = new Tarjeta(numero, fechaCaducidad, titular, cvv, banco);
            tarj.editarTarjeta(tarjetaNew, idUsuario);
            JOptionPane.showMessageDialog(null, "Se ha editado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            JDialog dialog = new VerCategoria();
        }
    }

    public void llenarCampos(ArrayList<String> datos) {
        txtFldNum.setText(datos.get(0));
        txtFldCaducidad.setEditable(false);
        txtFldTitular.setText(datos.get(1));
        txtFldCVV.setText(datos.get(2));
        txtFldBanco.setText(datos.get(3));
    }

    public static void main(String[] args) {
        EditarTarjetas dialog = new EditarTarjetas(idUsuario);
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
