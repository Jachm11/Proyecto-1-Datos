package circuitDesing;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import listas.ListaEnlazada;

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
