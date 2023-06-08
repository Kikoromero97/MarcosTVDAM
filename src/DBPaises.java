import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBPaises extends DBManager {
    private static final String SELECT_ALL = "SELECT * FROM PAIS";
    private static final String SELECT_SPECIFIC = SELECT_ALL + " WHERE id ";

    public DBPaises() {
        super();
    }

    public ResultSet verPais() {
        return verSelect(SELECT_ALL);
    }

    public ResultSet edityCrearPais() {
        return getSelect(SELECT_ALL);
    }

    public ResultSet verPaisEsp(int codigo) {
        return getSelect(SELECT_SPECIFIC + "LIKE '%" + codigo + "%'");
    }

    public void deletePais(int codigo) {
        try {
            try (ResultSet rs = verPaisEsp(codigo)) {
                rs.moveToInsertRow();
                if (rs.first()) {
                    rs.deleteRow();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el país.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearPais(Pais pais) {
        try (ResultSet rs = edityCrearPais()) {
            rs.moveToInsertRow();
            rs.updateInt("id", pais.getId());
            rs.updateString("nombre", pais.getNombre());
            rs.insertRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarPais(Pais pais) {
        try (ResultSet rs = edityCrearPais()) {
            while (rs.next()) {
                int codigo = rs.getInt("id");
                if (codigo == pais.getId()) {
                    rs.updateString("nombre", pais.getNombre());
                    rs.updateRow();
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsPais(int codigo) {
        try (ResultSet rs = verPaisEsp(codigo)) {
            return rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsDirectorByPais(int codigo) {
        try (ResultSet rs = getSelect("SELECT * FROM DIRECTOR WHERE idPais = " + codigo)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsIdiomaByPais(int codigo) {
        try (ResultSet rs = getSelect("SELECT * FROM PAIS_IDIOMA WHERE id_pais = " + codigo)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
