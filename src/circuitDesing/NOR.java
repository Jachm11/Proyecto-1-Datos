package circuitDesing;

import listas.Node;

public class NOR extends OR {


    /**
     * Constructor de la clase
     *
     * @param X        Posicion en eje X
     * @param Y        Posicion en eje Y
     * @param entradas cantidad de entradas
     */
    public NOR(double X, double Y, int entradas) {
        super(X, Y, entradas);
    }

    /**
     * Operacion logica de NOR
     * @return true or false segun la operacion
     */
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

