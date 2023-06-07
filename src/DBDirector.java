import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDirector extends DBManager {

    private static final String SELECT_ALL = "SELECT * FROM DIRECTOR";
    private static final String SELECT_SPECIFIC = SELECT_ALL + " WHERE codigo = ";

    public DBDirector() {
        super(UserManager.user.getUser_name(), UserManager.user.getPass());
    }

    public ResultSet verDirector() {
        return verSelect(SELECT_ALL);
    }

    public ResultSet edityCrearDirector() {
        return getSelect(SELECT_ALL);
    }

    public ResultSet verDirectorEsp(int codigo) {
        return getSelect(SELECT_SPECIFIC + codigo);
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
            rs.updateInt("AñosExp", dir.getAnyosExp());


            rs.insertRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarDirector(Categoria cat) {
        try (ResultSet rs = edityCrearDirector()) {
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                if (codigo == cat.getCodigo()) {
                    rs.updateString("nombre", cat.getNombre());
                    rs.updateString("descripcion", cat.getDescripcion());
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

}
