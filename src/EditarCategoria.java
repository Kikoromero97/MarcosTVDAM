import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        UIManager.put("OptionPane.yesButtonText", "Confirmar");
        UIManager.put("OptionPane.noButtonText", "Cancelar");
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
                editarCategoria();
            }
        });
    }

    public void editarCategoria () {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres editar con estos datos?", "Confirmación de editado", JOptionPane.YES_NO_OPTION);
        if (opcion == 0) {
            DBCategorias cat = new DBCategorias();
            int codigo = Integer.parseInt(txtFldCodigo.getText());
            String nombre = txtFldNombre.getText();
            String descrip = txtFldDescrip.getText();
            Categoria categ = new Categoria(codigo, nombre, descrip);
            cat.editarCategoria(categ);
            JOptionPane.showMessageDialog(null, "Se ha editado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            JDialog dialog = new VerCategoria();
        }
    }

    public void llenarCampos(ArrayList<String> datos) {
        txtFldCodigo.setText(datos.get(0));
        txtFldCodigo.setEditable(false);
        txtFldNombre.setText(datos.get(1));
        txtFldDescrip.setText(datos.get(2));
    }

    public static void main(String[] args) {
        EditarCategoria dialog = new EditarCategoria();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
