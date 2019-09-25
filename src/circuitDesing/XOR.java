package circuitDesing;

import AbstractFactory.tipoCompuerta;
import listas.ListaEnlazada;

/**
 * Clase concreta que define las propiedades y metodos de una compuerta lógica tipo XOR.
 *
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class XOR extends Compuerta {

    /**
     * Constructor de la clase.
     *
     * @param ID identificador numérico para la compuerta.
     * @param entradas cantidad de entradas.
     */
    public XOR(int entradas, int ID, tipoCompuerta tipo) {

        super(entradas,ID,tipo);
    }

    /**
     * Operacion logica de XOR.
     *
     * @return true or false segun la operacion.
     */
    @Override
    public boolean operar() {
        if(xop()%2 == 0){
            this.entradas = new ListaEnlazada();
            return false;
        } else {
            this.entradas = new ListaEnlazada();
            return true;
        }
    }
}
