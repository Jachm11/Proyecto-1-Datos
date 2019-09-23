package circuitDesing;

import AbstractFactory.tipoCompuerta;
import listas.ListaEnlazada;

public class NOT extends Compuerta {
    /**
     * Constructor de la clase
     * @param ID identificador numérico para la compuerta
     */
    public NOT(int ID, tipoCompuerta tipo) {
        super(1,ID,tipo);
        Pin miPin = (Pin)this.pinesIn.getHead().getData();
        miPin.setTranslateY(13);
        pinOut.setTranslateX(-25);
        pinOut.setTranslateY(-6);
    }


    /**
     * Operacion logica de NOT
     * @return true or false segun la operacion
     */
    @Override
    public boolean operar() {
        if (entradas.getHead().getData().equals(true)){
            this.entradas = new ListaEnlazada();
            return false;}
        else{
            this.entradas = new ListaEnlazada();
            return true;
        }
    }
}
