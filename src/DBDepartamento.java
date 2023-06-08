import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDepartamento extends DBManager {

    private static final String DB_DEP = "dbo.departamento";
    private static final String DB_DEP_LIMITED = "dbo.informacionLimitadaEmpleado";
    private static final String SELECT_DEPARTAMENTO = "SELECT * FROM " + DB_DEP;
    private static final String SELECT_DESPECIFICO = SELECT_DEPARTAMENTO + "WHERE nombre = ";


    /**
     * Constructor que implementa DBManager.
     */
    public DBDepartamento(){
        super();
    }

    public ResultSet verDepartamentos() {
        return verSelect(SELECT_DEPARTAMENTO);
    }

    public ResultSet verDespecifico(String nombre) {
        return verSelect(SELECT_DESPECIFICO + nombre);
    }

    public ResultSet editarDepartamentos() {
        return getSelect(SELECT_DEPARTAMENTO);
    }

    public void crearDepartamento(Departamento departamento) {
        try (ResultSet rs = editarDepartamentos()) {
            rs.moveToInsertRow();
            rs.updateInt("codigo", departamento.getCodigo());
            rs.updateString("nombre", departamento.getNombre());
            rs.updateString("descripcion", departamento.getDescripcion());
            rs.insertRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet editarDepartamento(Departamento departamento) {
        try (ResultSet rs = editarDepartamentos()) {
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                if (codigo == departamento.getCodigo()) {
                    rs.updateString("nombre", departamento.getNombre());
                    rs.updateString("descripcion", departamento.getDescripcion());
                    rs.updateRow();
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
