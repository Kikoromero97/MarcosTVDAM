import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class verSeries {
    private JPanel JPanelVerSeries;
    private JPanel JPanelBotones;
    private JButton buttonCancelSerie;
    private JButton buttonVolverSerie;
    private JButton crearButtonSerie;
    private JLabel logoMarcosTV;
    private JButton buscarButtonSerie;
    private JTextField introSerie;
    private JTable table1Serie;
    private JPanel JPanelSerie;

    public verSeries() {
        buttonVolverSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelPrincipalContenido.mostrarpanelPrincipalContenido();
            }
        });
    }

    public static void mostrarPantallaVerSeries(){
        JFrame frame = new JFrame("verSeries");
        frame.setContentPane(new verSeries().JPanelSerie);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(700, 500);
    }
}
