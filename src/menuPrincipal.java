import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuPrincipal extends JFrame{
    private JPanel JpanelPrincipalOpciones;
    private JLabel logoMarcosTV;
    private JButton areaDepartamentoButton;
    private JButton areaUsuarioButton;
    private JButton areaContenidoButton;
    private JButton salirButton;
    private JPanel JpanelLogo;
    private JPanel JPanelBotonesPrincipales;
    private JPanel JPanelBotonSalir;
    private JButton areaDirectoresButton;


    public menuPrincipal(){
        super("MARCOS TV");
        setSize(700, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setContentPane(JpanelPrincipalOpciones);


        areaUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JDialog infoUsuario = new InfoUsuario();
                    }
                });
            }
        });
    }
}
