public class Usuario_bd {
    private String user_name;
    private String pass;
    private String rol;

    /**
     * Constructor de la Class.
     *
     * @param user_name Nombre del usuario.
     * @param pass Contraseña del usuario.
     * @param rol Rol del usuario.
     */
    public Usuario_bd(String user_name, String pass, String rol) {
        this.user_name = user_name;
        this.pass = pass;
        this.rol = rol;
    }

    /**
     * Getter del nombre del usuario.
     *
     * @return String.
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * Getter de la contraseña del usuario.
     *
     * @return String
     */
    public String getPass() {
        return pass;
    }

    /**
     * Getter del rol del usuario.
     *
     * @return String.
     */
    public String getRol() {
        return rol;
    }

    /**
     * To String del usuario.
     *
     * @return String con formato nombre;contraseña;rol.
     */
    @Override
    public String toString() {
        return user_name + ";" + pass + ";" + rol;
    }
}
