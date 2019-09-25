package circuitDesing;

import AbstractFactory.tipoCompuerta;
import listas.ListaEnlazada;

/**
 * Clase concreta que define las propiedades y metodos de una compuerta lógica tipo XNOR.
 *
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class XNOR extends Compuerta {

    /**
     * Constructor de la clase.
     *
     * @param ID identificador numérico para la compuerta.
     * @param entradas cantidad de entradas.
     */
    public XNOR(int entradas,int ID, tipoCompuerta tipo) {

        super(entradas,ID,tipo);
    }

    /**
     * Operacion logica de NXOR.
     *
     * @return true or false segun la operacion.
     */
    @Override
    public boolean operar() {
        if(xop()%2 == 0){
            this.entradas = new ListaEnlazada();
            return true;
        } else {
            this.entradas = new ListaEnlazada();
            return false;
        }
    }
}
