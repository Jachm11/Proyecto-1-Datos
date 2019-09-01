package circuitDesing;

import listas.ListaEnlazada;
import listas.Node;

/**
 * Clase abstracta que define las propiedades de una compuerta lógica
 * @author Jose Alejandro
 * @since 31-08-19
 */
public abstract class  Compuerta {

    int numEntradas;
    int numConexiones;
    int factorX; // Solo para compuertas X
    ListaEnlazada entradas;
    ListaEnlazada conexiones;
    double posX;
    double posY;

    /**
     * Constructor de la clase
     */
    public Compuerta(double X,double Y){
        this.posX = X;
        this.posY = Y;
        this.entradas = new ListaEnlazada();
        this.conexiones = new ListaEnlazada();
    }

    /**
     * Metedo abstracto que se sobreescribe segun el tipo de compuerta
     * @return true or false dependiendo deloperardor y las entradas
     */
    public abstract boolean operar();

    public void input(boolean entrada){
        this.entradas.insertarInicio(entrada);
    }

    public boolean checkEntries(){
        if (this.entradas.getHead() == null){
            return false;
        }
        else {
            return true;
        }
    }
    public int xop(){
        factorX = 0;
        Node current = this.entradas.getHead();
        while( current.getNext() != null) {
            if (current.getData().equals(true)) {
                current = current.getNext();
                factorX++;
            }else {
                current = current.getNext();
            }
        }
        if (current.getData().equals(true)){
            factorX++;
        }
        return factorX;
    }
}
