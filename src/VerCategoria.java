import javax.swing.*;

public class VerCategoria extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton crearButton;
    private JTextField textField1;
    private JButton buscarButton;
    private JTable table1;

    public VerCategoria() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public static void main(String[] args) {
        VerCategoria dialog = new VerCategoria();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
        System.exit(0);
    }
}
