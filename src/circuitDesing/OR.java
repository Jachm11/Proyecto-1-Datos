package circuitDesing;

import listas.Node;

public class OR extends Compuerta {


    /**
     * Constructor de la clase
     *
     * @param X Posicion en X
     * @param Y Posicion en Y
     */
    public OR(double X, double Y) {
        super(X, Y);
    }

    /**
     * Operacion logica de OR
     * @return true or false segun la operacion
     */
    @Override
    public boolean operar() {
        Node current = this.entradas.getHead();
        while( current.getNext() != null) {
            if (!(current.getData().equals(true))) {
                current = current.getNext();
            }else {
                return true;
            }
        }
        if (current.getData().equals(true)){
            return true;
        }else {
            return false;
        }
    }
}
