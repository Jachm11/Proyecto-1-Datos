package circuitDesing;

import AbstractFactory.tipoCompuerta;
import listas.ListaEnlazada;
import listas.Node;

public class OR extends Compuerta {

    /**
     * Constructor de la clase
     *
     * @param ID identificador numérico para la compuerta
     * @param entradas cantidad de entradas
     */
    public OR(int entradas,int ID, tipoCompuerta tipo) {

        super(entradas,ID,tipo);
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
                this.entradas = new ListaEnlazada();
                return true;
            }
        }
        if (current.getData().equals(true)){
            this.entradas = new ListaEnlazada();
            return true;
        }else {
            this.entradas = new ListaEnlazada();
            return false;
        }
    }
}
