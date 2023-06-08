public class Episodio {
    int idEpisodio;
    int idTemporada;
    int idSerie;
    String nombre;
    java.sql.Date fechaDeLanzamiento;
    int duracion;
    String descripcion;

    public Episodio(int idEpisodio, int idTemporada, int idSerie, String nombre, java.sql.Date fechaDeLanzamiento, int duracion, String descripcion) {
        this.idEpisodio = idEpisodio;
        this.idTemporada = idTemporada;
        this.idSerie = idSerie;
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
        this.duracion = duracion;
        this.descripcion = descripcion;
    }

    public int getIdEpisodio()
    {
        return idEpisodio;
    }

    public int getIdTemporada()
    {
        return idTemporada;
    }

    public int getIdSerie()
    {
        return idSerie;
    }

    public String getNombreEpisodio()
    {
        return nombre;
    }

    public java.sql.Date getFechaEpisodio()
    {
        return fechaDeLanzamiento;
    }

    public int getDuracionEpisodio()
    {
        return duracion;
    }

    public String getDescripcionEpisodio()
    {
        return descripcion;
    }
}
