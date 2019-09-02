package circuitDesing;

import AbstractFactory.tipoCompuerta;
import listas.Node;

public class OR extends Compuerta {

    /**
     * Constructor de la clase
     *
     * @param X Posicion en eje X
     * @param Y Posicion en eje Y
     * @param entradas cantidad de entradas
     */
    public OR(double X, double Y, int entradas,int ID) {
        super(X, Y, entradas,ID);
        this.tipo = tipoCompuerta.OR;
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
