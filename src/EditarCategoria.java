import javax.swing.*;
import java.awt.event.*;

public class EditarCategoria extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public EditarCategoria() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public static void main(String[] args) {
        EditarCategoria dialog = new EditarCategoria();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
        System.exit(0);
    }
}
