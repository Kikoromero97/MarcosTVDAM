public class Temporada {
    private int numero;
    private int codSerie;
    private String resumen;

    public Temporada(int numero, int codSerie, String resumen) {
        this.numero = numero;
        this.codSerie = codSerie;
        this.resumen = resumen;
    }

    public int getNumeroTemporada()
    {
        return numero;
    }

    public int getCodSerie()
    {
        return codSerie;
    }

    public String getResumen()
    {
        return resumen;
    }
}
