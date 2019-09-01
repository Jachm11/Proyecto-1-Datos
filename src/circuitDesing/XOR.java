package circuitDesing;

public class XOR extends Compuerta {

    /**
     * Constructor de la clase
     *
     * @param X Posicion en eje X
     * @param Y Posicion en eje Y
     */
    public XOR(double X, double Y) {
        super(X, Y);
    }
    /**
     * Operacion logica de XOR
     * @return true or false segun la operacion
     */
    @Override
    public boolean operar() {
        if(xop()%2 == 0){
            return false;
        } else {
            return true;
        }
    }
}
