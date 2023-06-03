public class DBTarjetas extends DBManager{
    private static final String SELECT = "SELECT * FROM tarjetaUsuario INNER JOIN tarjeta ON tarjetaUsuario.NumTarjeta = tarjeta.numero" +
            " WHERE tarjetaUsuario.idUsuario = ;";

    public DBTarjetas() {
        super(UserManager.user.getUser_name(), UserManager.user.getPass());
    }



}
