import java.sql.ResultSet;

public class DBUsuarios extends DBManager{

    private static final String DB_USU = "dbo.usuario";

    private static final String DB_PER = "dbo.perfil";

    private static final String DB_SUS = "dbo.suscripcion";
    private static final String SELECT_USUARIOS = "SELECT * FROM " + DB_USU;
    private static final String SELECT_USUARIOS_WHERE = SELECT_USUARIOS + " WHERE codigo = ";

    private static final String SELECT_PERFILES = "SELECT * FROM " + DB_PER;
    private static final String SELECT_PERFILES_WHERE = SELECT_USUARIOS + " WHERE codigo = ";

    private static final String SELECT_SUS = "SELECT * FROM " + DB_SUS;

    public DBUsuarios(){
        super();
    }

    public ResultSet verUsuariosSorted(int codigo){
        return verSelect(SELECT_USUARIOS_WHERE + codigo);
    }
    public ResultSet verUsuarios(){
        return verSelect(SELECT_USUARIOS);
    }

    public ResultSet verSuscripciones(){
        return verSelect(SELECT_SUS);
    }
    public ResultSet verPerfilesSorted(int codigo){
        return verSelect(SELECT_PERFILES_WHERE + codigo);
    }


}
