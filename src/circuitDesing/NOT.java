package circuitDesing;

import AbstractFactory.tipoCompuerta;

public class NOT extends Compuerta {
    /**
     * Constructor de la clase
     *  @param X Posicion en X
     * @param Y Posicion en Y
     */
    public NOT(double X, double Y, int ID) {
        super(X, Y,1,ID);
        this.tipo = tipoCompuerta.NOT;
    }


    /**
     * Operacion logica de NOT
     * @return true or false segun la operacion
     */
    @Override
    public boolean operar() {
        if (entradas.getHead().getData().equals(true)){
            return false;}
        else{
            return true;
        }
    }
}
