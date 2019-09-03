package circuitDesing;

import AbstractFactory.tipoCompuerta;

public class XNOR extends XOR {


    /**
     * Constructor de la clase
     *
     * @param ID identificador numérico para la compuerta
     * @param entradas cantidad de entradas
     */
    public XNOR(int entradas,int ID) {
        super(entradas,ID);
        this.tipo = tipoCompuerta.XNOR;
    }

    /**
     * Operacion logica de NXOR
     * @return true or false segun la operacion
     */
    @Override
    public boolean operar() {
        if(xop()%2 == 0){
            return true;
        } else {
            return false;
        }
    }
}
