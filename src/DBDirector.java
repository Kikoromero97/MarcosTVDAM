import javax.swing.*;
import java.awt.image.DirectColorModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDirector extends DBManager {

    private static final String SELECT_ALL = "SELECT * FROM DIRECTOR";
    private static final String SELECT_SPECIFIC = SELECT_ALL + " WHERE codigo ";
    private static final String SELECT_ONLY_WOMEN = "SELECT * FROM dbo.SoloDirectorasyPremios";

    public DBDirector() {
        super();
    }

    public ResultSet verDirector() {
        return verSelect(SELECT_ALL);
    }

    public ResultSet verDirectoras() {
        return verSelect(SELECT_ONLY_WOMEN);
    }

    public ResultSet edityCrearDirector() {
        return getSelect(SELECT_ALL);
    }

    public ResultSet verDirectorEsp(int codigo) {
        return getSelect(SELECT_SPECIFIC + "LIKE '%" + codigo + "%'");
    }

    public void deleteDirector(int codigo) {
        try {
            try (ResultSet rs = verDirectorEsp(codigo)) {
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

    public void crearDirector(Director dir) {
        try (ResultSet rs = edityCrearDirector()) {
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

    public void editarDirector(Director dir) {
        try (ResultSet rs = edityCrearDirector()) {
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
    }

    public boolean existsDirector(int codigo) {
        try (ResultSet rs = verDirectorEsp(codigo)) {
            return rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsSeriesByDirector(int codigo) {
        try (ResultSet rs = getSelect("SELECT * FROM serie WHERE director = " + codigo)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
