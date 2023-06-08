import java.sql.Date;

public class Episodio {
    int idEpisodio;
    int idTemporada;
    int idSerie;
    String nombre;
    java.sql.Date fechaDeLanzamiento;
    int duracion;
    String descripcion;

    public Episodio(int idEpisodio, int idTemporada, int idSerie, String nombre, Date fechaDeLanzamiento, int duracion, String descripcion) {
        this.idEpisodio = idEpisodio;
        this.idTemporada = idTemporada;
        this.idSerie = idSerie;
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
        this.duracion = duracion;
        this.descripcion = descripcion;
    }
}
