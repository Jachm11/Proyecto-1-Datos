package circuitDesing;

import AbstractFactory.tipoCompuerta;

public class XOR extends Compuerta {

    /**
     * Constructor de la clase
     *
     * @param ID identificador numérico para la compuerta
     * @param entradas cantidad de entradas
     */
    public XOR(int entradas, int ID) {
        super(entradas,ID);
        this.tipo = tipoCompuerta.XOR;
    }

    /**
     * Operacion logica de XOR
     * @return true or false segun la operacion
     */
    @Override
    public boolean operar() {
        if(xop()%2 == 0){
            return false;
        } else {
            return true;
        }
    }
}
