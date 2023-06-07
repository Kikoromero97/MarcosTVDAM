import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuPrincipal {
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

    //MAIN CREADO SIMPLEMENTE PARA PODER EJECUTAR PANTALLA Y VER QUE FUNCIONA; CAMBIAR A QUIEN CORRESPONDA POR FUNCIÓN


    public static void main(String[] args){
        if(DBManager.loadDriver() && DBManager.connect()){
            JFrame frame = new JFrame("menuPrincipal");
            frame.setContentPane(new menuPrincipal().JpanelPrincipalOpciones);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }else{
            System.out.println("No funciona nada");
        }

    }

    public menuPrincipal() {
    areaContenidoButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            PanelPrincipalContenido.mostrarpanelPrincipalContenido();
        }
    });


}
}
