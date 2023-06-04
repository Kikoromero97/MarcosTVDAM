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

    public CrearDirector() {
        setContentPane(contentPane);
        setVisible(true);
        setSize(700, 500);
        setTitle("Creando director");
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        UIManager.put("OptionPane.yesButtonText", "Confirmar");
        UIManager.put("OptionPane.noButtonText", "Cancelar");
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer crear este director?", "Confirmación de creación", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    JOptionPane.showMessageDialog(null, "Se ha creado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    JDialog dialog = new VerDirector();
                }
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

    public static void main(String[] args) {
        CrearDirector dialog = new CrearDirector();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
