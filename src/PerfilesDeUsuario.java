public class PerfilesDeUsuario {

    private int codigo;
    private String nombre;
    private int codigoUsuario;


    public PerfilesDeUsuario(String nombre, int codigoUsuario) {
        this.nombre = nombre;
        this.codigoUsuario = codigoUsuario;
    }



    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}
