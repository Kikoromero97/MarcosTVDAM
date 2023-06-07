import java.sql.ResultSet;

public class DBTarjetas extends DBManager{
    private static final String SELECT_TARJETA = "SELECT * FROM tarjeta INNER JOIN tarjetaUsuario ON tarjetaUsuario.NumTarjeta = tarjeta.numero WHERE tarjetaUsuario.NumTarjeta = ";
    private static final String SELECT_CADUCADAS =  "dbo.TarjetasCaducadas";
    private static final String SELECT_TARJETAS = "SELECT * FROM tarjeta";
    public DBTarjetas() {
        super(UserManager.user.getUser_name(), UserManager.user.getPass());
    }

    public ResultSet verTarjeta(int codigo){
        return verSelect(SELECT_TARJETA + codigo);
    }

    public ResultSet deleteTarjeta(int codigo){
        return verSelect(SELECT_TARJETA + codigo);
    }
}
