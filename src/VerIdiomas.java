import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerIdiomas extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnCrear;
    private JTextField txtFldBuscarNom;
    private JButton btnBuscar;
    private JTable tablaIdiomas;
    private JButton btnEliminar;
    private static final DBIdioma idioma = new DBIdioma();

    public VerIdiomas() {
        setContentPane(contentPane);
        UserManager.loadSession();
        setVisible(true);
        setSize(700, 500);
        setTitle("Idiomas");
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        UIManager.put("OptionPane.yesButtonText", "Confirmar");
        UIManager.put("OptionPane.noButtonText", "Cancelar");
        crearTabla();
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new PanelPrincipalContenido();
            }
        });
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearPais();
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarIdioma();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarPais();
            }
        });
        tablaIdiomas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarInsert(e);
            }
        });
    }

    public void buscarIdioma() {
        String codSinParsear = txtFldBuscarNom.getText();
        if (codSinParsear.isEmpty()) {
            crearTabla();
            return;
        }
        int codigo;
        try {
            codigo = Integer.parseInt(txtFldBuscarNom.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "¡El campo \"id\" debe ser un número!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!idioma.existsIdioma(codigo)) {
            JOptionPane.showMessageDialog(null, "¡Este código no existe!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        } else {
            crearTablaEsp(codigo);
        }
    }

    public void borrarPais() {
        String codSinParsear = txtFldBuscarNom.getText();
        int codigo;
        try {
            codigo = Integer.parseInt(txtFldBuscarNom.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "¡El campo \"id\" debe ser un número!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (codSinParsear.equals("")) {
            JOptionPane.showMessageDialog(null, "¡El campo \"id\" no puede estar vacío!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!idioma.existsIdioma(codigo)) {
                JOptionPane.showMessageDialog(null, "¡Este código no existe!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            } else {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer eliminar este idioma de la BD?", "Confirmación de borrado", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    if (idioma.existsPaisByIdioma(codigo)) {
                        JOptionPane.showMessageDialog(null, "Este idioma ya pertenece a un país. No puede borrarse.", "Error de FK", JOptionPane.ERROR_MESSAGE);
                    } else {
                        idioma.deleteIdioma(codigo);
                        JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                        txtFldBuscarNom.setText("");
                        crearTabla();
                    }
                }
            }
        }
    }

    public void crearPais() {
        String codigo = JOptionPane.showInputDialog(null, "Ingrese el código del idioma:", "Registro de usuario", JOptionPane.QUESTION_MESSAGE);
        if (codigo != null && !codigo.isEmpty()) {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del idioma:", "Registro de usuario", JOptionPane.QUESTION_MESSAGE);
            if (nombre != null && !nombre.isEmpty()) {
                Idioma idiomaNew = new Idioma(Integer.parseInt(codigo), nombre);
                idioma.crearIdioma(idiomaNew);
                JOptionPane.showMessageDialog(null, "Se ha creado el idioma correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                crearTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Debes introducir un nombre.", "Campo vacío", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes introducir un número.", "Campo vacío", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void seleccionarInsert(MouseEvent e) {
        if (e.getClickCount() == 1) {
            JTable table = (JTable) e.getSource();
            int row = table.getSelectedRow();
            String id = (String) table.getValueAt(row, 0);
            String nombre = (String) table.getValueAt(row, 1);
            String nombrePais = JOptionPane.showInputDialog(null, "Ingrese el nombre del idioma:", nombre);
            if (nombrePais.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe de contener texto.", "Campo vacío", JOptionPane.ERROR_MESSAGE);
            } else {
                Idioma idiomaNew = new Idioma(Integer.parseInt(id), nombrePais);
                JOptionPane.showMessageDialog(null, "Se ha editado el idioma correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                idioma.editarIdioma(idiomaNew);
                crearTabla();
            }
        }
    }

    public void crearTabla() {
        String[][] tabla = Utilitis.getDataFromResultSet(idioma.verIdioma(), 2);
        String[] columnasVisitas = {"Código", "Nombre"};
        DefaultTableModel table = new DefaultTableModel(Utilitis.deleteNulls(tabla), columnasVisitas);
        tablaIdiomas.setModel(table);
        Utilitis.centerTable(tablaIdiomas);
    }

    public void crearTablaEsp(int codigo) {
        String[][] tabla = Utilitis.getDataFromResultSet(idioma.verIdiomaEsp(codigo), 2);
        String[] columnasVisitas = {"Código", "Nombre"};
        DefaultTableModel table = new DefaultTableModel(Utilitis.deleteNulls(tabla), columnasVisitas);
        tablaIdiomas.setModel(table);
        Utilitis.centerTable(tablaIdiomas);
    }

    public static void main(String[] args) {
        VerIdiomas dialog = new VerIdiomas();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }

    public static void mostrarIdioma() {
        JDialog dialog = new VerIdiomas();
    }
}
