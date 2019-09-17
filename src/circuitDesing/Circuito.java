package circuitDesing;

import GUI.SavedCircuit;
import javafx.scene.image.Image;
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
    public ListaEnlazada absInPins;
    public ListaEnlazada absOutPins;

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
        absInPins = new ListaEnlazada();
        absOutPins = new ListaEnlazada();
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
        int unpluggeds = currentGate.getUnpluggeds();
        if (unpluggeds > 0) {
            absIn.insertarInicio(currentGate);

            Node currentNode = currentGate.getPinesIn().getHead();
            while (currentNode.getNext() != null) {
                Pin currentPin = (Pin) currentNode.getData();
                if (!currentPin.conectado) {
                    absInPins.insertarInicio(currentPin);
                }
                currentNode = currentNode.getNext();
            }
            Pin currentPin = (Pin) currentNode.getData();
            if (!currentPin.conectado) {
                absInPins.insertarInicio(currentPin);
            }
        }

        if (unpluggeds == currentGate.getNumEntradas()) {
            System.out.println("soloito");
            currentGate.setFirst(true);
        } else {
            currentGate.setMid(true);
            currentGate.setFirst(false);
        }
        if (OutIn) {
            currentGate.setLast(false);
        } else {
            currentGate.setLast(true);
            System.out.println(currentGate.getTipo().toString() + currentGate.getID());
            absOut.insertarInicio(currentGate);
            absOutPins.insertarInicio(currentGate.pinOut);
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

    public boolean checkCircuit() {
        if (compuertas == null){
            System.out.println("Circuito vacío:No hay compuertas");
            return false;
        }else {
            setRol();
            int cont = 0;
            Node current = compuertas.getHead();
            while (current.getNext() != null){
                Compuerta currentGate = (Compuerta)current.getData();
                if(currentGate.isLast() & currentGate.isFirst()){
                    cont++;
                }
                current = current.getNext();
            }
            Compuerta currentGate = (Compuerta)current.getData();
            if(currentGate.isLast() & currentGate.isFirst()){
                cont++;
            }
            if (cont > 1){
                System.out.println("Circuito invalido: Hay compuertas sin conectar");
                return false;
            }else{
                return true;
            }
        }

    }

    public SavedCircuit saveThis(Image customImg) {
        SavedCircuit circuit = new SavedCircuit(NumEntradas,NumSalidas,this,customImg);
        return circuit;
    }
}
