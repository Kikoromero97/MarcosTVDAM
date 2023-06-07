public class IncorrectMailException extends Exception{
    String sc;

    public IncorrectMailException(String sc) {
        this.sc = sc;
    }

    @Override
    public String toString() {
        return "IncorrectMailException{" +
                "sc='" + sc + '\'' +
                '}';
    }
}
