package circuitDesing;

/**
 * Clase donde se almacena un circuito guardado y para ser tratado como compuerta
 */
public class CustomGate extends Compuerta {
    int salidas;

    /**
     * Constructor de la clase
     *
     * @param X
     * @param Y
     * @param entradas
     */
    public CustomGate(double X, double Y, int entradas, int salidas, int ID) {
        super(X, Y, entradas,ID);
        this.salidas = salidas;
    }

    public int getSalidas() {
        return salidas;
    }

    public void setSalidas(int salidas) {
        this.salidas = salidas;
    }

    @Override
    public boolean operar() {
        return false;
    }


}
