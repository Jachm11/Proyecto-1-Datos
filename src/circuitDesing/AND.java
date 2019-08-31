package circuitDesing;

import listas.Node;

public class AND extends Compuerta {

    /**
     * Constructor de la clase
     */
    public AND(double X, double Y) {
        super(X, Y);

    }

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
        return true;
    }
}
