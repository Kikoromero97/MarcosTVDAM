public class UsuariosClientes {


    private String nombre;
    private String direccion;
    private String telefono;
    private String mail;
    private String edad;

    public UsuariosClientes(String nombre, String direccion, String telefono, String mail, String edad) throws IncorrectMailException {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        if (!mail.contains("@")){
            throw new IncorrectMailException("Email incorrecto");
        } else {
            this.mail = mail;
        }
        this.edad = edad;
    }


}
