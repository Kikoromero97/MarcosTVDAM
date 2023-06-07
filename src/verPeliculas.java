import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class verPeliculas {
    private JPanel JPanelVerPeliculas;
    private JButton buttonCancelPelicula;
    private JButton buttonVolverPeliculas;
    private JButton crearButtonPeliculas;
    private JButton buscarButtonPelicula;
    private JTextField introPelicula;
    private JTable table1Pelicula;
    private JPanel JPanelBotones;
    private JLabel logoMarcosTV;
    private JPanel JPanelPeliculas;

    public static void mostrarPatallaVerPelicula() {
        JFrame frame = new JFrame("verPeliculas");
        frame.setContentPane(new verPeliculas().JPanelPeliculas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public verPeliculas() {

    buscarButtonPelicula.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

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


        buttonCancelPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
