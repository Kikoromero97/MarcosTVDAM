import java.util.Date;

public class Peliculas extends Contenidos {
    private int codigoPeliculas;
    private int director;
    private String tipoContenido;


    public Peliculas(int codigo, String titulo, String descripcion, int duracionSegundos, float valoracion, Date anyo_lanzamiento, float presupuesto, int edad_recomendada, Date fecha_alta, int codigoPeliculas, int director, String tipoContenido) {
        super(codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta);
        this.codigoPeliculas = codigoPeliculas;
        this.director = director;
        this.tipoContenido = tipoContenido;
    }

    public int getCodigoPeliculas() {
        return codigoPeliculas;
    }

    public void setCodigoPeliculas(int codigoPeliculas) {
        this.codigoPeliculas = codigoPeliculas;
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
}
