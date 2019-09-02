package circuitDesing;

import AbstractFactory.tipoCompuerta;

public class XNOR extends XOR {


    /**
     * Constructor de la clase
     *
     * @param X        Posicion en eje X
     * @param Y        Posicion en eje Y
     * @param entradas cantidad de entradas
     */
    public XNOR(double X, double Y, int entradas,int ID) {
        super(X, Y, entradas,ID);
        this.tipo = tipoCompuerta.XNOR;
    }

    /**
     * Operacion logica de NXOR
     * @return true or false segun la operacion
     */
    @Override
    public boolean operar() {
        if(xop()%2 == 0){
            return true;
        } else {
            return false;
        }
    }
}
