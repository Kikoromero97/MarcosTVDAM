import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelPrincipalContenido {
    public JPanel JPanelVerPeliculas;
    private JPanel JPanelPrincipal;
    private JPanel JPanelBotones;
    private JButton paisButton;
    private JButton idiomaButton;
    private JButton peliculaButton;
    private JButton categoriaButton;
    private JButton serieButton;
    private JButton documentalButton;
    private JButton salirButton;
    private JLabel logoMarcosTV;
    private static DB_Contenido dbContenido = new DB_Contenido();

    private static verPeliculas panelVerPeliculas = new verPeliculas();
    private static verDocumental panelVerDocumental = new verDocumental();
    private static verSeries panelVerSeries  = new verSeries();

    private static JFrame frame = new JFrame("PanelPrincipalContenido");



    public static void mostrarpanelPrincipalContenido(){
        frame.setContentPane(new PanelPrincipalContenido().JPanelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    public PanelPrincipalContenido() {

    peliculaButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            dbContenido = new DB_Contenido();
            panelVerPeliculas.mostrarPantallaVerPelicula(dbContenido);
            dbContenido.printTablaPeliculas();

        }
    });

    documentalButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dbContenido = new DB_Contenido();
            panelVerDocumental.mostrarPantallaVerDocumental(dbContenido);
            dbContenido.printTablaDocumental();
        }
    });


    serieButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            dbContenido = new DB_Contenido();
            panelVerSeries.mostrarPantallaVerSeries(dbContenido);
            dbContenido.printTablaSeries();
        }
    });


    categoriaButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           // VerCategoria.mostarPanelVerCategoria();
        }
    });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new menuPrincipal();
                frame.dispose();
            }
        });
    }


}
