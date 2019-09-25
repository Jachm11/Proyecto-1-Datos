package circuitDesing;

import listas.ListaEnlazada;
import listas.Node;
import AbstractFactory.tipoCompuerta;

/**
 * Clase concreta que define las propiedades y metodos de una compuerta lógica tipo AND.
 *
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class AND extends Compuerta {

    /**
     * Constructor de la clase.
     *
     * @param entradas cantidad de entradas.
     * @param ID identificador numérico para la compuerta.
     */
    public AND(int entradas, int ID,tipoCompuerta tipo) {

        super(entradas,ID,tipo);

    }

    /**
     * Operacion logica de AND.
     *
     * @return true or false segun la operacion.
     */
    @Override
    public boolean operar(){
        Node current = this.entradas.getHead();
        while( current.getNext() != null){
            if (current.getData().equals(true)){
                current = current.getNext();
            }
            else {
                this.entradas = new ListaEnlazada();
                return false;
            }
        }
        this.entradas = new ListaEnlazada();
        return current.getData().equals(true);
    }
}
