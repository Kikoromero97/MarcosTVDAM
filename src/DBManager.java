import java.sql.*;

public abstract class DBManager {

    private static Connection conn = null;

    // Configuración de la conexión a la base de datos
    private static final String DB_HOST = "192.168.7.208\\SQLEXPRESS";
    private static final String DB_PORT = "1433";
    private static final String DB_TRUST_SERVER = "TrustServerCertificate=true";
    private static final String DB_URL = "jdbc:sqlserver://"+ DB_HOST + ":" + DB_PORT +";" + DB_TRUST_SERVER;


    // Configuración del usuario de la base de datos

    private static String user;
    private static String pass;

    // Configuración de mensajes de respuesta
    private static final String DB_MSQ_CONN_OK = "CONEXIÓN CORRECTA";
    private static final String DB_MSQ_CONN_NO = "ERROR EN LA CONEXIÓN";

    /**
     * Función para cargar el driver de SQL Server.
     *
     * @return boolean Devuelve true si consigue cargarlo y false si no lo consigue.
     */
    public static boolean loadDriver(){
        try{
            System.out.print("Cargando Driver...");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            System.out.println(" OK!");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Función que se encarga de conectar con la base de datos.
     *
     * @return boolean Devuelve true si se ha realizado la conexión con exito y false en caso contrario.
     */
    public static boolean connect() {
        try {
            System.out.print("Conectando a la base de datos... ");
            conn = DriverManager.getConnection(DB_URL, user, pass);
            System.out.println(DB_MSQ_CONN_OK);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(DB_MSQ_CONN_NO);
            return false;
        }
    }

    /**
     * Constructor para implementar en las class que lo extienden.
     *
     * @param user Se pasa el nombre del usuario con el que se conecta a la base de datos.
     * @param pass Se pasa la contraseña del usuario con el que se conecta a la base de datos.
     */

    public DBManager(String user, String pass){
        DBManager.user = user;
        DBManager.pass = pass;
        loadDriver();
        connect();
    }

    /**
     * Función que realiza un SELECT (SOLO LECTURA).
     *
     * @param select select que deseas realizar.
     * @return ResultSet con los datos devueltos de la base de datos (SOLO LECTURA).
     */
    public ResultSet verSelect(String select){
        try{
            Statement st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(select);
            return rs;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Función que realiza un SELECT (EDITABLE).
     *
     * @param select select que deseas realizar.
     * @return ResultSet con los datos devueltos de la base de datos (EDITABLE).
     */
    public ResultSet getSelect(String select){
        try{
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(select);
            return rs;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
