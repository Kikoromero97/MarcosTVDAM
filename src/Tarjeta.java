import java.math.BigInteger;
import java.sql.Date;

public class Tarjeta {
    private BigInteger numTarjeta;
    private Date caducidad;
    private String titular;
    private int cvv;
    private String banco;

    public Tarjeta(BigInteger numTarjeta, Date caducidad, String titular, int cvv, String banco) {
        this.numTarjeta = numTarjeta;
        this.caducidad = caducidad;
        this.titular = titular;
        this.cvv = cvv;
        this.banco = banco;
    }

    public BigInteger getNumTarjeta() {
        return numTarjeta;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public String getTitular() {
        return titular;
    }

    public int getCvv() {
        return cvv;
    }

    public String getBanco() {
        return banco;
    }
}
