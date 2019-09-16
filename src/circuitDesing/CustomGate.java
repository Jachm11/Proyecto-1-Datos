package circuitDesing;

import AbstractFactory.tipoCompuerta;
import GUI.BigPin;
import listas.ListaEnlazada;

/**
 * Clase donde se almacena un circuito guardado y para ser tratado como compuerta
 */
public class CustomGate extends Compuerta {
    int NumSalidas;
    ListaEnlazada pinesOut;
    BigPin bigPinOut;

    /**
     * Constructor de la clase
     * @param ID identificador para la compuerta
     * @param entradas
     */
    public CustomGate( int entradas, int salidas, int ID) {
        super(entradas,salidas);
        this.NumSalidas = salidas;
        this.tipo = tipoCompuerta.Custom;
    }

    public int getSalidas() {
        return NumSalidas;
    }

    public void setSalidas(int salidas) {
        this.NumSalidas = salidas;
    }

    public ListaEnlazada getPinesOut() {
        return pinesOut;
    }

    public BigPin getBigPinOut() {
        return bigPinOut;
    }

    public void setBigPinOut(BigPin bigPinOut) {
        this.bigPinOut = bigPinOut;
    }

    @Override
    public boolean operar() {
        return false;
    }


}
