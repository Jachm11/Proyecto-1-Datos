package circuitDesing;

public class NXOR extends XOR {
    /**
     * Constructor de la clase
     *
     * @param X Posicion en eje X
     * @param Y Posicion en eje Y
     */
    public NXOR(double X, double Y) {
        super(X, Y);
    }

    @Override
    public boolean operar() {
        if(xop()%2 == 0){
            return true;
        } else {
            return false;
        }
    }
}
