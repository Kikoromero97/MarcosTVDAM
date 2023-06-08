import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerDepartamento extends JFrame{
    private JPanel panelPrincipal;
    private JLabel logoMARCOSTV;
    private JComboBox comboBoxDepartamentos;
    private JTextField txtFldCodigo;
    private JTextField txtFldNombre;
    private JTextArea txtDescripcion;
    private JButton areaDeEmpleadosButton;
    private JButton modificarButton;
    private JButton volverButton;
    private JButton crearButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("VerDepartamento");
        frame.setContentPane(new VerDepartamento().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void mostrarVerDepartamento() {
        JFrame frame = new JFrame("VerDepartamento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setVisible(true);
    }

    public VerDepartamento() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   menuPrincipal.mostrarmenuPrincipal;
                dispose();
            }
        });
        areaDeEmpleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  AreaDeEmpleados.mostrarAreaDeEmpleados();
                dispose();
            }
        });

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearDepartamento.mostrarCrearDepartamento();
                dispose();
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              // ModificarDepartamento.mostrarModificarDepartamento();
                dispose();
            }
        });

        comboBoxDepartamentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBoxDepartamentos.getSelectedItem();
                DBDepartamento deb = new DBDepartamento();
                ResultSet rs = deb.verDespecifico(selectedItem);

                try {
                    if (rs.next()) {
                        String value1 = rs.getString("columna1"); // Reemplaza "columna1" con el nombre real de la columna
                        String value2 = rs.getString("columna2"); // Reemplaza "columna2" con el nombre real de la columna
                        int value3 = rs.getInt("columna3"); // Reemplaza "columna3" con el nombre real de la columna

                        txtFldCodigo.setText(value1);
                        txtFldNombre.setText(value2);
                        txtDescripcion.setText(String.valueOf(value3));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }


        });
    }
}
