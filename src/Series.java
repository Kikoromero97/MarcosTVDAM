import java.util.ArrayList;

public class Series extends Contenidos {

    private int director;
    private String tipoContenido;

    private ArrayList<Temporada> temporadas;


    public Series(int codigo, String titulo, String descripcion, int duracionSegundos, float valoracion, java.sql.Date anyo_lanzamiento, float presupuesto, int edad_recomendada, java.sql.Date fecha_alta, int director, String tipoContenido) {
        super(codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta);
        this.director = director;
        this.tipoContenido = tipoContenido;
        temporadas = new ArrayList<Temporada>();
    }

    public int getDirector() {
        return director;
    }

    public void setDirector(int director) {
        this.director = director;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public void anyadirTemporada(Temporada temporada)
    {
        temporadas.add(temporada);
    }

    public void eliminarTemporada(int numTemporada)
    {
        for (int i = 0; i < temporadas.size(); i++)
        {
            if (temporadas.get(i).getNumeroTemporada() == numTemporada)
            {
                temporadas.remove(temporadas.get(i));
            }
        }

    }
}
