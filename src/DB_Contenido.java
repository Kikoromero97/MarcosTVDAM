import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DB_Contenido extends DBManager{

    public DB_Contenido() {
        super();
    }
    private static List<Peliculas> tablaPeliculas;
    private static List<Documental> tablaDocumental;
    private static List<Series> tablaSeries;

    public void verPeliSerieDoc(){

    }

    /*Contenidos*/

    public boolean anyadirContenido(Contenidos contenido){
        try{
            ResultSet rs =  getSelect("SELECT c.* FROM contenido c");

            rs.moveToInsertRow();
            rs.updateInt("codigo",contenido.getCodigo());
            rs.updateString("titulo",contenido.getTitulo());
            rs.updateString("descripcion",contenido.getDescripcion());
            rs.updateInt("duracionSegundos", contenido.getDuracionSegundos());
            rs.updateFloat("valoracion", contenido.getValoracion());
            rs.updateDate("anyo_lanzamiento", contenido.getAnyo_lanzamiento());
            rs.updateFloat("presupuesto", contenido.getPresupuesto());
            rs.updateInt("edad_recomendada", contenido.getEdad_recomendada());
            rs.updateDate("fecha_alta", contenido.getFecha_alta());

            rs.insertRow();
            System.out.println("Datos insertados");
            rs.close();
            return true;

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*Peliculas*/

    public boolean eliminarPelicula(int cod) {
        try {
            ResultSet rs = super.getSelect("SELECT * FROM pelicula WHERE codigoPelicula = " + cod);

            if (rs == null) {
                System.out.println("Error, Resultset null");
                return false;
            }

            if (rs.first()) {
                rs.deleteRow();
                rs.close();
                System.out.println("Eliminado con exito");
                return true;
            } else {
                System.out.println("Error, en el Resultset ");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public  List<Peliculas> printTablaPeliculas() {
        try {
            ResultSet rs =  super.getSelect("SELECT c.*, p.* FROM pelicula p JOIN contenido c ON p.codigoPelicula = c.codigo;");

            tablaPeliculas = new ArrayList<>();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                int duracion = rs.getInt("duracionSegundos");
                float valoracion = rs.getFloat("valoracion");
                java.sql.Date anyo_lanzamiento = rs.getDate("anyo_lanzamiento");
                float presupuesto = rs.getFloat("presupuesto");
                int edad_recomendada = rs.getInt("edad_recomendada");
                java.sql.Date fecha_alta = rs.getDate("fecha_alta");
                int director = rs.getInt("director");
                String  tipoContenido = rs.getString("tipoContenido");


                Peliculas pelicula = new Peliculas(codigo,titulo,descripcion,duracion,valoracion,anyo_lanzamiento,presupuesto,edad_recomendada,fecha_alta, director,tipoContenido);
                tablaPeliculas.add(pelicula);

                System.out.println(pelicula);
            }return tablaPeliculas;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public boolean anyadirPelicula(Peliculas pelicula){
        try {

            anyadirContenido(pelicula);
            ResultSet rs =  getSelect("SELECT p.* FROM pelicula p");

            rs.moveToInsertRow();
            rs.updateInt("codigoPelicula", pelicula.getCodigo());
            rs.updateInt("director",pelicula.getDirector());
            rs.updateString("tipoContenido", pelicula.getTipoContenido());

            rs.insertRow();
            System.out.println("Datos insertados");
            rs.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public Peliculas buscarPelicula(String nombre)
    {
        for(Peliculas pelicula : tablaPeliculas)
        {
            String nombreEnBD = pelicula.getTitulo().toLowerCase();
            String nombreAComparar = nombre.toLowerCase();

            if (nombreEnBD.equals(nombreAComparar))
            {
                return pelicula;
            }
        }
        return  null;
    }

/*Documental*/

    public boolean eliminarDocumental(int cod) {
        try {
            ResultSet rs = super.getSelect("SELECT * FROM documental WHERE codigo = " + cod);

            if (rs == null) {
                System.out.println("Error, Resultset null");
                return false;
            }

            if (rs.first()) {
                rs.deleteRow();
                rs.close();
                System.out.println("Eliminado con exito");
                return true;
            } else {
                System.out.println("Error, en el Resultset ");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public  List<Documental> printTablaDocumental() {
        try {
            ResultSet rs =  super.getSelect("SELECT c.*, d.* FROM documental d JOIN contenido c ON d.codigo = c.codigo;");

            tablaDocumental = new ArrayList<>();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                int duracion = rs.getInt("duracionSegundos");
                float valoracion = rs.getFloat("valoracion");
                java.sql.Date anyo_lanzamiento = rs.getDate("anyo_lanzamiento");
                float presupuesto = rs.getFloat("presupuesto");
                int edad_recomendada = rs.getInt("edad_recomendada");
                java.sql.Date fecha_alta = rs.getDate("fecha_alta");
                int director = rs.getInt("director");
                String  tipoContenido = rs.getString("tipoContenido");


                Documental documental = new Documental(codigo,titulo,descripcion,duracion,valoracion,anyo_lanzamiento,presupuesto,edad_recomendada,fecha_alta, director,tipoContenido);
                tablaDocumental.add(documental);

                System.out.println(documental);
            }return tablaDocumental;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public boolean anyadirDocumental(Documental documental){
        try {

            anyadirContenido(documental);
            ResultSet rs =  getSelect("SELECT p.* FROM documental p");

            rs.moveToInsertRow();
            rs.updateInt("codigo", documental.getCodigo());
            rs.updateInt("director",documental.getDirector());
            rs.updateString("tipoContenido", documental.getTipoContenido());

            rs.insertRow();
            System.out.println("Datos insertados");
            rs.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public Documental buscarDocumental(String nombre)
    {
        for(Documental documental : tablaDocumental)
        {
            String nombreEnBD = documental.getTitulo().toLowerCase();
            String nombreAComparar = nombre.toLowerCase();

            if (nombreEnBD.equals(nombreAComparar))
            {
                return documental;
            }
        }
        return  null;
    }


/*Series*/

      public boolean eliminarSerie(int cod) {
        try {
            ResultSet rs = super.getSelect("SELECT * FROM serie WHERE codigoSerie = " + cod);

            if (rs == null) {
                System.out.println("Error, Resultset null");
                return false;
            }

            if (rs.first()) {
                rs.deleteRow();
                rs.close();
                System.out.println("Eliminado con exito");
                return true;
            } else {
                System.out.println("Error, en el Resultset ");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public  List<Series> printTablaSeries() {
        try {
            ResultSet rs =  super.getSelect("SELECT c.*, s.* FROM serie s JOIN contenido c ON s.codigo = c.codigo;");

            tablaSeries = new ArrayList<>();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                int duracion = rs.getInt("duracionSegundos");
                float valoracion = rs.getFloat("valoracion");
                java.sql.Date anyo_lanzamiento = rs.getDate("anyo_lanzamiento");
                float presupuesto = rs.getFloat("presupuesto");
                int edad_recomendada = rs.getInt("edad_recomendada");
                java.sql.Date fecha_alta = rs.getDate("fecha_alta");
                int director = rs.getInt("director");
                String  tipoContenido = rs.getString("tipoContenido");


                Series serie = new Series(codigo,titulo,descripcion,duracion,valoracion,anyo_lanzamiento,presupuesto,edad_recomendada,fecha_alta, director,tipoContenido);
                tablaSeries.add(serie);

                System.out.println(serie);
            }return tablaSeries;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public boolean anyadirSerie(Series serie){
        try {

            anyadirContenido(serie);
            ResultSet rs =  getSelect("SELECT p.* FROM serie p");

            rs.moveToInsertRow();
            rs.updateInt("codigoSerie", serie.getCodigo());
            rs.updateInt("director",serie.getDirector());
            rs.updateString("tipoContenido", serie.getTipoContenido());

            rs.insertRow();
            System.out.println("Datos insertados");
            rs.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public Series buscarSerie(String nombre)
    {
        for(Series serie : tablaSeries)
        {
            String nombreEnBD = serie.getTitulo().toLowerCase();
            String nombreAComparar = nombre.toLowerCase();

            if (nombreEnBD.equals(nombreAComparar))
            {
                return serie;
            }
        }
        return  null;
    }


}
