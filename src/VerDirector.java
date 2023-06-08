import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VerDirector extends JDialog {
    private JPanel contentPane;
    private JButton BtnCrear;
    private JButton BtnEliminar;
    private JButton BtnVolver;
    private JTextField txtFldCodigo;
    private JButton BtnBuscar;
    private JTable TablaDirectores;
    private JButton buscarDirectorasButton;
    private static final DBDirector director = new DBDirector();

    public VerDirector() {
        setContentPane(contentPane);
        UserManager.loadSession();
        setTitle("Directores");
        setVisible(true);
        setSize(700, 500);
        setModal(true);
        getRootPane().setDefaultButton(BtnVolver);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        UIManager.put("OptionPane.yesButtonText", "Confirmar");
        UIManager.put("OptionPane.noButtonText", "Cancelar");
        crearTabla();

        BtnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new menuPrincipal();
            }
        });

        BtnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JDialog dialog = new CrearDirector();
            }
        });

        BtnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCampo();
            }
        });

        BtnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCampo();
            }
        });
        TablaDirectores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarInsert(e);
            }
        });
        buscarDirectorasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearTablaOnlyWomen();
            }
        });
    }

    public void buscarCampo() {
        String codSinParsear = txtFldCodigo.getText();
        if (codSinParsear.isEmpty()) {
            crearTabla();
            return;
        }
        int codigo;
        try {
            codigo = Integer.parseInt(txtFldCodigo.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "¡El campo \"código\" debe ser un número!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!director.existsDirector(codigo)) {
            JOptionPane.showMessageDialog(null, "¡Este código no existe!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        } else {
            crearTablaEsp(codigo);
        }
    }

    public void eliminarCampo() {
        String codSinParsear = txtFldCodigo.getText();
        int codigo;
        try {
            codigo = Integer.parseInt(txtFldCodigo.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "¡El campo \"código\" debe ser un número!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (codSinParsear.equals("")) {
            JOptionPane.showMessageDialog(null, "¡El campo \"código\" no puede estar vacío!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!director.existsDirector(codigo)) {
                JOptionPane.showMessageDialog(null, "¡Este código no existe!", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
            } else {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer eliminar este director?", "Confirmación de borrado", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    if (director.existsSeriesByDirector(codigo)) {
                        JOptionPane.showMessageDialog(null, "Este director ya pertenece a un contenido. No puede borrarse.", "Error de FK", JOptionPane.ERROR_MESSAGE);
                    } else {
                        director.deleteDirector(codigo);
                        JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente.", "Realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
                        txtFldCodigo.setText("");
                        crearTabla();
                    }
                }
            }
        }
    }

    public void crearTabla() {
        String[][] tabla = Utilitis.getDataFromResultSet(director.verDirector(), 9);
        String[] columnasVisitas = {"Código", "Nombre", "Apellidos", "Edad", "Nacionalidad", "Género", "Nº de premios", "Años exp.", "idPais"};
        DefaultTableModel table = new DefaultTableModel(Utilitis.deleteNulls(tabla), columnasVisitas);
        TablaDirectores.setModel(table);
        Utilitis.centerTable(TablaDirectores);
    }

    public void crearTablaOnlyWomen() {
        String[][] tabla = Utilitis.getDataFromResultSet(director.verDirectoras(), 4);
        String[] columnasVisitas = {"Nombre", "Apellidos", "Edad", "Nº de premios"};
        DefaultTableModel table = new DefaultTableModel(tabla, columnasVisitas);
        TablaDirectores.setModel(table);
        Utilitis.centerTable(TablaDirectores);
    }

    public void crearTablaEsp(int codigo) {
        String[][] tabla = Utilitis.getDataFromResultSet(director.verDirectorEsp(codigo), 9);
        String[] columnasVisitas = {"Código", "Nombre", "Apellidos", "Edad", "Nacionalidad", "Género", "Nº de premios", "Años exp.", "idPais"};
        DefaultTableModel table = new DefaultTableModel(Utilitis.deleteNulls(tabla), columnasVisitas);
        TablaDirectores.setModel(table);
        Utilitis.centerTable(TablaDirectores);
    }

    public void seleccionarInsert(MouseEvent e) {
        if (e.getClickCount() == 1) {
            JTable table = (JTable) e.getSource();
            int row = table.getSelectedRow();
            String codigo = (String) table.getValueAt(row, 0);
            String nombre = (String) table.getValueAt(row, 1);
            String apellido = (String) table.getValueAt(row, 2);
            String edad = (String) table.getValueAt(row, 3);
            String nacionalidad = (String) table.getValueAt(row, 4);
            String genero = (String) table.getValueAt(row, 5);
            String numpremios = (String) table.getValueAt(row, 6);
            String anyosexp = (String) table.getValueAt(row, 7);
            String idPais = (String) table.getValueAt(row, 8);
            ArrayList<String> datos = new ArrayList<>();
            datos.add(codigo);
            datos.add(nombre);
            datos.add(apellido);
            datos.add(numpremios);
            datos.add(anyosexp);
            datos.add(edad);
            datos.add(genero);
            datos.add(nacionalidad);
            datos.add(idPais);
            EditarDirector edit = new EditarDirector();
            edit.llenarCampos(datos);
            dispose();
        }
    }

    public static void main(String[] args) {
        JDialog dialog = new VerDirector();
        dialog.setVisible(true);
        dialog.setSize(700, 500);
    }

    public static void mostrarDirector() {
        JDialog dialog = new VerDirector();
    }
}
