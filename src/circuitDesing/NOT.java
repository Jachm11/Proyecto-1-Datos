package circuitDesing;

public class NOT extends Compuerta {
    /**
     * Constructor de la clase
     *
     * @param X Posicion en X
     * @param Y Posicion en Y
     */
    public NOT(double X, double Y) {
        super(X, Y);
    }

    @Override
    public boolean operar() {
        if (entradas.getHead().getData().equals(true)){
            return false;}
        else{
            return true;
        }
    }
}
