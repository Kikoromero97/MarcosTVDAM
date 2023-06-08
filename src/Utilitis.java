import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilitis {

    private static final int ROW_INTERVALS = 16;

    /**
     * Centra todas las celdas de un JTable.
     *
     * @param table tabla que se desea centrar.
     */
    public static void centerTable(JTable table) {
        DefaultTableCellRenderer centerCell = new DefaultTableCellRenderer();
        centerCell.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerCell);
        }
    }

    /**
     * Función que elimina las filas vacias.
     *
     * @param data Datos que deseas
     * @return
     */
    private static String[][] deleteNulls(String[][] data) {
        int count = 0;
        for (String[] strings : data) {
            if (strings[0] != null) {
                count++;
            }
        }
        String[][] result = new String[count][data[0].length];
        count = 0;
        for (String[] datum : data) {
            if (datum[0] != null) {
                System.arraycopy(datum, 0, result[count], 0, datum.length);
                count++;
            }
        }
        return result;
    }

    /**
     * Función que convierte un ResultSet en una matríz de String.
     *
     * @param rs ResultSet a convertir.
     * @param cantidadColumnas La cantidad de columnas que desea convertir.
     * @return String[][] devuelve los datos del ResultSet.
     */
    public static String[][] getDataFromResultSet(ResultSet rs, int cantidadColumnas) {
        try {
            String[][] result = new String[rs.getFetchSize()][cantidadColumnas];
            int count = 0;
            while (rs.next()) {
                if (result[count] != null) {
                    for (int i = 0; i < cantidadColumnas; i++) {
                        result[count][i] = rs.getString(i+1);
                    }
                }
                count++;
            }
            return deleteNulls(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Función que convierte un ResultSet en una matríz de String.
     *
     * @param rs ResultSet a convertir.
     * @param columnas Las columnas que desea convertir.
     * @return String[][] devuelve los datos del ResultSet.
     */
    public static String[][] getDataFromResultSet(ResultSet rs, String[] columnas) {
        try {
            String[][] result = new String[rs.getFetchSize()][columnas.length];
            int count = 0;
            while (rs.next()) {
                if (result[count] != null) {
                    for (int i = 0; i < columnas.length; i++) {
                        result[count][i] = rs.getString(columnas[i]);
                    }
                }
                count++;
            }
            return deleteNulls(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Función que devuelve el contenido de una celda de un JTable.
     *
     * @param table JTable que deseas sacar información.
     * @param columna Número de columna que deseas sacar.
     * @param rowY Número Y de la row (AcctionLisener e.getChild().getY())
     *
     * @return String con el contenido de la celda.
     */
    private String getCell(JTable table, int columna, int rowY) {
        int rowLine = rowY / ROW_INTERVALS;
        return (String) table.getValueAt(rowLine, columna);
    }
}
