import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

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
                    JOptionPane.showMessageDialog(null, "Debes seleccionar el documental que quieras borrar.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buscarButtonDocumental.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenido_db = new DB_Contenido();
                List<Documental> documental = contenido_db.buscarDocumental(introDocumental.getText());

                if (documental.size() > 0)
                {
                    documentalContenido = new Object[documental.size()][11];
                    for (int i = 0; i < documental.size(); i++) {
                        documentalContenido[i][0] = documental.get(i).getCodigo();
                        documentalContenido[i][1] = documental.get(i).getTitulo();
                        documentalContenido[i][2] = documental.get(i).getDescripcion();
                        documentalContenido[i][3] = documental.get(i).getDuracionSegundos();
                        documentalContenido[i][4] = documental.get(i).getValoracion();
                        documentalContenido[i][5] = documental.get(i).getAnyo_lanzamiento();
                        documentalContenido[i][6] = documental.get(i).getPresupuesto();
                        documentalContenido[i][7] = documental.get(i).getFecha_alta();
                        documentalContenido[i][9] = documental.get(i).getDirector();
                        documentalContenido[i][10] = documental.get(i).getTipoContenido();
                    }
                    DefaultTableModel model = new DefaultTableModel(documentalContenido,columnas);
                    table1Documental.setModel(model);
                }
                else
                {
                    if (introDocumental.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Debes escribir el nombre del documental para poder encontrarlo.","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado ningún documental con el nombre: " + introDocumental.getText(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        verButtonTodasDocumentales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pintarDocumentalTabla();
                introDocumental.setText("");
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
