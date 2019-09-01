package circuitDesing;

public class XOR extends Compuerta {

    /**
     * Constructor de la clase
     *
     * @param X Posicion en eje X
     * @param Y Posicion en eje Y
     * @param entradas cantidad de entradas
     */
    public XOR(double X, double Y, int entradas) {
        super(X, Y, entradas);
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
