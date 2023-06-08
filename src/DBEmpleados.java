import java.lang.reflect.Type;
import java.sql.*;

public class DBEmpleados extends DBManager{

    // Tablas
    private static final String DB_EMP = "dbo.empleado";
    private static final String DB_SES = "dbo.sesion";
    private static final String DB_EMP_LIMITED = "dbo.informacionLimitadaEmpleado";


    // Selects de la tabla empleado
    private static final String SELECT_EMPLEADOS = "SELECT * FROM " + DB_EMP;
    private static final String SELECT_EMPLEADO_CUSTOM_WHERE = "SELECT codigo, nif, (nombre + ' ' + primer_apellido + ' ' + segundo_apellido) AS 'Nombre Completo'  FROM " + DB_EMP + " WHERE ";
    private static final String SELECT_EMPLEADO_POR_CODIGO = SELECT_EMPLEADOS + " WHERE codigo = ";
    private static final String SELECT_EMPLEADO_POR_NIF = SELECT_EMPLEADOS + " WHERE nif LIKE '";


    // Select de la tabla empleadoLimitado
    private static final String SELECT_EMPLEADOS_LIMITADO = "SELECT * FROM " + DB_EMP_LIMITED;


    // Selects de la tabla sesion
    private static final String SELECT_SESIONES = "SELECT * FROM " + DB_SES;


    // Procedures de sesion
    private static final String PROCEDURE_NEW_SESION = "EXEC dbo.newSesion @nif=?, @nombre=?, @contrasenya=?, @rol=?, @return=?";


    /**
     * Constructor que implementa DBManager.
     */
    public DBEmpleados(){
        super();
    }       

    /**
     * Función que devuelve un empleado específico.
     *
     * @param codigo codigo del empleado.
     * @return ResultSet con todos los datos del empleado (Solo lectura).
     */
    public ResultSet verEmpleado(int codigo){
        return verSelect(SELECT_EMPLEADO_POR_CODIGO + codigo);
    }

    public boolean delete(int codigo){
        try {
            ResultSet rs = getSelect(SELECT_EMPLEADO_POR_CODIGO + codigo);
            rs.last();
            rs.deleteRow();
            rs.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
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

    /**
     * Función para ver las sesiones.
     * @return ResultSet devuelve las sesiones almacenadas en la base de datos.
     */
    public ResultSet verSesiones(){
        return verSelect(SELECT_SESIONES);
    }


    /**
     * Función para buscar empleados mediante un custom WHERE.
     *
     * @param whereParameters custom WHERE.
     * @return ResultSet los empleados que coinciden con el custom WHERE.
     */
    public ResultSet verEmpleadosCustom(String whereParameters){
        return verSelect(SELECT_EMPLEADO_CUSTOM_WHERE + whereParameters);
    }
}
