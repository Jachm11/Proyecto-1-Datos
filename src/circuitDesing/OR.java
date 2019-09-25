package circuitDesing;

import AbstractFactory.tipoCompuerta;
import listas.ListaEnlazada;
import listas.Node;

/**
 * Clase concreta que define las propiedades y metodos de una compuerta lógica tipo OR.
 *
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class OR extends Compuerta {

    /**
     * Constructor de la clase.
     *
     * @param ID identificador numérico para la compuerta.
     * @param entradas cantidad de entradas.
     */
    public OR(int entradas,int ID, tipoCompuerta tipo) {

        super(entradas,ID,tipo);
    }

    /**
     * Operacion logica de OR.
     *
     * @return true or false segun la operacion.
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
