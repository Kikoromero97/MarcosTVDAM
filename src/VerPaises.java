import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VerPaises extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnCrear;
    private JTextField txtFldBuscarNom;
    private JButton btnBuscar;
    private JTable tablaPaises;
    private JButton btnEliminar;

    private static final DBPaises pais = new DBPaises();

    public VerPaises() {
        setContentPane(contentPane);
        UserManager.loadSession();
        setVisible(true);
        setSize(700, 500);
        setTitle("Países");
        setModal(true);
        getRootPane().setDefaultButton(btnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        UIManager.put("OptionPane.yesButtonText", "Confirmar");
        UIManager.put("OptionPane.noButtonText", "Cancelar");
        crearTabla();
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearPais();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PanelPrincipalContenido.mostrarpanelPrincipalContenido();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPais();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarPais();
            }
        });
        tablaPaises.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarInsert(e);
            }
        });
    }

    public void buscarPais() {
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
        if (!pais.existsPais(codigo)) {
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
            if (!pais.existsPais(codigo)) {
                JOptionPane.showMessageDialog(null, "¡Este código no existe!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            } else {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer eliminar este país de la BD?", "Confirmación de borrado", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    if (pais.existsDirectorByPais(codigo)) {
                        JOptionPane.showMessageDialog(null, "Este país ya pertenece a un director. No puede borrarse.", "Error de FK", JOptionPane.ERROR_MESSAGE);
                    } else if (pais.existsIdiomaByPais(codigo)) {
                        JOptionPane.showMessageDialog(null, "Este país ya pertenece a un idioma. No puede borrarse.", "Error de FK", JOptionPane.ERROR_MESSAGE);
                    } else {
                        pais.deletePais(codigo);
                        JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                        txtFldBuscarNom.setText("");
                        crearTabla();
                    }
                }
            }
        }
    }

    public void crearPais() {
        String codigo = JOptionPane.showInputDialog(null, "Ingrese el código del país:", "Registro de usuario", JOptionPane.QUESTION_MESSAGE);
        if (codigo != null && !codigo.isEmpty()) {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del país:", "Registro de usuario", JOptionPane.QUESTION_MESSAGE);
            if (nombre != null && !nombre.isEmpty()) {
                Pais paisNew = new Pais(Integer.parseInt(codigo), nombre);
                pais.crearPais(paisNew);
                JOptionPane.showMessageDialog(null, "Se ha creado el país correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
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
            String nombrePais = JOptionPane.showInputDialog(null, "Ingrese el nombre del país:", nombre);
            if (nombrePais.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe de contener texto.", "Campo vacío", JOptionPane.ERROR_MESSAGE);
            } else {
                Pais paisNew = new Pais(Integer.parseInt(id), nombrePais);
                JOptionPane.showMessageDialog(null, "Se ha editado el país correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                pais.editarPais(paisNew);
                crearTabla();
            }
        }
    }

    public void crearTabla() {
        String[][] tabla = Utilitis.getDataFromResultSet(pais.verPais(), 2);
        String[] columnasVisitas = {"Código", "Nombre"};
        DefaultTableModel table = new DefaultTableModel(Utilitis.deleteNulls(tabla), columnasVisitas);
        tablaPaises.setModel(table);
        Utilitis.centerTable(tablaPaises);
    }

    public void crearTablaEsp(int codigo) {
        String[][] tabla = Utilitis.getDataFromResultSet(pais.verPaisEsp(codigo), 2);
        String[] columnasVisitas = {"Código", "Nombre"};
        DefaultTableModel table = new DefaultTableModel(Utilitis.deleteNulls(tabla), columnasVisitas);
        tablaPaises.setModel(table);
        Utilitis.centerTable(tablaPaises);
    }

    public static void main(String[] args) {
        VerPaises dialog = new VerPaises();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }

    public static void mostrarPais() {
        JDialog dialog = new VerPaises();
    }
}
