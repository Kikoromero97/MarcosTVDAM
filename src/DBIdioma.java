import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBIdioma extends DBManager {
    private static final String SELECT_ALL = "SELECT * FROM IDIOMA";
    private static final String SELECT_SPECIFIC = SELECT_ALL + " WHERE id ";

    public DBIdioma() {
        super();
    }

    public ResultSet verIdioma() {
        return verSelect(SELECT_ALL);
    }

    public ResultSet edityCrearIdioma() {
        return getSelect(SELECT_ALL);
    }

    public ResultSet verIdiomaEsp(int codigo) {
        return getSelect(SELECT_SPECIFIC + "LIKE '%" + codigo + "%'");
    }

    public void deleteIdioma(int codigo) {
        try {
            try (ResultSet rs = verIdiomaEsp(codigo)) {
                rs.moveToInsertRow();
                if (rs.first()) {
                    rs.deleteRow();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el idioma.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearIdioma(Idioma idi) {
        try (ResultSet rs = edityCrearIdioma()) {
            rs.moveToInsertRow();
            rs.updateInt("id", idi.getId());
            rs.updateString("nombre", idi.getNombre());
            rs.insertRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarIdioma(Idioma idi) {
        try (ResultSet rs = edityCrearIdioma()) {
            while (rs.next()) {
                int codigo = rs.getInt("id");
                if (codigo == idi.getId()) {
                    rs.updateString("nombre", idi.getNombre());
                    rs.updateRow();
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsIdioma(int codigo) {
        try (ResultSet rs = verIdiomaEsp(codigo)) {
            return rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsPaisByIdioma(int codigo) {
        try (ResultSet rs = getSelect("SELECT * FROM PAIS_IDIOMA WHERE id_idioma = " + codigo)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
