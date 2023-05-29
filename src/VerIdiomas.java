import javax.swing.*;

public class VerIdiomas extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnCrear;
    private JTextField txtFldBuscarNom;
    private JButton btnBuscar;
    private JTable tablaIdiomas;
    private JButton buttonOK;

    public VerIdiomas() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public static void main(String[] args) {
        VerIdiomas dialog = new VerIdiomas();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
        System.exit(0);
    }
}
