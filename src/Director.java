public class Director {
    private int codigo;
    private String nombre;
    private String apellidos;
    private int numPremios;
    private int anyosExp;
    private int edad;
    private generoDirector genero;
    private String nacionalidad;
    private String pais;

    public Director(int codigo, String nombre, String apellidos, int numPremios, int anyosExp, int edad, generoDirector genero, String nacionalidad, String pais) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numPremios = numPremios;
        this.anyosExp = anyosExp;
        this.edad = edad;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
        this.pais = pais;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }

    public int getNumPremios() {
        return numPremios;
    }

    public int getAnyosExp() {
        return anyosExp;
    }

    public int getEdad() {
        return edad;
    }

    public generoDirector getGenero() {
        return genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getPais() {
        return pais;
    }
}
