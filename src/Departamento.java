public class Departamento {

    private String descripcion;
    private String nombre;
    private String departamento;
    private int codigo;


    /**
     * Constructor de la Class.
     *
     * @param descripcion Descripción.
     * @param nombre Nombre del Departamento.
     * @param departamento Departamento.
     * @param codigo Código del Departamento.
     */
    public Departamento(String descripcion, String nombre, String departamento, int codigo) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.departamento = departamento;
        this.codigo = codigo;
    }

    /**
     * Getter de la descripción del departamento.
     *
     * @return String.
     */

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Getter del nombre del departamento.
     *
     * @return String.
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * Getter del departamento.
     *
     * @return String.
     */

    public String getDepartamento() {
        return departamento;
    }

    /**
     * Getter del código del departamento.
     *
     * @return String.
     */

    public int getCodigo() {
        return codigo;
    }


}
