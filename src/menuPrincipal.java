import javax.swing.*;

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("menuPrincipal");
        frame.setContentPane(new menuPrincipal().JpanelPrincipalOpciones);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public menuPrincipal() {
        super("menuPrincipal");
        setContentPane(JpanelPrincipalOpciones);
    }
}
