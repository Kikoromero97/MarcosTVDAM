import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VerCategoria extends JDialog {
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnEliminar;
    private JButton btnCrear;
    private JTextField txtFldNombre;
    private JButton btnBuscar;
    private JTable tablaCat;

    public VerCategoria() {
        setContentPane(contentPane);
        setVisible(true);
        setSize(700, 500);
        setTitle("Categorías");
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
                PanelPrincipalContenido.mostrarpanelPrincipalContenido();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCampo();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCampo();
            }
        });

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new CrearCategoria();
            }
        });

        tablaCat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarInsert(e);
            }
        });
    }

    public void buscarCampo() {
        DBCategorias categorias = new DBCategorias();
        String codSinParsear = txtFldNombre.getText();
        if (codSinParsear.isEmpty()) {
            crearTabla();
            return;
        }
        int codigo;
        try {
            codigo = Integer.parseInt(txtFldNombre.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "¡El campo \"código\" debe ser un número!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!categorias.existsCategoria(codigo)) {
            JOptionPane.showMessageDialog(null, "¡Este código no existe!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        } else {
            crearTablaEsp(codigo);
        }
    }

    public void eliminarCampo() {
        DBCategorias categorias = new DBCategorias();
        int codigo;
        String codSinParsear = txtFldNombre.getText();
        try {
            codigo = Integer.parseInt(txtFldNombre.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "¡El campo \"código\" debe ser un número!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (codSinParsear.equals("")) {
            JOptionPane.showMessageDialog(null, "¡El campo \"código\" no puede estar vacío!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!categorias.existsCategoria(codigo)) {
                JOptionPane.showMessageDialog(null, "¡Este código no existe!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            } else {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer eliminar esta categoría?", "Confirmación de borrado", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    categorias.deleteCategoria(codigo);
                    JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                    txtFldNombre.setText("");
                    crearTabla();
                }
            }
        }
    }

    public void seleccionarInsert(MouseEvent e) {
        if (e.getClickCount() == 1) {
            JTable table = (JTable) e.getSource();
            int row = table.getSelectedRow();
            String codigo = (String) table.getValueAt(row, 0);
            String nombre = (String) table.getValueAt(row, 1);
            String descripcion = (String) table.getValueAt(row, 2);
            ArrayList<String> datos = new ArrayList<>();
            datos.add(codigo);
            datos.add(nombre);
            datos.add(descripcion);
            EditarCategoria edit = new EditarCategoria();
            edit.llenarCampos(datos);
            dispose();
        }
    }

    public void crearTabla() {
        DBCategorias categoria = new DBCategorias();
        String[][] tabla = Utilitis.getDataFromResultSet(categoria.verCategorias(), 3);
        String[] columnasVisitas = {"Código", "Nombre", "Descripción"};
        DefaultTableModel table = new DefaultTableModel(Utilitis.deleteNulls(tabla), columnasVisitas);
        tablaCat.setModel(table);
        Utilitis.centerTable(tablaCat);

    }

    public void crearTablaEsp(int codigo) {
        DBCategorias categoria = new DBCategorias();
        String[][] tabla = Utilitis.getDataFromResultSet(categoria.verCategoriaEsp(codigo), 3);
        String[] columnasVisitas = {"Código", "Nombre", "Descripción"};
        DefaultTableModel table = new DefaultTableModel(Utilitis.deleteNulls(tabla), columnasVisitas);
        tablaCat.setModel(table);
        Utilitis.centerTable(tablaCat);
    }

    public static void main(String[] args) {
        VerCategoria dialog = new VerCategoria();
        dialog.setSize(700, 500);
        dialog.setVisible(true);
    }

    public static void mostrarPanelVerCategoria() {
        VerCategoria dialog = new VerCategoria();
    }

}
