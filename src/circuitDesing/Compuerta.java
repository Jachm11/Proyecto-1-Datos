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
    ListaEnlazada entradas;
    ListaEnlazada conexiones;
    double posX;
    double posY;

    public Compuerta(double X,double Y){
        this.posX = X;
        this.posY = Y;

    }
    public abstract boolean operar();

    public void input(boolean entrada){
        this.entradas.insertarInicio(entrada);
    }
}
