package circuitDesing;

import AbstractFactory.tipoCompuerta;
import GUI.SavedCircuit;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import listas.ListaEnlazada;
import listas.Node;

import java.io.IOException;

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
    //static Pin selectedBigPin;
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
        int unpluggeds = currentGate.getUnpluggeds(currentGate.pinesIn);
        NumEntradas += unpluggeds;
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
            currentGate.setFirst(true);
        } else {
            //currentGate.setMid(true);
            currentGate.setFirst(false);
        }
        if (currentGate.getTipo() == tipoCompuerta.Custom){
            CustomGate thisCast = (CustomGate) currentGate;
            int unpluggededOuts = thisCast.getUnpluggeds(thisCast.pinesOut);
            NumSalidas += unpluggededOuts;
            if (unpluggededOuts >0 ){
                absOut.insertarInicio(currentGate);

                Node currentNode = thisCast.pinesOut.getHead();
                while (currentNode.getNext() != null) {
                    Pin currentPin = (Pin) currentNode.getData();
                    if (!currentPin.conectado) {
                        absOutPins.insertarInicio(currentPin);
                    }
                    currentNode = currentNode.getNext();
                }
                Pin currentPin = (Pin) currentNode.getData();
                if (!currentPin.conectado) {
                    absOutPins.insertarInicio(currentPin);
                }
            }
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
        //System.out.println("estas son las entradas:"+getNumEntradas());
        //System.out.println("estas son las saldias:"+ getNumSalidas());

    }



    public void execute() {
        Node current = compuertas.getHead();
        System.out.println(current.getNext() != null);
        while (current.getNext() != null){
            Compuerta currentGate = (Compuerta) current.getData();
            //System.out.println(currentGate.isLast());
            if (currentGate.isLast()){
                if (currentGate.getTipo() != tipoCompuerta.Custom) {
                    boolean result = currentGate.output();
                    System.out.println(result);
                    currentGate.pinOut.setColorValue(result);
                }else {
                    CustomGate thisCustom = (CustomGate) currentGate;
                    thisCustom.returnCicle();
                }

            }
            current = current.getNext();
        }
        Compuerta currentGate = (Compuerta) current.getData();
        //System.out.println(currentGate.isLast());
        if (currentGate.isLast()) {
            if (currentGate.getTipo() != tipoCompuerta.Custom) {
                boolean result = currentGate.output();
                System.out.println(result);
                currentGate.pinOut.setColorValue(result);
            }else {
                CustomGate thisCustom = (CustomGate) currentGate;
                thisCustom.returnCicle();
            }
        }
    }

    public boolean checkCircuit() {
        if (compuertas == null || compuertas.getHead() == null){
            System.out.println("Circuito vacío:No hay compuertas");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR: NO GATES FOUND");
            alert.setHeaderText("Circuit is empty!");
            alert.setContentText("Please insert new logic gates before running the circuit");
            ButtonType ok = new ButtonType("OK");
            alert.getButtonTypes().setAll(ok);
            alert.showAndWait();
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR: INVALID CIRCUIT");
                alert.setHeaderText("There are unplugged gates in the circuit!");
                alert.setContentText("Please connect all the gates to at least another gate");
                ButtonType ok = new ButtonType("OK");
                alert.getButtonTypes().setAll(ok);
                alert.showAndWait();
                return false;
            }else{
                return true;
            }
        }

    }

    public SavedCircuit saveThis(Image customImg) {

        return new SavedCircuit(NumEntradas,NumSalidas,GUI.TableController.getController().createTable(),customImg);
    }
}
