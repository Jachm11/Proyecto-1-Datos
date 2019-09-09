package circuitDesing;

import AbstractFactory.tipoCompuerta;

public class NOT extends Compuerta {
    /**
     * Constructor de la clase
     * @param ID identificador numérico para la compuerta
     */
    public NOT(int ID) {
        super(1,ID);
        this.tipo = tipoCompuerta.NOT;
        Pin miPin = (Pin)this.pinesIn.getHead().getData();
        miPin.setCenterY(33);
    }


    /**
     * Operacion logica de NOT
     * @return true or false segun la operacion
     */
    @Override
    public boolean operar() {
        if (entradas.getHead().getData().equals(true)){
            return false;}
        else{
            return true;
        }
    }
}
