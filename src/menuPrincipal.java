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

    public menuPrincipal() {
        super("MARCOS TV");
        setSize(700, 500);
        setVisible(true);
        setContentPane(JpanelPrincipalOpciones);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        switch (UserManager.user.getRol()){
            case atc -> {
                areaDepartamentoButton.setEnabled(false);
                areaContenidoButton.setEnabled(false);
                areaDirectoresButton.setEnabled(false);
            }
            case rrhh -> {
                areaUsuarioButton.setEnabled(false);
                areaContenidoButton.setEnabled(false);
                areaDirectoresButton.setEnabled(false);
            }
            case calidad -> {
                areaUsuarioButton.setEnabled(false);
                areaDepartamentoButton.setEnabled(false);
            }
        }
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserManager.exitSession();
                inicioSesion.mostrarInicioSession();
                dispose();
            }
        });
        areaUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // SeleccionUser.mostrarUsuarios();
                dispose();
            }
        });
        areaContenidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // PanelPrincipalContenido.mostrarpanelPrincipalContenido();
                dispose();
            }
        });
        areaDepartamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // VerDepartamento.mostrarDepartamentos();
                dispose();
            }
        });
        areaDirectoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // VerDirector.mostrarDirector();
                dispose();
            }
        });
    }
    public static void  mostrarMenuPrincipal(){
        JFrame frame = new menuPrincipal();
    }
}
