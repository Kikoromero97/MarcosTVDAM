import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class CrearSerie {
    private JPanel JPanelCrearSerie;
    private JPanel JPanelIntroDatosSerie;
    private JFormattedTextField introCodigo;
    private JFormattedTextField introFecha;
    private JFormattedTextField introTitulo;
    private JFormattedTextField introValoracion;
    private JFormattedTextField introFechaAlta;
    private JFormattedTextField introContenido;
    private JFormattedTextField IntroEdadRecomendada;
    private JFormattedTextField introPresupuesto;
    private JFormattedTextField introDescripcion;
    private JFormattedTextField introCodDirector;
    private JFormattedTextField introDuracion;
    private JLabel logoMarcosTV;
    private JButton crearButton;
    private JButton cancelarButton;
    private JButton temporadaButton;
    private JButton episodioButton;

    static JFrame frame = new JFrame("crearSerie");


    private boolean formularioValido()
    {
        if (!validarCamposNoVacios(introFecha.getText())
                || !validarCamposNoVacios(introTitulo.getText())
                || !validarCamposNoVacios(introValoracion.getText())
                || !validarCamposNoVacios(introValoracion.getText())
                || !validarCamposNoVacios(introFechaAlta.getText())
                || !validarCamposNoVacios(introContenido.getText())
                || !validarCamposNoVacios(IntroEdadRecomendada.getText())
                || !validarCamposNoVacios(introPresupuesto.getText())
                || !validarCamposNoVacios(introDescripcion.getText())
                || !validarCamposNoVacios(introCodDirector.getText())
                || !validarCamposNoVacios(introDuracion.getText()))
        {
            return  false;
        }
        else
        {
            return true;
        }
    }
    private boolean validarCamposNoVacios (String texto)
    {
        if (texto.equals(""))
        {
            return false;
        }
        return true;

    }

    public CrearSerie(verSeries panelVerSeries, DB_Contenido contenido_db) {
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (formularioValido() == true) {
                    boolean creado = false;

                    int cod = Integer.parseInt(introCodigo.getText());
                    java.sql.Date fecha = java.sql.Date.valueOf(introFecha.getText());
                    String titulo = introTitulo.getText();
                    float valoracion = Integer.parseInt(introValoracion.getText());
                    java.sql.Date fechaAlta = java.sql.Date.valueOf(introFechaAlta.getText());
                    String introCont = introContenido.getText();
                    int introEdad = Integer.parseInt(IntroEdadRecomendada.getText());
                    float presupuesto = Float.parseFloat(introPresupuesto.getText());
                    String descripcion = introDescripcion.getText();
                    int codDirector = Integer.parseInt(introCodDirector.getText());
                    int duracion = Integer.parseInt(introDuracion.getText());

                    Series serie = new Series(cod, titulo, descripcion, duracion, valoracion, fecha, presupuesto, introEdad, fechaAlta, codDirector, introCont);
                    creado = contenido_db.anyadirSerie(serie);

                    if (creado == true)
                    {
                        panelVerSeries.pintarSeriesTabla();

                        JOptionPane.showMessageDialog(null, "Se ha creado satisfactoriamente la película " + titulo);
                        frame.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se ha podido crear la nueva  serie ");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No puede haber campos vacios");
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            frame.dispose();
            }
        });
    }

    public static void verPanelCrearSerie(DB_Contenido contenido_db){
        frame.setContentPane(new CrearSerie(verSeries.panelVerSeries, contenido_db).JPanelCrearSerie);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(700,500);
    }
}
