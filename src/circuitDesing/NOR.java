package circuitDesing;

import AbstractFactory.tipoCompuerta;
import listas.Node;

public class NOR extends OR {


    /**
     * Constructor de la clase
     *
     * @param ID identificador numérico para la compuerta
     * @param entradas cantidad de entradas
     */
    public NOR(int entradas, int ID) {
        super(entradas,ID);
        this.tipo = tipoCompuerta.NOR;
    }

    /**
     * Operacion logica de NOR
     * @return true or false segun la operacion
     */
    @Override
    public boolean operar() {
        Node current = this.entradas.getHead();
        while( current.getNext() != null) {
            if (!(current.getData().equals(true))) {
                current = current.getNext();
            }else {
                return false;
            }
        }
        if (current.getData().equals(true)){
            return false;
        }else {
            return true;
        }
    }
}

