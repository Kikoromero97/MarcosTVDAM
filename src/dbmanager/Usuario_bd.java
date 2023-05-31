package dbmanager;

public class Usuario_bd {
    private String user_name;
    private String pass;
    private String rol;

    public Usuario_bd(String user_name, String pass, String rol) {
        this.user_name = user_name;
        this.pass = pass;
        this.rol = rol;
    }

    public String getUser_name() {

        return user_name;
    }

    public String getPass() {
        return pass;
    }

    public String getRol() {
        return rol;
    }
}
