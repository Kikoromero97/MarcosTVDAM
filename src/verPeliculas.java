import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class verPeliculas {
    final String[] columnas = {"CODIGO", "TITULO", "DESCRIPCIÓN", "DURACIÓN", "VALORACIÓN", "AÑO LANZAMIENTO", "PRESUPUESTO", "EDAD RECOMENDADA","FECHA-ALTA", "CODIGO PELICULA" , "DIRECTOR" , "TIPO CONTENIDO"};

    private static List<Peliculas> pelis;
    private JPanel JPanelVerPeliculas;
    private JButton buttonEliminarPelicula;
    private JButton buttonVolverPeliculas;
    private JButton crearButtonPeliculas;
    private JButton buscarButtonPelicula;
    private JTextField introPelicula;
    private JTable table1Pelicula;
    private JPanel JPanelBotones;
    private JLabel logoMarcosTV;
    private JPanel JPanelPeliculas;
    private JButton verButtonTodasPeliculas;

    public static Object[][] peliculasContenido;
    private static DB_Contenido contenido_db;

    static verPeliculas panelVerPeliculas = new verPeliculas();
    static JFrame frame = new JFrame("verPeliculas");

    public static void mostrarPatallaVerPelicula(DB_Contenido db_contenido) {

        frame.setContentPane( panelVerPeliculas.JPanelPeliculas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public verPeliculas() {
        pintarPeliculasTabla();

    buttonVolverPeliculas.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    });


    crearButtonPeliculas.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            crearPelicula.verPanelCrearPelicula(contenido_db);
        }
    });

        buttonEliminarPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cod = (int) peliculasContenido[table1Pelicula.getSelectedRow()][0];
                contenido_db.eliminarPelicula(cod);
                pintarPeliculasTabla();
            }
        });
        buscarButtonPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Peliculas pelicula = contenido_db.buscarPelicula(introPelicula.getText());

                if (pelicula != null)
                {
                    peliculasContenido = new Object[1][11];
                    peliculasContenido[0][0] = pelicula.getCodigo();
                    peliculasContenido[0][1] = pelicula.getTitulo();
                    peliculasContenido[0][2] = pelicula.getDescripcion();
                    peliculasContenido[0][3] = pelicula.getDuracionSegundos();
                    peliculasContenido[0][4] = pelicula.getValoracion();
                    peliculasContenido[0][5] = pelicula.getAnyo_lanzamiento();
                    peliculasContenido[0][6] = pelicula.getPresupuesto();
                    peliculasContenido[0][7] = pelicula.getFecha_alta();
                    peliculasContenido[0][9] = pelicula.getDirector();
                    peliculasContenido[0][10] = pelicula.getTipoContenido();

                    DefaultTableModel model = new DefaultTableModel(peliculasContenido,columnas);
                    table1Pelicula.setModel(model);
                }
                else
                {
                    if (introPelicula.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Debes escribir el nombre de la película para poder encontrarla.");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna película con el nombre: " + introPelicula.getText());
                    }
                }
            }
        });
        verButtonTodasPeliculas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintarPeliculasTabla();
            }
        });
    }

    public void pintarPeliculasTabla(){

        contenido_db = new DB_Contenido();
        pelis = contenido_db.printTablaPeliculas();
        peliculasContenido = new Object[pelis.size()][11];

        for (int i = 0; i < pelis.size(); i++) {
            peliculasContenido[i][0] = pelis.get(i).getCodigo();
            peliculasContenido[i][1] = pelis.get(i).getTitulo();
            peliculasContenido[i][2] = pelis.get(i).getDescripcion();
            peliculasContenido[i][3] = pelis.get(i).getDuracionSegundos();
            peliculasContenido[i][4] = pelis.get(i).getValoracion();
            peliculasContenido[i][5] = pelis.get(i).getAnyo_lanzamiento();
            peliculasContenido[i][6] = pelis.get(i).getPresupuesto();
            peliculasContenido[i][7] = pelis.get(i).getFecha_alta();
            peliculasContenido[i][9] = pelis.get(i).getDirector();
            peliculasContenido[i][10] = pelis.get(i).getTipoContenido();
        }
        DefaultTableModel model = new DefaultTableModel(peliculasContenido,columnas);
        table1Pelicula.setModel(model);
    }

}
