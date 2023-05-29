import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerPaises extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnCrear;
    private JTextField txtFldBuscarNom;
    private JButton btnBuscar;
    private JTable tablaPaises;

    public VerPaises() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog(null, "Escribe el nombre del pais:");
            }
        });
    }

    public static void main(String[] args) {
        VerPaises dialog = new VerPaises();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
        System.exit(0);
    }
}
