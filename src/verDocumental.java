import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class verDocumental {
    private JPanel JPanelVerDocumental;
    private JPanel JPanelBotones;
    private JButton buttonCancelDocumental;
    private JButton buttonVolverDocumental;
    private JButton crearButtonDocumental;
    private JLabel logoMarcosTV;
    private JButton buscarButtonDocumental;
    private JTextField introDocumental;
    private JTable table1Documental;
    private JPanel JPanelppVerDocumental;

    public verDocumental() {
        crearButtonDocumental.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonVolverDocumental.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelPrincipalContenido.mostrarpanelPrincipalContenido();
            }
        });
    }

    public static void mostrarPantallaVerDocumental(){
        JFrame frame = new JFrame("verDocumental");
        frame.setContentPane(new verDocumental().JPanelppVerDocumental);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(700,500);
    }


}
