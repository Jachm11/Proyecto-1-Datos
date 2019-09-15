package circuitDesing;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import listas.ListaEnlazada;
import listas.Node;

/**
 * Clase que se encarga del almacenamiento de las compuertas en pantalla, esta es la clase que se almacena
 * la informacion del circuito para mas tarde ser trasformado a una Custom gate.
 * @since 31-08-19
 *
 */
public class Circuito extends Pane {

    int NumEntradas;
    int NumSalidas;
    static ListaEnlazada compuertas;
    static Pin selectedPin;
    public ListaEnlazada absIn;
    public ListaEnlazada absOut;

    public int getNumEntradas() {
        return NumEntradas;
    }

    public void setEntradas(int entradas) {
        this.NumEntradas = entradas;
    }

    public int getNumSalidas() {
        return NumSalidas;
    }

    public void setSalidas(int salidas) {
        this.NumSalidas = salidas;
    }

    public ListaEnlazada getCompuertas() {
        if (compuertas==null){
            compuertas = new ListaEnlazada();
        }
        return compuertas;
    }
    public void setRol(){
        NumEntradas = 0;
        NumSalidas = 0;
        absIn = new ListaEnlazada();
        absOut = new ListaEnlazada();
        ListaEnlazada circuitoActual = compuertas;
        Node current = circuitoActual.getHead();
        while (current.getNext() != null){
            rolAux(current);
            current = current.getNext();
        }
        rolAux(current);
    }

    private void rolAux(Node current) {
        Compuerta currentGate = (Compuerta) current.getData();
        boolean OutIn = currentGate.isOutIn();
        NumEntradas += currentGate.getUnpluggeds();
        if (currentGate.getUnpluggeds() > 0){
            absIn.insertarInicio(currentGate);
        }
        if (OutIn){
            currentGate.setLast(false);
        }else{
            currentGate.setLast(true);
            System.out.println(currentGate.getTipo().toString()+currentGate.getID());
            absOut.insertarInicio(currentGate);
            NumSalidas++;
        }

    }

    public void execute() {
        Node current = compuertas.getHead();
        while (current.getNext() != null){
            Compuerta currentGate = (Compuerta) current.getData();
            if (currentGate.isLast()){
                System.out.println(currentGate.output());

            }
            current = current.getNext();
        }
        System.out.println(NumEntradas);
        System.out.println(NumSalidas);
        Compuerta currentGate = (Compuerta) current.getData();
        if (currentGate.isLast()) {
            System.out.println(currentGate.output());
        }
    }
}
