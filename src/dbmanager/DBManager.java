package dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBManager {

    private static Connection conn = null;

    // Configuración de la conexión a la base de datos
    private static final String DB_HOST = "192.168.7.208\\SQLEXPRESS";
    private static final String DB_PORT = "1433";
    private static final String DB_TRUST_SERVER = "TrustServerCertificate=true";
    private static final String DB_URL = "jdbc:sqlserver://"+ DB_HOST + ":" + DB_PORT +";" + DB_TRUST_SERVER;


    // Configuración del usuario de la base de datos

    private static final String DB_USER = "programa";
    private static final String DB_PASS = "1234";

    // Configuración de mensajes de respuesta
    private static final String DB_MSQ_CONN_OK = "CONEXIÓN CORRECTA";
    private static final String DB_MSQ_CONN_NO = "ERROR EN LA CONEXIÓN";

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
    public static boolean connect() {
        try {
            System.out.print("Conectando a la base de datos... ");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println(DB_MSQ_CONN_OK);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(DB_MSQ_CONN_NO);
            return false;
        }
    }

    public DBManager(){
        loadDriver();
        connect();
    }
}
