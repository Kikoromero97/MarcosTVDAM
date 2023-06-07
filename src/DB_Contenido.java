import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Contenido extends DBManager{



    public DB_Contenido() {
        super();
    }

    public static void verPeliSerieDoc(){

    }

    public void eliminarPeliSerieDoc(int cod){
        try {
            ResultSet rs = super.getSelect("SELECT * FROM pelicula WHERE codigoPelicula = " + cod);

            rs.deleteRow();
            rs.close();

        }catch (SQLException e){
            e.printStackTrace();

        }

    }
}
