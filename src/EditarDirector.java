import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                JOptionPane.showMessageDialog(null, "Se ha editado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                JDialog dialog = new VerDirector();
            }
        });
    }

    public static void main(String[] args) {
        EditarDirector dialog = new EditarDirector();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }
}
