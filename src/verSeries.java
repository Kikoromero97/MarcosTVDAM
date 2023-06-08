import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class verSeries {
    final String[] columnas = {"CODIGO", "TITULO", "DESCRIPCIÓN", "DURACIÓN", "VALORACIÓN", "AÑO LANZAMIENTO", "PRESUPUESTO", "EDAD RECOMENDADA","FECHA-ALTA", "CODIGO PELICULA" , "DIRECTOR" , "TIPO CONTENIDO"};

    private static List<Series> pelis;
    private JPanel JPanelVerSeries;
    private JButton buttonEliminarSerie;
    private JButton buttonVolverSeries;
    private JButton crearButtonSeries;
    private JButton buscarButtonSerie;
    private JTextField introSerie;
    private JTable table1Serie;
    private JPanel JPanelBotones;
    private JLabel logoMarcosTV;
    private JPanel JPanelSeries;
    private JButton verButtonTodasSeries;
    private JButton verTemporadasButton;

    public static Object[][] seriesContenido;
    private static DB_Contenido contenido_db;

    static verSeries panelVerSeries = new verSeries();
    static JFrame frame = new JFrame("verSeries");

    private static verTemporada panelVerTemporada  = new verTemporada();


    public static void mostrarPantallaVerSeries(DB_Contenido db_contenido) {

        frame.setContentPane( panelVerSeries.JPanelSeries);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public verSeries() {
        pintarSeriesTabla();

    buttonVolverSeries.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    });


    crearButtonSeries.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CrearSerie.verPanelCrearSerie(contenido_db);
        }
    });

        buttonEliminarSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int filaSeleccionada = table1Serie.getSelectedRow();

                if (filaSeleccionada != -1) {
                    int cod = (int) seriesContenido[table1Serie.getSelectedRow()][0];
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres borrar esta serie " + seriesContenido[table1Serie.getSelectedRow()][1] + "?", "Borrando...", JOptionPane.YES_NO_OPTION);

                    if (respuesta == 0) // Opción Si = borrar
                    {
                        contenido_db.eliminarSerie(cod);
                        pintarSeriesTabla();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar la serie que quieras borrar.");
                }
            }
        });
        buscarButtonSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Series> serie = contenido_db.buscarSerie(introSerie.getText());

                if (serie.size() > 0)
                {
                    seriesContenido = new Object[serie.size()][11];
                    for (int i = 0; i < serie.size(); i++) {
                        seriesContenido[i][0] = serie.get(i).getCodigo();
                        seriesContenido[i][1] = serie.get(i).getTitulo();
                        seriesContenido[i][2] = serie.get(i).getDescripcion();
                        seriesContenido[i][3] = serie.get(i).getDuracionSegundos();
                        seriesContenido[i][4] = serie.get(i).getValoracion();
                        seriesContenido[i][5] = serie.get(i).getAnyo_lanzamiento();
                        seriesContenido[i][6] = serie.get(i).getPresupuesto();
                        seriesContenido[i][7] = serie.get(i).getFecha_alta();
                        seriesContenido[i][9] = serie.get(i).getDirector();
                        seriesContenido[i][10] = serie.get(i).getTipoContenido();
                    }
                    DefaultTableModel model = new DefaultTableModel(seriesContenido,columnas);
                    table1Serie.setModel(model);
                }
                else
                {
                    if (introSerie.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Debes escribir el nombre de la película para poder encontrarla.");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna película con el nombre: " + introSerie.getText());
                    }
                }
            }
        });

        verButtonTodasSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintarSeriesTabla();
                introSerie.setText("");
            }
        });
        verTemporadasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1Serie.getSelectedRow();

                if (selectedRow == -1) //GetSelectedRow Devuelve -1 si no hay nada seleccionado
                {
                    JOptionPane.showMessageDialog(null, "Para ver las temporadas primero debes seleccionar la serie.");
                }
                else
                {
                    int codigoSerie = (int) seriesContenido[selectedRow][0];
                    verTemporada.setIdSerie(codigoSerie);
                    panelVerTemporada.mostrarPantallaVerTemporada(contenido_db,codigoSerie);
                    panelVerTemporada.pintarTemporadaTabla();

                }
            }
        });
    }

    public void pintarSeriesTabla(){

        contenido_db = new DB_Contenido();
        pelis = contenido_db.printTablaSeries();
        seriesContenido = new Object[pelis.size()][11];

        for (int i = 0; i < pelis.size(); i++) {
            seriesContenido[i][0] = pelis.get(i).getCodigo();
            seriesContenido[i][1] = pelis.get(i).getTitulo();
            seriesContenido[i][2] = pelis.get(i).getDescripcion();
            seriesContenido[i][3] = pelis.get(i).getDuracionSegundos();
            seriesContenido[i][4] = pelis.get(i).getValoracion();
            seriesContenido[i][5] = pelis.get(i).getAnyo_lanzamiento();
            seriesContenido[i][6] = pelis.get(i).getPresupuesto();
            seriesContenido[i][7] = pelis.get(i).getFecha_alta();
            seriesContenido[i][9] = pelis.get(i).getDirector();
            seriesContenido[i][10] = pelis.get(i).getTipoContenido();
        }
        DefaultTableModel model = new DefaultTableModel(seriesContenido,columnas);
        table1Serie.setModel(model);
    }

}
