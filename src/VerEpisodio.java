import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerEpisodio {
    final String[] columnas = {"CODIGO", "CODIGO TEMPORADA", "CODIGO SERIE", "NOMBRE", "FECHA DE LANZAMIENTO", "DURACION", "DESCRIPCION"};

    private static List<Episodio> caps;
    private JPanel JPanelVerEpisodios;
    private JButton buttonEliminarEpisodio;
    private JButton buttonVolverEpisodio;
    private JButton crearButtonEpisodio;

    private JButton buscarButtonEpisodio;
    private JTextField introEpisodio;
    private JTable table1Episodio;
    private JPanel JPanelBotones;
    private JLabel logoMarcosTV;
    private JPanel JPanelEpisodio;
    private JButton verButtonTodosEpisodio;

    private  JButton verEpisodiosButton;

    public static Object[][] episodioContenido;
    private static DB_Contenido contenido_db;

    static VerEpisodio panelVerEpisodio;
    static JFrame frame = new JFrame("verEpisodio");

    static CrearEpisodio crearEpisodio;
    static private int idSerie;
    static private int idTemporada;

    public void mostrarPantallaVerEpisodio(DB_Contenido db_contenido, int codigoSerie, int codigoTemporada) {

        idSerie = codigoSerie;
        idTemporada = codigoTemporada;
        frame.setContentPane(this.JPanelEpisodio);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        panelVerEpisodio = this;
    }

    public static void setIdSerieYTemporada(int idSerie, int idTemporada)
    {
        VerEpisodio.idSerie = idSerie;
        VerEpisodio.idTemporada = idTemporada;
    }

    public VerEpisodio() {
        pintarEpisodioTabla();

    buttonVolverEpisodio.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    });


    crearButtonEpisodio.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            crearEpisodio = new CrearEpisodio(panelVerEpisodio, contenido_db);
            crearEpisodio.verPanelCrearEpisodio();
        }
    });

        buttonEliminarEpisodio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExecutionControl.NotImplementedException("TODO");

                int filaSeleccionada = table1Episodio.getSelectedRow();

                if (filaSeleccionada != -1) {
                    int numeroEpisodio = (int) episodioContenido[table1Episodio.getSelectedRow()][0];
                    int codigoTemporada = (int) episodioContenido[table1Episodio.getSelectedRow()][1];
                    int codigoSerie = (int) episodioContenido[table1Episodio.getSelectedRow()][2];

                    String nombreEpisodio = (String) episodioContenido[table1Episodio.getSelectedRow()][3];
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres borrar el episodio " + nombreEpisodio + "?", "Borrando...", JOptionPane.YES_NO_OPTION);
                    if (respuesta == 0) // Opción Si = borrar
                    {
                        contenido_db.eliminarEpisodio(numeroEpisodio, codigoTemporada, codigoSerie);
                        pintarEpisodioTabla();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar el episodio que quieras borrar.");
                }


            }
        });
        buscarButtonEpisodio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExecutionControl.NotImplementedException("TODO");

                if (introEpisodio.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Debes escribir el número del episodio para poder encontrarlo.");
                }
                else {
                    List<Episodio> episodio = contenido_db.buscarEpisodio(introEpisodio.getText());

                    if (episodio.size() > 0)
                    {
                        episodioContenido = new Object[episodio.size()][7];

                        for (int i = 0; i < episodio.size(); i++) {
                            episodioContenido[i][0] = episodio.get(i).getIdEpisodio();
                            episodioContenido[i][1] = episodio.get(i).getIdTemporada();
                            episodioContenido[i][2] = episodio.get(i).getIdSerie();
                            episodioContenido[i][3] = episodio.get(i).getNombreEpisodio();
                            episodioContenido[i][4] = episodio.get(i).getFechaEpisodio();
                            episodioContenido[i][5] = episodio.get(i).getDuracionEpisodio();
                            episodioContenido[i][6] = episodio.get(i).getDescripcionEpisodio();
                        }
                        DefaultTableModel model = new DefaultTableModel(episodioContenido,columnas);
                        table1Episodio.setModel(model);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No existe la episodio " + introEpisodio.getText());
                    }
                }


            }
        });
        verButtonTodosEpisodio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintarEpisodioTabla();
                introEpisodio.setText("");
            }
        });
    }

    public void pintarEpisodioTabla(){

        contenido_db = new DB_Contenido();
        caps = contenido_db.printTablaEpisodio(idSerie,idTemporada);
        episodioContenido = new Object[caps.size()][7];

        for (int i = 0; i < caps.size(); i++) {
            episodioContenido[i][0] = caps.get(i).getIdEpisodio();
            episodioContenido[i][1] = caps.get(i).getIdTemporada();
            episodioContenido[i][2] = caps.get(i).getIdSerie();
            episodioContenido[i][3] = caps.get(i).getNombreEpisodio();
            episodioContenido[i][4] = caps.get(i).getFechaEpisodio();
            episodioContenido[i][5] = caps.get(i).getDuracionEpisodio();
            episodioContenido[i][6] = caps.get(i).getDescripcionEpisodio();
        }
        DefaultTableModel model = new DefaultTableModel(episodioContenido,columnas);
        table1Episodio.setModel(model);
    }

}
