import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class CrearEpisodio {
    private JPanel JPanelGeneral;
    private JPanel JPapnelTitulo;

    private JPanel JPanelInfo;

    private JTextField introID;
    private JTextField introTemporada;
    private JTextField introIDserie;
    private JTextField introNombre;
    private JTextField introFechaLanzamiento;
    private JTextField introDuracion;
    private JTextField introDescripcion;

    private JButton anyadirButton;

    private JButton cancelarButton;

    static JFrame frame = new JFrame("crearEpisodio");


    private boolean formularioValido()
    {
        if (!validarCamposNoVacios(introID.getText())
                || !validarCamposNoVacios(introTemporada.getText())
                || !validarCamposNoVacios(introIDserie.getText())
                || !validarCamposNoVacios(introNombre.getText())
                || !validarCamposNoVacios(introFechaLanzamiento.getText())
                || !validarCamposNoVacios(introDuracion.getText())
                || !validarCamposNoVacios(introDescripcion.getText()))
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

    public CrearEpisodio(VerEpisodio panelVerEpisodio, DB_Contenido contenido_db) {
        anyadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (formularioValido() == true) {
                    boolean creado = false;

                    int idEpisodio = Integer.parseInt(introID.getText());;
                    int idTemporada = Integer.parseInt(introTemporada.getText());;
                    int idSerie  = Integer.parseInt(introIDserie.getText());;;
                    String nombre = introNombre.getText();
                    java.sql.Date fechaDeLanzamiento = Date.valueOf(introFechaLanzamiento.getText());
                    int duracion  = Integer.parseInt(introDuracion.getText());;;
                    String descripcion = introDescripcion.getText();

                    Episodio episodio = new Episodio(idEpisodio, idTemporada, idSerie, nombre,fechaDeLanzamiento,duracion, descripcion);

                    creado = contenido_db.anyadirEpisodio(episodio);

                    if (creado == true)
                    {
                        panelVerEpisodio.pintarEpisodioTabla();
                        JOptionPane.showMessageDialog(null, "Se ha creado satisfactoriamente el nuevo  episodio ","Información",JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se ha podido crear el nuevo  episodio ","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No puede haber campos vacios","Error",JOptionPane.ERROR_MESSAGE);
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

    public void verPanelCrearEpisodio(){
        frame.setContentPane(this.JPanelGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(700,500);
    }
}
