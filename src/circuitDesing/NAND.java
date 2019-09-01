package circuitDesing;

import listas.Node;

public class NAND extends AND {
    /**
     * Constructor de la clase
     *
     * @param X Posicion en eje X
     * @param Y Posicion en eje Y
     */
    public NAND(double X, double Y) {
        super(X, Y);
    }

    /**
     * Operacion logica de NAND
     * @return true or false segun la operacion
     */
    @Override
    public boolean operar(){
        Node current = this.entradas.getHead();
        while( current.getNext() != null){
            if (current.getData().equals(true)){
                current = current.getNext();
            }
            else {
                return true;
            }
        }
        if( current.getData().equals(true)){
            return false;
        }else {
            return true;
        }
    }
}
