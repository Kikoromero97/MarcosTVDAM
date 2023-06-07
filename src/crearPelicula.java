import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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

    public crearPelicula() {
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cod = Integer.parseInt(introCodigo.getText());
                String fecha = introFecha.getText();
                String titulo = introTitulo.getText();
                float valoracion = Integer.parseInt(introValoracion.getText());
                String fechaAlta = introFechaAlta.getText();
                String introCont = introContenido.getText();

            }
        });
    }

    public static void verPanelCrearPelicula(){
        JFrame frame = new JFrame("crearPelicula");
        frame.setContentPane(new crearPelicula().JPanelCrearPelicula);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
