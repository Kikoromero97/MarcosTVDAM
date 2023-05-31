package dbmanager;

import java.io.File;

public class Main {
    public static Usuario_bd user;
    private static File usuarioTXT = new File("cache/usuarioLogeado.txt");
    public static void main(String[] args) {

        if (usuarioTXT.exists()){
            user = new Usuario_bd(nombre, pass, rol);
            pantallaPrincipal();
        } else {
            inicioSesion();
        }.0
    }
}