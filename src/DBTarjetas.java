import javax.swing.*;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTarjetas extends DBManager{
    private static final String SELECT_TARJETA = "SELECT t.numero, t.caducidad, t.titular, t.cvv, t.banco FROM tarjetaUsuario INNER JOIN tarjeta t ON tarjetaUsuario.NumTarjeta = t.numero " +
            "INNER JOIN usuario u ON tarjetaUsuario.idUsuario = u.codigo";
    private static final String SELECT_TARJETAS_USUARIO = SELECT_TARJETA + " WHERE tarjetaUsuario.idUsuario = ";
    private static final String SELECT_TARJ_ESP = SELECT_TARJETA + " WHERE u.codigo = ";
    private static final String SELECT_TARJ_ESP_CONT = " AND t.numero ";
    private static final String SELECT_ANYADIR_TARJ = "SELECT * FROM tarjetaUsuario";

    public DBTarjetas() {
        super();
    }

    public ResultSet verTarjetaEsp(int codigo){
        return verSelect(SELECT_TARJ_ESP + codigo);
    }
    public ResultSet edityCrearTarjeta(int codigo) {
        return getSelect(SELECT_TARJETAS_USUARIO + codigo);
    }

    public ResultSet anyadirTarjetaConUsuario() {
        return getSelect(SELECT_ANYADIR_TARJ);
    }

    public ResultSet verTarjetaMuyEsp(int codigo, BigInteger numero){
        return getSelect(SELECT_TARJ_ESP + codigo + SELECT_TARJ_ESP_CONT + "LIKE '%" + numero + "%'");
    }
    public void deleteTarjeta(int codigo, BigInteger numero) {
        try {
            try (ResultSet rs = verTarjetaMuyEsp(codigo, numero)) {
                rs.moveToInsertRow();
                if (rs.first()) {
                    rs.deleteRow();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado la tarjeta.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearTarjeta(Tarjeta tarj, int codigo) {
        try (ResultSet rs = edityCrearTarjeta(codigo)) {
            rs.moveToInsertRow();
            rs.updateObject("numero", tarj.getNumTarjeta());
            rs.updateDate("caducidad", tarj.getCaducidad());
            rs.updateString("titular", tarj.getTitular());
            rs.updateInt("cvv", tarj.getCvv());
            rs.updateString("banco", tarj.getBanco());
            rs.insertRow();

            ResultSet rs_tarjUser = anyadirTarjetaConUsuario();
            BigInteger numeroTarjeta = tarj.getNumTarjeta();

            rs_tarjUser.moveToInsertRow();
            rs_tarjUser.updateInt("idUsuario", codigo);
            rs_tarjUser.updateObject("NumTarjeta", numeroTarjeta);
            rs_tarjUser.insertRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarTarjeta(Tarjeta tarj, int codigo, BigInteger numero) {
        try (ResultSet rs = verTarjetaMuyEsp(codigo, numero)) {
            while (rs.next()) {
                BigInteger numTarj = BigInteger.valueOf(rs.getLong("numero"));
                if (numTarj.equals(tarj.getNumTarjeta())) {
                    rs.updateLong("numero", tarj.getNumTarjeta().longValue());
                    rs.updateDate("caducidad", new java.sql.Date(tarj.getCaducidad().getTime()));
                    rs.updateString("titular", tarj.getTitular());
                    rs.updateInt("cvv", tarj.getCvv());
                    rs.updateString("banco", tarj.getBanco());

                    // Actualizar la fila manualmente con una sentencia UPDATE
                    PreparedStatement updateStatement = rs.getStatement().getConnection().prepareStatement(
                            "UPDATE tarjeta SET numero = ?, caducidad = ?, titular = ?, cvv = ?, banco = ? WHERE numero = ?"
                    );
                    updateStatement.setLong(1, tarj.getNumTarjeta().longValue());
                    updateStatement.setDate(2, new java.sql.Date(tarj.getCaducidad().getTime()));
                    updateStatement.setString(3, tarj.getTitular());
                    updateStatement.setInt(4, tarj.getCvv());
                    updateStatement.setString(5, tarj.getBanco());
                    updateStatement.setLong(6, tarj.getNumTarjeta().longValue());
                    updateStatement.executeUpdate();
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public boolean existsTarjeta(int codigoUsuario, BigInteger codigo) {
        try (ResultSet rs = verTarjetaMuyEsp(codigoUsuario, codigo)) {
            return rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
