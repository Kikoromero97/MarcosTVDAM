import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class verTemporada {
    final String[] columnas = {"CODIGO", "CODIGO SERIE", "RESUMEN"};

    private static List<Temporada> temps;
    private JPanel JPanelVerTemporada;
    private JButton buttonEliminarTemporada;
    private JButton buttonVolverTemporada;
    private JButton crearButtonTemporada;
    private JButton buscarButtonTemporada;
    private JTextField introTemporada;
    private JTable table1Temporada;
    private JPanel JPanelBotones;
    private JLabel logoMarcosTV;
    private JPanel JPanelTemporada;
    private JButton verButtonTodasTemporada;

    private  JButton verEpisodiosButton;

    public static Object[][] temporadaContenido;
    private static DB_Contenido contenido_db;

    static verTemporada panelVerTemporada;
    static JFrame frame = new JFrame("verTemporada");

    static CrearTemporada crearTemporada;
    static VerEpisodio panelverEpisodio;
    static private int idSerie;

    public void mostrarPantallaVerTemporada(DB_Contenido db_contenido, int codigoSerie) {

        idSerie = codigoSerie;
        frame.setContentPane(this.JPanelTemporada);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        panelVerTemporada = this;
    }

    public static void setIdSerie(int idSerie) {
        verTemporada.idSerie = idSerie;
    }

    public verTemporada() {
        pintarTemporadaTabla();

        panelverEpisodio = new VerEpisodio();


        buttonVolverTemporada.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    });


    crearButtonTemporada.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            crearTemporada = new CrearTemporada(panelVerTemporada, contenido_db);
            crearTemporada.verPanelCrearTemporada();
        }
    });

        buttonEliminarTemporada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table1Temporada.getSelectedRow();

                if (filaSeleccionada != -1) {
                    int numeroTemporada = (int) temporadaContenido[table1Temporada.getSelectedRow()][0];
                    int codigoSerie = (int) temporadaContenido[table1Temporada.getSelectedRow()][1];
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres borrar la temporada " + numeroTemporada + "?", "Borrando...", JOptionPane.YES_NO_OPTION);
                    if (respuesta == 0) // Opción Si = borrar
                    {
                        contenido_db.eliminarTemporada(numeroTemporada, codigoSerie);
                        pintarTemporadaTabla();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar la temporada que quieras borrar.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buscarButtonTemporada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (introTemporada.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Debes escribir el número de la temporada para poder encontrarla.","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    Temporada temporada = contenido_db.buscarTemporada(introTemporada.getText());

                    if (temporada != null) {
                        temporadaContenido = new Object[1][3];
                        temporadaContenido[0][0] = temporada.getNumeroTemporada();
                        temporadaContenido[0][1] = temporada.getCodSerie();
                        temporadaContenido[0][2] = temporada.getResumen();

                        DefaultTableModel model = new DefaultTableModel(temporadaContenido, columnas);
                        table1Temporada.setModel(model);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No existe la temporada " + introTemporada.getText(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        verButtonTodasTemporada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintarTemporadaTabla();
            }
        });
        verEpisodiosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table1Temporada.getSelectedRow();

                if (filaSeleccionada != -1)
                {
                    int codigoTemporada  = (int) temporadaContenido[filaSeleccionada][0];
                    int codigoSerie = (int)  temporadaContenido[filaSeleccionada][1];


                    panelverEpisodio.mostrarPantallaVerEpisodio(contenido_db, codigoSerie,codigoTemporada);
                    panelverEpisodio.pintarEpisodioTabla();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar una temporada de la que ver los capítulos","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void pintarTemporadaTabla(){

        contenido_db = new DB_Contenido();
        temps = contenido_db.printTablaTemporada(idSerie);
        temporadaContenido = new Object[temps.size()][3];

        for (int i = 0; i < temps.size(); i++) {
            temporadaContenido[i][0] = temps.get(i).getNumeroTemporada();
            temporadaContenido[i][1] = temps.get(i).getCodSerie();
            temporadaContenido[i][2] = temps.get(i).getResumen();
        }
        DefaultTableModel model = new DefaultTableModel(temporadaContenido,columnas);
        table1Temporada.setModel(model);
    }

}
