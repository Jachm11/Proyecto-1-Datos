package circuitDesing;

import AbstractFactory.tipoCompuerta;
import listas.ListaEnlazada;

public class XNOR extends XOR {


    /**
     * Constructor de la clase
     *
     * @param ID identificador numérico para la compuerta
     * @param entradas cantidad de entradas
     */
    public XNOR(int entradas,int ID, tipoCompuerta tipo) {

        super(entradas,ID,tipo);
    }

    /**
     * Operacion logica de NXOR
     * @return true or false segun la operacion
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
