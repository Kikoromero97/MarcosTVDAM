import java.sql.ResultSet;

public class DBEmpleados extends DBManager{

    private static final String DB_EMP = "dbo.empleado";
    private static final String DB_EMP_LIMITED = "dbo.informacionLimitadaEmpleado";
    private static final String SELECT_EMPLEADOS = "SELECT * FROM " + DB_EMP;
    private static final String SELECT_EMPLEADO_POR_CODIGO = SELECT_EMPLEADOS + " WHERE codigo = ";
    private static final String SELECT_EMPLEADOS_LIMITADO = "SELECT * FROM " + DB_EMP_LIMITED;

    private static final String SELECT_EMPLEADO_POR_NIF = SELECT_EMPLEADOS + " WHERE nif = ";

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
}
