package circuitDesing;

import listas.Node;
import AbstractFactory.tipoCompuerta;


public class AND extends Compuerta {


    /**
     * Constructor de la clase
     *
     * @param X Posicion en eje X
     * @param Y Posicion en eje Y
     * @param entradas cantidad de entradas
     */
    public AND(double X, double Y, int entradas, int ID) {
        super(X, Y, entradas,ID);
        this.tipo = tipoCompuerta.AND;
    }

    /**
     * Operacion logica de AND
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
                return false;
            }
        }
        return current.getData().equals(true);
    }
}
