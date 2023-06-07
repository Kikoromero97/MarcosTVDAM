public class Peliculas extends Contenidos {

    private int director;
    private String tipoContenido;


    public Peliculas(int codigo, String titulo, String descripcion, int duracionSegundos, float valoracion, java.sql.Date anyo_lanzamiento, float presupuesto, int edad_recomendada, java.sql.Date fecha_alta, int director, String tipoContenido) {
        super(codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta);
        this.director = director;
        this.tipoContenido = tipoContenido;
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
