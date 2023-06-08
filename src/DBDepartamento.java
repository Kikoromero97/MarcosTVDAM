import java.sql.ResultSet;

public class DBDepartamento extends DBManager {

    private static final String DB_DEP = "dbo.departamento";
    private static final String DB_DEP_LIMITED = "dbo.informacionLimitadaEmpleado";
    private static final String SELECT_DEPARTAMENTO = "SELECT * FROM " + DB_DEP;


    /**
     * Constructor que implementa DBManager.
     */
    public DBDepartamento(){
        super();
    }




}
