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


}
