import javax.swing.*;

public class VerDirector extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton volverButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton buscarButton;
    private JTable table1;

    public VerDirector() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public static void main(String[] args) {
        VerDirector dialog = new VerDirector();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
        System.exit(0);
    }
}
