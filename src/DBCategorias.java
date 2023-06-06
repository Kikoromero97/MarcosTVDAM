import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCategorias extends DBManager {
    private static final String SELECT_ALL = "SELECT * FROM CATEGORIA";
    private static final String SELECT_SPECIFIC = SELECT_ALL + " WHERE codigo = ";

    public DBCategorias() {
        super(UserManager.user.getUser_name(), UserManager.user.getPass());
    }

    public ResultSet verCategorias() {
        return verSelect(SELECT_ALL);
    }

    public ResultSet edityCrearCategorias() {
        return getSelect(SELECT_ALL);
    }

    public ResultSet verCategoriaEsp(int codigo) {
        return getSelect(SELECT_SPECIFIC + codigo);
    }

    public void deleteCategoria(int codigo) {
        try {
            try (ResultSet rs = verCategoriaEsp(codigo)) {
                rs.moveToInsertRow();
                if (rs.first()) {
                    rs.deleteRow();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado la categoria.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearCategoria(Categoria cat) {
        try (ResultSet rs = edityCrearCategorias()) {
            rs.moveToInsertRow();
            rs.updateInt("codigo", cat.getCodigo());
            rs.updateString("nombre", cat.getNombre());
            rs.updateString("descripcion", cat.getDescripcion());
            rs.insertRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarCategoria(Categoria cat) {
        try (ResultSet rs = edityCrearCategorias()) {
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

    public boolean existsCategoria(int codigo) {
        try (ResultSet rs = verCategoriaEsp(codigo)) {
            return rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
