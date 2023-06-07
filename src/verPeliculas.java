import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class verPeliculas {
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

    public static Object[][] peliculasContenido;
    private static DB_Contenido contenido_db;

    public static void mostrarPatallaVerPelicula(DB_Contenido db_contenido) {
        contenido_db = db_contenido;
        JFrame frame = new JFrame("verPeliculas");
        frame.setContentPane(new verPeliculas().JPanelPeliculas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public verPeliculas() {
        pintarPeliculasTabla();

    buscarButtonPelicula.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int cod= Integer.parseInt(introPelicula.getText());
        }
    });


    buttonVolverPeliculas.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            PanelPrincipalContenido.mostrarpanelPrincipalContenido();
        }
    });


    crearButtonPeliculas.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            crearPelicula.verPanelCrearPelicula();
        }
    });


        buttonEliminarPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cod= Integer.parseInt(introPelicula.getText());
                contenido_db.eliminarPelicula(cod);
                pintarPeliculasTabla();
            }
        });
    }

    public void pintarPeliculasTabla(){
        String[] columnas = {"CODIGO", "TITULO", "DESCRIPCIÓN", "DURACIÓN", "VALORACIÓN", "AÑO LANZAMIENTO", "PRESUPUESTO", "EDAD RECOMENDADA","FECHA-ALTA", "CODIGO PELICULA" , "DIRECTOR" , "TIPO CONTENIDO"};
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
            peliculasContenido[i][8] = pelis.get(i).getCodigoPeliculas();
            peliculasContenido[i][9] = pelis.get(i).getDirector();
            peliculasContenido[i][10] = pelis.get(i).getTipoContenido();
        }
        DefaultTableModel model = new DefaultTableModel(peliculasContenido,columnas);
        table1Pelicula.setModel(model);

    }

}
