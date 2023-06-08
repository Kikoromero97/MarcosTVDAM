import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class CrearDocumental {
    private JPanel JPanelCrearDocumental;
    private JPanel JPanelIntroDatosDocumental;
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

    static JFrame frame = new JFrame("crearDocumental");


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

    public CrearDocumental(verDocumental panelVerDocumental, DB_Contenido contenido_db) {
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (formularioValido() == true) {
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

                    Documental documental = new Documental(cod, titulo, descripcion, duracion, valoracion, fecha, presupuesto, introEdad, fechaAlta, codDirector, introCont);
                    contenido_db.anyadirDocumental(documental);
                    panelVerDocumental.pintarDocumentalTabla();

                    JOptionPane.showMessageDialog(null, "Se ha creado satisfactoriamente el documental "+titulo);
                    frame.dispose();
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

    public static void verPanelCrearDocumental(DB_Contenido contenido_db){
        frame.setContentPane(new CrearDocumental(verDocumental.panelVerDocumental, contenido_db).JPanelCrearDocumental);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(700,500);
    }
}
