import javax.swing.*;

public class EditarDirector extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnEditar;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField7;
    private JTextField textField8;

    public EditarDirector() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
    }

    public static void main(String[] args) {
        EditarDirector dialog = new EditarDirector();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
        System.exit(0);
    }
}
