import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelPrincipalContenido extends JFrame{
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



    public static void mostrarpanelPrincipalContenido(){
        JFrame frame = new PanelPrincipalContenido();

    }

    public PanelPrincipalContenido() {

        setContentPane(JPanelPrincipal);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,500);
        setVisible(true);
        setLocationRelativeTo(null);
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
           VerCategoria.mostrarPanelVerCategoria();
           dispose();
        }
    });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new menuPrincipal();
                dispose();
            }
        });
        paisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerPaises.mostrarPais();
                dispose();
            }
        });
        idiomaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerIdiomas.mostrarIdioma();
                dispose();
            }
        });
    }


}
