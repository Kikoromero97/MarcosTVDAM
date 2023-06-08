import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUsuarios extends DBManager{

    private static Connection conn;

    private static final String DB_USU = "dbo.usuario";

    private static final String DB_PER = "dbo.perfil";

    private static final String DB_SUS = "dbo.suscripcion";
    private static final String SELECT_USUARIOS = "SELECT * FROM " + DB_USU;
    private static final String SELECT_USUARIOS_WHERE = SELECT_USUARIOS + " WHERE ";

    private static final String SELECT_PERFILES = "SELECT * FROM " + DB_PER;
    private static final String SELECT_PERFILES_WHERE = SELECT_PERFILES + " WHERE codigoUsuario = ";

    private static final String SELECT_SUS = "SELECT * FROM " + DB_SUS;

    public DBUsuarios(){
        super();
    }


    public ResultSet getUsuarios(){
        return getSelect(SELECT_USUARIOS);
    }

    public ResultSet getUsuariosSorted(int codigo){
        return getSelect(SELECT_USUARIOS_WHERE + "codigo = " + codigo);
    }
    /**
     * Busqueda de usuarios en CuentasUser.java
     * @return dependiendo de la busqueda devuelve unicamente el nombre buscado
     * Si no existe lo ignora y no muestra nada
     * */
    public ResultSet verUsuariosSorted(String nombre){
        return verSelect(SELECT_USUARIOS_WHERE + "nombre LIKE '%" + nombre + "%';");
    }
    public ResultSet verUsuarios(){
        return verSelect(SELECT_USUARIOS);
    }

    public ResultSet verSuscripciones(){
        return verSelect(SELECT_SUS);
    }

    public ResultSet verPerfiles(){
        return verSelect(SELECT_PERFILES);
    }


    /**
     * Busca por codigo de usuario
     * NO CODIGO DE PERFIL
     * */
    public ResultSet verPerfilesSorted(int code){
        return verSelect(SELECT_PERFILES_WHERE + code);
    }


    public Boolean crearUsuario(UsuariosClientes cli) {
        try (ResultSet rs = getUsuarios()) {

            /*Comprobar la edad*/
            int edad = comprobarEdad(cli.getEdad());
            if (edad == -1){
                return false;
            }
        int code = getNewCodigo(DB_USU);

            rs.moveToInsertRow();
            rs.updateInt("codigo", code);
            rs.updateString("nombre", cli.getNombre());
            rs.updateString("telefono", cli.getTelefono());
            rs.updateString("email", cli.getMail());
            rs.updateInt("edad", edad);
            rs.updateString("nacionalidad", cli.getLocalizacion());
            rs.insertRow();
            JOptionPane.showMessageDialog(null, "Creado correctamente", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se ha podido crear el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public Boolean editarUsuario(UsuariosClientes cli, int codigo) {
        try (ResultSet rs = getUsuariosSorted(codigo)) {

            /*Comprobar la edad*/
            int edad = comprobarEdad(cli.getEdad());
            if (edad == -1){
                return false;
            }

            rs.first();
            rs.updateString("nombre", cli.getNombre());
            rs.updateString("telefono", cli.getTelefono());
            rs.updateString("email", cli.getMail());
            rs.updateInt("edad", edad);
            rs.updateString("nacionalidad", cli.getLocalizacion());
            rs.updateRow();

            JOptionPane.showMessageDialog(null, "Creado correctamente", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se ha podido modificar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /*public boolean existsDirector(int codigo) {
        try (ResultSet rs = verDirectorEsp(codigo)) {
            return rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/




    public int getNewCodigo(String tabla){
        try{
            ResultSet rs = verSelect("SELECT MAX(codigo) FROM " + tabla);
            int newId = 1;
            while (rs.next()){
                newId += rs.getInt(1);
            }
            rs.close();
            return newId;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }




    /**
     *
     * Comprobar edad
     * Lo hice por la mala estructura, al menos se puede verificar si la edad es un número o no
     * */

    public int comprobarEdad(String edad){
        try{
            return Integer.parseInt(edad);
        } catch (NumberFormatException excep){
            JOptionPane.showMessageDialog(null, "Edad incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
}
