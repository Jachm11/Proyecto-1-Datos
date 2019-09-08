package circuitDesing;

import javafx.scene.layout.GridPane;
import listas.ListaEnlazada;

public class Circuito extends GridPane {

    int entradas;
    int salidas;
    static ListaEnlazada compuertas;

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
