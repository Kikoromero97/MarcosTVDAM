import javax.swing.*;

public class CrearTarjetas extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnEliminar;
    private JButton btnEditar;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    public CrearTarjetas() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
    }

    public static void main(String[] args) {
        CrearTarjetas dialog = new CrearTarjetas();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
        System.exit(0);
    }
}
