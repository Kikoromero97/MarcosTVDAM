import java.lang.reflect.Type;
import java.sql.*;

public class DBEmpleados extends DBManager{

    private static final String DB_EMP = "dbo.empleado";
    private static final String DB_EMP_LIMITED = "dbo.informacionLimitadaEmpleado";
    private static final String SELECT_EMPLEADOS = "SELECT * FROM " + DB_EMP;
    private static final String SELECT_EMPLEADO_POR_CODIGO = SELECT_EMPLEADOS + " WHERE codigo = ";
    private static final String SELECT_EMPLEADOS_LIMITADO = "SELECT * FROM " + DB_EMP_LIMITED;

    private static final String SELECT_EMPLEADO_POR_NIF = SELECT_EMPLEADOS + " WHERE nif = ";

    private static final String PROCEDURE_NEW_SESION = "EXEC dbo.newSesion @nif=?, @nombre=?, @contrasenya=?, @rol=?, @return=?";

    /**
     * Constructor que implementa DBManager.
     */
    public DBEmpleados(){
        super();
    }

    /**
     * Función que devuelve un empleado especifico.
     *
     * @param codigo codigo del empleado.
     * @return ResultSet con todos los datos del empleado (Solo lectura).
     */
    public ResultSet verEmpleado(int codigo){
        return verSelect(SELECT_EMPLEADO_POR_CODIGO + codigo);
    }

    /**
     * Función que devuelve empleados con el mismo nif proporcionado.
     *
     * @param nif nif del empleado/s.
     * @return ResultSet con todos los datos del/de los empleado/s (Solo lectura).
     */
    public ResultSet verEmpleado(String nif){
        return verSelect(SELECT_EMPLEADO_POR_NIF + nif);
    }

    /**
     * Función que devuelve un empleado especifico.
     *
     * @param codigo codigo del empleado.
     * @return ResultSet con todos los datos del empleado (Editable).
     */
    public ResultSet editEmpleado(int codigo){
        return getSelect(SELECT_EMPLEADO_POR_CODIGO + codigo);
    }

    /**
     * Función que lista el codigo, el nif y el nombre completo de todos los empleados.
     *
     * @return ResultSet con los datos de todos los empleados.
     */
    public ResultSet listarEmpleadosLimitado(){
        return verSelect(SELECT_EMPLEADOS_LIMITADO);
    }

    /**
     * Función que intenta crear una nueva sesión en la base de datos.
     *
     * @param nif Nif del empleado que desea crear una nueva sesión.
     * @param nombre Nombre de la sesión.
     * @param contrasenya Contraseña de la sesión.
     * @param rol Rol de la sesión.
     * @return int -3: error con la bd, -2: el nombre de la sesión ya existe, -1: el nif no está registrado, 0: el nif ya tiene sesión y 1: creado exitosamente.
     */
    public int procedureNewSesion(String nif, String nombre, String contrasenya, String rol){
        try{
            CallableStatement cs = getConn().prepareCall(PROCEDURE_NEW_SESION);

            cs.registerOutParameter("@return", Types.INTEGER);
            cs.setString("@nif", nif);
            cs.setString("@nombre", nombre);
            cs.setString("@contrasenya", contrasenya);
            cs.setString("@rol", rol);
            cs.execute();
            return cs.getInt("@return");
        } catch (SQLException e){
            e.printStackTrace();
            return -3;
        }
    }
}
