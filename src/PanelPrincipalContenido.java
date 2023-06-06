import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public static void mostrarpanelPrincipalContenido(){
        JFrame frame = new JFrame("PanelPrincipalContenido");
        frame.setContentPane(new PanelPrincipalContenido().JPanelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,500);
        frame.setVisible(true);
    }

    public PanelPrincipalContenido() {

    peliculaButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            verPeliculas.mostrarPatallaVerPelicula();
        }
    });

    documentalButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            verDocumental.mostrarPantallaVerDocumental();
        }
    });


    serieButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            verSeries.mostrarPantallaVerSeries();
        }
    });


    categoriaButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           // VerCategoria.mostarPanelVerCategoria();
        }
    });

    }
}
