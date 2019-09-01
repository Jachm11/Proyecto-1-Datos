package circuitDesing;

public class Pin {

    int id;
    boolean valor;
    Compuerta compuerta;

    /**
     * Constructor de la clase
     * @param id identificador para el numero de pin
     */
    public Pin(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isValor() {
        return valor;
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    public Compuerta getCompuerta() {
        return compuerta;
    }

    public void setCompuerta(Compuerta compuerta) {
        this.compuerta = compuerta;
    }
}
