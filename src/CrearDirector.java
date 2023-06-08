import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearDirector extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnCrear;
    private JTextField txtFldCodigo;
    private JTextField txtFldNombre;
    private JTextField txtFldApellidos;
    private JTextField txtFldPremios;
    private JTextField txtFldAnyos;
    private JTextField txtFldEdad;
    private JTextField txtFldPais;
    private JCheckBox CheckHombre;
    private JCheckBox CheckMujer;
    private JTextField txtFldNac;

    public CrearDirector() {
        setContentPane(contentPane);
        UserManager.loadSession();
        setVisible(true);
        setSize(700, 500);
        setTitle("Creando director");
        setModal(true);
        ButtonGroup group = new ButtonGroup();
        group.add(CheckHombre);
        group.add(CheckMujer);
        getRootPane().setDefaultButton(btnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        UIManager.put("OptionPane.yesButtonText", "Confirmar");
        UIManager.put("OptionPane.noButtonText", "Cancelar");
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearDirector();
            }
        });
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new VerDirector();
            }
        });
    }

    public void crearDirector () {
        if (txtFldCodigo.getText().equals("") || txtFldNombre.getText().equals("") || txtFldApellidos.getText().equals("") || txtFldPremios.getText().equals("")
            || txtFldAnyos.getText().equals("") || txtFldEdad.getText().equals("") || txtFldNac.getText().equals("") || txtFldPais.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben de estar completos.", "Error al crear", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!CheckHombre.isSelected() && !CheckMujer.isSelected()) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un género.", "Error al crear", JOptionPane.ERROR_MESSAGE);
            } else {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer crear este director?", "Confirmación de creación", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    JOptionPane.showMessageDialog(null, "Se ha creado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                    pasandoDatos();
                    dispose();
                    JDialog dialog = new VerDirector();
                }
            }
        }
    }

    public void pasandoDatos () {
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
        String nacionalidad = txtFldNac.getText();
        int idPais = Integer.parseInt(txtFldPais.getText());
        Director dir = new Director(codigo, nombre, apellidos, numPremios, anyosExp, edad, genero, nacionalidad, idPais);
        director.crearDirector(dir);
    }

    public static void main(String[] args) {
        CrearDirector dialog = new CrearDirector();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
