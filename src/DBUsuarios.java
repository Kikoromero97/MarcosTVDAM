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
    private static final String SELECT_PERFILES_WHERE = SELECT_PERFILES + " WHERE ";

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


    public ResultSet getPerfiles(){
        return getSelect(SELECT_PERFILES);
    }

    public ResultSet getPerfilesSorted(int codigo){
        return getSelect(SELECT_PERFILES_WHERE + "codigoPerfil = " + codigo);
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
        return verSelect(SELECT_PERFILES_WHERE + " codigoUsuario = " + code);
    }


    public Boolean crearUsuario(UsuariosClientes cli) {
        try (ResultSet rs = getUsuarios()) {

            /*Comprobar la edad*/
            int edad = comprobarEdad(cli.getEdad());
            if (edad == -1){
                return false;
            }
        int code = getNewCodigo("codigo", DB_USU);

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



    public Boolean crearPerfil(PerfilesDeUsuario per) {
        try (ResultSet rs = getPerfiles()) {

            /*Comprobar la edad*/

            int code = getNewCodigo("codigoPerfil", DB_PER);

            rs.moveToInsertRow();
            rs.updateInt("codigoPerfil", code);
            rs.updateString("nombre", per.getNombre());
            rs.updateInt("codigoUsuario", per.getCodigoUsuario());
            rs.insertRow();
            JOptionPane.showMessageDialog(null, "Creado correctamente", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se ha podido crear el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    public Boolean editarPerfil(PerfilesDeUsuario per, int codigo) {
        try (ResultSet rs = getPerfilesSorted(codigo)) {

            /*Comprobar la edad*/

            int code = getNewCodigo("codigoPerfil", DB_PER);

            rs.first();
            rs.updateString("nombre", per.getNombre());
            rs.updateRow();

            JOptionPane.showMessageDialog(null, "Editado correctamente", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se ha podido editar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }




    public boolean existeUsuario(int code) {
        try {
            ResultSet rs = getUsuariosSorted(code);
            if (rs.next()) {
                int count = rs.getInt(1);

                return count > 0;
            }
        }catch (SQLException exception){
            JOptionPane.showMessageDialog(null, "error");
            exception.printStackTrace();
            return false;
        }
        return false;
    }



    public int getNewCodigo(String columna, String tabla){
        try{
            ResultSet rs = verSelect("SELECT MAX(" + columna + ") FROM " + tabla);
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
