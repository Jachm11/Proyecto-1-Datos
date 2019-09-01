package circuitDesing;

import listas.Node;

public class NOR extends OR {
    /**
     * Constructor de la clase
     *
     * @param X Posicion en X
     * @param Y Posicion en Y
     */
    public NOR(double X, double Y) {
        super(X, Y);
    }

    @Override
    public boolean operar() {
        Node current = this.entradas.getHead();
        while( current.getNext() != null) {
            if (!(current.getData().equals(true))) {
                current = current.getNext();
            }else {
                return false;
            }
        }
        if (current.getData().equals(true)){
            return false;
        }else {
            return true;
        }
    }
}

