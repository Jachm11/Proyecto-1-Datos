package circuitDesing;

import listas.ListaEnlazada;

/**
 * Clase abstracta que define las propiedades de una compuerta lógica
 * @author Jose Alejandro
 * @since 31-08-19
 */
public abstract class  Compuerta {

    int numEntradas;
    int numConexiones;
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
}
