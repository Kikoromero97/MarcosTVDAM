import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class que se encarga de gestionar la sessión del usuario.
 */
public class UserManager {

    public static File userSessionFile = new File("cache/usr.txt");
    public static Usuario_bd user;

    /**
     * Función que comprueba si hay una sessión almacenada.
     *
     * @return boolean Devuelve true si existe una session y false en caso contrario.
     */
    public static boolean hasSession(){
        return userSessionFile.exists();
    }

    /**
     * Función que se encarga de cargar la sessión almacenada.
     *
     * @return boolean Devuelve true si ha conseguido cargar el usuario y false en el caso contrario.
     */
    public static boolean loadSession() {
        try {
            BufferedReader rd = new BufferedReader(new FileReader(userSessionFile));
            String line = "";
            while ((line = rd.readLine()) != null){
                String[] lineSplit = line.split(";");
                user = new Usuario_bd(lineSplit[0], lineSplit[1], lineSplit[2]);
            }
            rd.close();
            return true;
        }catch (FileNotFoundException e){
            System.err.println(e.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Función que se encaga de guardar la sessión del usuario.
     *
     * @return boolean Devuelve true si lo ha conseguido guardar y false en caso de que no lo consigue almacenar.
     */

    public static boolean saveSession(){
        try {
            FileWriter wr = new FileWriter(userSessionFile);
            wr.write(user.toString());
            wr.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Función que se encarga de eliminar la sessión almacenada.
     *
     * @return boolean Devuelve true si lo consigue eliminar y false en caso contrario.
     */

    public static boolean exitSession(){
        user = null;
        return userSessionFile.delete();
    }

    /**
     * Setter para el usuario.
     *
     * @param user usuario.
     */
    public static void setUser(Usuario_bd user) {
        UserManager.user = user;
    }

    /**
     * Setter para el usuario.
     *
     * @param user nombre del usuario.
     * @param pass contraseña del usuario.
     * @param rol rol del usuario.
     */
    public static void setUser(String user, String pass, String rol) {
        UserManager.user = new Usuario_bd(user, pass, rol);
    }

    /**
     * Función que asigna un usuario nuevo y lo guarda en cache.
     *
     * @param user nombre del usuario.
     * @param pass contraseña del usuario.
     * @param rol rol del usuario.
     */
    public static void newUser(String user, String pass, String rol){
        UserManager.setUser(user, pass, rol);
        UserManager.saveSession();
    }

    /**
     * Función que asigna un usuario nuevo y lo guarda en cache.
     *
     * @param user usuario a asignar.
     */
    public static void newUser(Usuario_bd user){
        UserManager.setUser(user);
        UserManager.saveSession();
    }
}
