public class UsuariosClientes {


    private String nombre;
    private String telefono;
    private String mail;
    private String edad;

    private String localizacion;

    public UsuariosClientes(String nombre, String telefono, String mail, String edad, String ciudad) throws IncorrectMailException {
        this.nombre = nombre;
        this.telefono = telefono;
        if (!mail.contains("@")){
            throw new IncorrectMailException("Email incorrecto");
        } else {
            this.mail = mail;
        }
        this.edad = edad;
        this.localizacion = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
}
