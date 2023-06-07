import java.util.Date;

public class Contenidos {

    private int codigo;

    private String titulo;

    private String descripcion;

    private int duracionSegundos;

    private float valoracion;

    private Date anyo_lanzamiento;

    private float presupuesto;

    private int edad_recomendada;

    private Date fecha_alta;

    public Contenidos(int codigo, String titulo, String descripcion, int duracionSegundos, float valoracion, Date anyo_lanzamiento, float presupuesto, int edad_recomendada, Date fecha_alta) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracionSegundos = duracionSegundos;
        this.valoracion = valoracion;
        this.anyo_lanzamiento = anyo_lanzamiento;
        this.presupuesto = presupuesto;
        this.edad_recomendada = edad_recomendada;
        this.fecha_alta = fecha_alta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public Date getAnyo_lanzamiento() {
        return anyo_lanzamiento;
    }

    public void setAnyo_lanzamiento(Date anyo_lanzamiento) {
        this.anyo_lanzamiento = anyo_lanzamiento;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getEdad_recomendada() {
        return edad_recomendada;
    }

    public void setEdad_recomendada(int edad_recomendada) {
        this.edad_recomendada = edad_recomendada;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    @Override
    public String toString() {
        return "Contenidos{" +
                "codigo=" + codigo +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracionSegundos=" + duracionSegundos +
                ", valoracion=" + valoracion +
                ", anyo_lanzamiento=" + anyo_lanzamiento +
                ", presupuesto=" + presupuesto +
                ", edad_recomendada=" + edad_recomendada +
                ", fecha_alta=" + fecha_alta +
                '}';
    }
}
