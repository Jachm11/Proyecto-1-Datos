package circuitDesing;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import listas.ListaEnlazada;

/**
 * Clase que se encarga del almacenamiento de las compuertas en pantalla, esta es la clase que se almacena
 * la informacion del circuito para mas tarde ser trasformado a una Custom gate.
 * @since 31-08-19
 *
 */
public class Circuito extends Group {

    int entradas;
    int salidas;
    static ListaEnlazada compuertas;
    static Pin selectedPin;

    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public int getSalidas() {
        return salidas;
    }

    public void setSalidas(int salidas) {
        this.salidas = salidas;
    }

    public ListaEnlazada getCompuertas() {
        if (compuertas==null){
            this.compuertas = new ListaEnlazada();
        }
        return compuertas;
    }

    public void setCompuertas(ListaEnlazada compuertas) {
        this.compuertas = compuertas;
    }
}
