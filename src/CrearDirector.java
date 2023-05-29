import javax.swing.*;
import java.awt.event.*;

public class CrearDirector extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;

    public CrearDirector() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public static void main(String[] args) {
        CrearDirector dialog = new CrearDirector();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
        System.exit(0);
    }
}
