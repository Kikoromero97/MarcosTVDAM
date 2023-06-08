import java.sql.ResultSet;

public class DBDepartamento extends DBManager {

    private static final String DB_DEP = "dbo.departamento";
    private static final String SELECT_DEPARTAMENTO = "SELECT * FROM " + DB_DEP;

    private static final String SELECT_DEPARTAMENTO_POR_CODIGO = SELECT_DEPARTAMENTO + " WHERE codigo = ";

    private static final String SELECT_DEPARTAMENTO_POR_NOMBRE = SELECT_DEPARTAMENTO + " WHERE nombre LIKE ";




    /**
     * Constructor que implementa DBManager.
     */
    public DBDepartamento(){
        super();
    }

    public ResultSet verDepartamentos(){
        return verSelect(SELECT_DEPARTAMENTO);
    }

    /**
     * Función para ver un departamento específico.
     *
     * @param codigo código del departamento.
     *
     * @return ResultSet con el departamento con ese código.
     */
    public ResultSet verDepartamento(int codigo){
        return verSelect(SELECT_DEPARTAMENTO_POR_CODIGO + codigo);
    }

    /**
     * Función para ver un departamento específico.
     *
     * @param nombre nombre del departamento.
     * @return ResultSet con el departamento con ese nombre.
     */
    public ResultSet verDepartamento(String nombre){
        return verSelect(SELECT_DEPARTAMENTO_POR_NOMBRE + "'" + nombre + "'");
    }
}
