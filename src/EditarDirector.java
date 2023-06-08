import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditarDirector extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnEditar;
    private JTextField txtFldApellidos;
    private JTextField txtFldPremios;
    private JTextField txtFldAnyos;
    private JTextField txtFldEdad;
    private JTextField txtFldCodigo;
    private JTextField txtFldNombre;
    private JTextField txtFldPais;
    private JCheckBox CheckHombre;
    private JCheckBox CheckMujer;
    private JTextField txtFldNacionalidad;

    public EditarDirector() {
        setContentPane(contentPane);
        setVisible(true);
        setSize(700, 500);
        setTitle("Editando director");
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        UIManager.put("OptionPane.yesButtonText", "Confirmar");
        UIManager.put("OptionPane.noButtonText", "Cancelar");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(CheckHombre);
        grupo.add(CheckMujer);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new VerDirector();
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
            DBDirector director = new DBDirector();
            generoDirector genero;
            int codigo = Integer.parseInt(txtFldCodigo.getText());
            String nombre = txtFldNombre.getText();
            String apellidos = txtFldApellidos.getText();
            int numPremios = Integer.parseInt(txtFldPremios.getText());
            int anyosExp = Integer.parseInt(txtFldAnyos.getText());
            int edad = Integer.parseInt(txtFldEdad.getText());
            if (CheckMujer.isSelected()) {
                genero = generoDirector.M;
            } else {
                genero = generoDirector.H;
            }
            String nacionalidad = txtFldNacionalidad.getText();
            int idPais = Integer.parseInt(txtFldPais.getText());
            Director dir = new Director(codigo, nombre, apellidos, numPremios, anyosExp, edad, genero, nacionalidad, idPais);
            director.editarDirector(dir);
            JOptionPane.showMessageDialog(null, "Se ha editado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            JDialog dialog = new VerDirector();
        }
    }
    public void llenarCampos(ArrayList<String> datos) {
        txtFldCodigo.setText(datos.get(0));
        txtFldCodigo.setEditable(false);
        txtFldNombre.setText(datos.get(1));
        txtFldApellidos.setText(datos.get(2));
        txtFldPremios.setText(datos.get(3));
        txtFldAnyos.setText(datos.get(4));
        txtFldEdad.setText(datos.get(5));
        if (datos.get(6).startsWith("H")) {
            CheckHombre.setSelected(true);
        } else {
            CheckMujer.setSelected(true);
        }
        txtFldNacionalidad.setText(datos.get(7));
        txtFldPais.setText(datos.get(8));
    }
    public static void main(String[] args) {
        EditarDirector dialog = new EditarDirector();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
