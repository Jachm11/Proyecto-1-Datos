package circuitDesing;

import AbstractFactory.tipoCompuerta;
import listas.ListaEnlazada;
import listas.Node;

/**
 * Clase concreta que define las propiedades y metodos de una compuerta lógica tipo NOR.
 *
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class NOR extends Compuerta {

    /**
     * Constructor de la clase.
     *
     * @param ID identificador numérico para la compuerta.
     * @param entradas cantidad de entradas.
     */
    public NOR(int entradas, int ID, tipoCompuerta tipo) {

        super(entradas,ID,tipo);
    }

    /**
     * Operacion logica de NOR.
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
                return false;
            }
        }
        if (current.getData().equals(true)){
            this.entradas = new ListaEnlazada();
            return false;
        }else {
            this.entradas = new ListaEnlazada();
            return true;
        }
    }
}

