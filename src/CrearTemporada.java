import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearTemporada {
    private JPanel JPaneGeneral;
    private JPanel JPapnelTitulo;

    private JPanel JPanelInfo;

    private JTextField introNumTemporada;
    private JTextField introNumSerie;
    private JTextField introNumResumen;

    private JButton anyadirButton;

    private JButton cancelarButton;

    static JFrame frame = new JFrame("crearTemporada");


    private boolean formularioValido()
    {
        if (!validarCamposNoVacios(introNumTemporada.getText())
                || !validarCamposNoVacios(introNumSerie.getText())
                || !validarCamposNoVacios(introNumResumen.getText()))
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

    public CrearTemporada(verTemporada panelVerTemporada, DB_Contenido contenido_db) {
        anyadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (formularioValido() == true) {
                    boolean creado = false;

                    int numTemporada = Integer.parseInt(introNumTemporada.getText());
                    int codigoSerie = Integer.parseInt(introNumSerie.getText());
                    String resumen = introNumResumen.getText();

                    Temporada temporada = new Temporada (numTemporada, codigoSerie, resumen);

                    creado = contenido_db.anyadirTemporada(temporada);

                    if (creado == true)
                    {
                        panelVerTemporada.pintarTemporadaTabla();
                        JOptionPane.showMessageDialog(null, "Se ha creado satisfactoriamente la nueva  temporada ","Información",JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se ha podido crear la nueva  temporada ","Error",JOptionPane.ERROR_MESSAGE);
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

    public void verPanelCrearTemporada(){
        frame.setContentPane(this.JPaneGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(700,500);
    }
}
