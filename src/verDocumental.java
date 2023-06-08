import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class verDocumental {
    final String[] columnas = {"CODIGO", "TITULO", "DESCRIPCIÓN", "DURACIÓN", "VALORACIÓN", "AÑO LANZAMIENTO", "PRESUPUESTO", "EDAD RECOMENDADA","FECHA-ALTA", "CODIGO" , "DIRECTOR" , "TIPO CONTENIDO"};

    private static List<Documental> documentals;
    private JPanel JPanelVerDocumental;
    private JButton buttonEliminarDocumental;
    private JButton buttonVolverDocumental;
    private JButton crearButtonDocumental;
    private JButton buscarButtonDocumental;
    private JTextField introDocumental;
    private JTable table1Documental;
    private JPanel JPanelBotones;
    private JLabel logoMarcosTV;
    private JPanel JPanelDocumental;
    private JButton verButtonTodasDocumentales;
    private JButton verButtonTodasDocumental;

    public static Object[][] documentalContenido;
    private static DB_Contenido contenido_db;

    static verDocumental panelVerDocumental = new verDocumental();
    static JFrame frame = new JFrame("verDocumental");

    public static void mostrarPantallaVerDocumental(DB_Contenido db_contenido) {

        frame.setContentPane( panelVerDocumental.JPanelDocumental);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public verDocumental() {
        pintarDocumentalTabla();

    buttonVolverDocumental.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    });


    crearButtonDocumental.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CrearDocumental.verPanelCrearDocumental(contenido_db);
        }
    });

        buttonEliminarDocumental.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int filaSeleccionada = table1Documental.getSelectedRow();

                if (filaSeleccionada != -1) {
                    int cod = (int) documentalContenido[table1Documental.getSelectedRow()][0];
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres borrar el documental " + documentalContenido[table1Documental.getSelectedRow()][1] + "?", "Borrando...", JOptionPane.YES_NO_OPTION);

                    if (respuesta == 0) // Opción Si = borrar
                    {
                        contenido_db.eliminarDocumental(cod);
                        pintarDocumentalTabla();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar el documental que quieras borrar.");
                }
            }
        });
        buscarButtonDocumental.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenido_db = new DB_Contenido();
                Documental documental = contenido_db.buscarDocumental(introDocumental.getText());

                if (documental != null)
                {
                    documentalContenido = new Object[1][11];
                    documentalContenido[0][0] = documental.getCodigo();
                    documentalContenido[0][1] = documental.getTitulo();
                    documentalContenido[0][2] = documental.getDescripcion();
                    documentalContenido[0][3] = documental.getDuracionSegundos();
                    documentalContenido[0][4] = documental.getValoracion();
                    documentalContenido[0][5] = documental.getAnyo_lanzamiento();
                    documentalContenido[0][6] = documental.getPresupuesto();
                    documentalContenido[0][7] = documental.getFecha_alta();
                    documentalContenido[0][9] = documental.getDirector();
                    documentalContenido[0][10] = documental.getTipoContenido();

                    DefaultTableModel model = new DefaultTableModel(documentalContenido,columnas);
                    table1Documental.setModel(model);
                }
                else
                {
                    if (introDocumental.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Debes escribir el nombre del documental para poder encontrarlo.");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado ningún documental con el nombre: " + introDocumental.getText());
                    }
                }
            }
        });

        verButtonTodasDocumentales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintarDocumentalTabla();
            }
        });
    }

    public void pintarDocumentalTabla(){

        contenido_db = new DB_Contenido();
        documentals = contenido_db.printTablaDocumental();
        documentalContenido = new Object[documentals.size()][11];

        for (int i = 0; i < documentals.size(); i++) {
            documentalContenido[i][0] = documentals.get(i).getCodigo();
            documentalContenido[i][1] = documentals.get(i).getTitulo();
            documentalContenido[i][2] = documentals.get(i).getDescripcion();
            documentalContenido[i][3] = documentals.get(i).getDuracionSegundos();
            documentalContenido[i][4] = documentals.get(i).getValoracion();
            documentalContenido[i][5] = documentals.get(i).getAnyo_lanzamiento();
            documentalContenido[i][6] = documentals.get(i).getPresupuesto();
            documentalContenido[i][7] = documentals.get(i).getFecha_alta();
            documentalContenido[i][9] = documentals.get(i).getDirector();
            documentalContenido[i][10] = documentals.get(i).getTipoContenido();
        }
        DefaultTableModel model = new DefaultTableModel(documentalContenido,columnas);
        table1Documental.setModel(model);
    }

}
