import javax.swing.*;

public class EditarTarjetas extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton editarButton;
    private JButton volverButton;
    private JButton buttonCancel;

    public EditarTarjetas() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public static void main(String[] args) {
        EditarTarjetas dialog = new EditarTarjetas();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
        System.exit(0);
    }
}
