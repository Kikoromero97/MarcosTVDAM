import javax.swing.*;

public class crearPelicula {
    private JPanel JPanelCrearPelicula;
    private JPanel JPanelIntroDatosPelicula;
    private JFormattedTextField introCodigo;
    private JFormattedTextField introFecha;
    private JFormattedTextField introTitulo;
    private JFormattedTextField introValoracion;
    private JFormattedTextField introFechaAlta;
    private JFormattedTextField introContenido;
    private JFormattedTextField IntroEdadRecomendada;
    private JFormattedTextField introPresupuesto;
    private JFormattedTextField introDescripcion;
    private JFormattedTextField introNombreDirector;
    private JFormattedTextField introApellidosDIrector;
    private JFormattedTextField introDuracion;
    private JLabel logoMarcosTV;
    private JButton crearButton;
    private JButton cancelarButton;

    public static void verPanelCrearPelicula(){
        JFrame frame = new JFrame("crearPelicula");
        frame.setContentPane(new crearPelicula().JPanelCrearPelicula);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
