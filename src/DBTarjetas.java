import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTarjetas extends DBManager{
    private static final String SELECT_TARJETA = "SELECT t.numero, t.caducidad, t.titular, t.cvv, t.banco FROM tarjetaUsuario INNER JOIN tarjeta t ON tarjetaUsuario.NumTarjeta = t.numero " +
            "INNER JOIN usuario u ON tarjetaUsuario.idUsuario = u.codigo";
    private static final String SELECT_TARJETAS_USUARIO = SELECT_TARJETA + " WHERE tarjetaUsuario.idUsuario = ";
    private static final String SELECT_CADUCADAS =  "dbo.TarjetasCaducadas";
    private static final String SELECT_TARJETAS = "SELECT * FROM tarjeta";
    public DBTarjetas() {
        super();
    }

    public ResultSet verTarjetaEsp(int codigo){
        return verSelect(SELECT_TARJETAS_USUARIO + codigo);
    }

    public ResultSet verTarjeta(){
        return verSelect(SELECT_TARJETAS);
    }
    public ResultSet edityCrearTarjeta() {
        return getSelect(SELECT_TARJETA);
    }
    public void deleteTarjeta(int codigo) {
        try {
            try (ResultSet rs = verTarjetaEsp(codigo)) {
                rs.moveToInsertRow();
                if (rs.first()) {
                    rs.deleteRow();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el director.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public void crearTarjeta(Tarjeta dir) {
        try (ResultSet rs = edityCrearTarjeta()) {
            rs.moveToInsertRow();
            rs.updateInt("codigo", dir.getCodigo());
            rs.updateString("nombre", dir.getNombre());
            rs.updateString("apellidos", dir.getApellidos());
            rs.updateInt("edad", dir.getEdad());
            rs.updateString("nacionalidad", dir.getNacionalidad());
            rs.updateString("sexo", dir.getGenero().toString());
            rs.updateInt("NumPremios", dir.getNumPremios());
            rs.updateInt("anyosexperiencia", dir.getAnyosExp());
            rs.updateInt("idPais", dir.getIdPais());
            rs.insertRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarTarjeta(Tarjeta dir) {
        try (ResultSet rs = edityCrearTarjeta()) {
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                if (codigo == dir.getCodigo()) {
                    rs.updateString("nombre", dir.getNombre());
                    rs.updateString("apellidos", dir.getApellidos());
                    rs.updateInt("edad", dir.getEdad());
                    rs.updateString("nacionalidad", dir.getNacionalidad());
                    rs.updateString("sexo", dir.getGenero().toString());
                    rs.updateInt("NumPremios", dir.getNumPremios());
                    rs.updateInt("anyosexperiencia", dir.getAnyosExp());
                    rs.updateInt("idPais", dir.getIdPais());
                    rs.updateRow();
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public boolean existsTarjeta(int codigo) {
        try (ResultSet rs = verTarjetaEsp(codigo)) {
            return rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsSeriesByTarjeta(int codigo) {
        try (ResultSet rs = getSelect("SELECT * FROM serie WHERE director = " + codigo)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
