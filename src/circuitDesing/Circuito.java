package circuitDesing;

import AbstractFactory.tipoCompuerta;
import GUI.Controller;
import GUI.SavedCircuit;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import listas.ListaEnlazada;
import listas.Node;

/**
 * Clase que se encarga del almacenamiento de las compuertas en pantalla, esta es la clase que se almacena
 * la informacion del circuito para mas tarde ser trasformado a una Custom gate.
 *
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class Circuito extends Pane {

    static ListaEnlazada compuertas;
    static Pin selectedPin;
    public ListaEnlazada absIn;
    public ListaEnlazada absOut;
    private ListaEnlazada absInPins;
    private ListaEnlazada absOutPins;
    private int NumEntradas;
    private int NumSalidas;

    //         ____________________
    //________/Getters and Setters

    public int getNumEntradas() {
        return NumEntradas;
    }

    public void setEntradas(int entradas) {
        this.NumEntradas = entradas;
    }

    public int getNumSalidas() {
        return NumSalidas;
    }

    //(por la inexistencia de un construcutor de clase)
    public ListaEnlazada getCompuertas() {
        if (compuertas==null){
            compuertas = new ListaEnlazada();
        }
        return compuertas;
    }

    //         ______________________________________
    //________/METODOS PARA LA EJECUCION DEL CIRCUITO

    /**
     * Este metodo inicia el ciclo para que las compuertas pidan su valor de salida. Este se ejecuta en las compuertas del final.
     */
    public void execute() {
        Node current = compuertas.getHead();
        while (current.getNext() != null){
            askOutGate(current);
            current = current.getNext();
        }
        askOutGate(current);
        Controller.getController().Console.appendText("\n"+"Simulation Ended"+"\n"+"______________________________________________________________________________________________"+ "\n");
    }

    /**
     *Pregunta a las compuertas finales del circuito por su valor, esto incia del ciclo que recorre el circuito.
     *
     * @param current Nodo que contiene la informacion de la compuerta a la que se esta pidiendo su valor de salida
     */
    private void askOutGate(Node current) {
        Compuerta currentGate = (Compuerta) current.getData();
        if (currentGate.isLast()){
            if (currentGate.getTipo() != tipoCompuerta.Custom) {
                boolean result = currentGate.output();
                System.out.println(result);
                Controller.getController().Console.appendText(currentGate.getTipo()+" #"+currentGate.ID+" "+"result: "+result+ "\n");
                currentGate.pinOut.setColorValue(result);
            }else {
                CustomGate thisCustom = (CustomGate) currentGate;
                thisCustom.returnCicle();
            }
        }
    }

    /**
     * Este metodo analiza el circuito para asegurarse que esta como debe, y que es posible ejecutarlo. Ademas ejecuta el comado setRol(), para terminar de preparar el ciruito.
     * Criterios:
     * Todas las compuertas conectadas (No hay compuertas "flotando").
     * Existen compuertas en el circuito.
     *
     * @return true or false segun los criterios.
     */
    public boolean checkCircuit() {
        if (compuertas == null || compuertas.getHead() == null){
            System.out.println("Circuito vacío: No hay compuertas");
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

    /**
     * Este metodo revisa la configuracion del circuito e identifica sus partes.
     */
    private void setRol(){
        NumEntradas = 0;
        NumSalidas = 0;
        absIn = new ListaEnlazada();
        absOut = new ListaEnlazada();
        absInPins = new ListaEnlazada();
        absOutPins = new ListaEnlazada();
        ListaEnlazada circuitoActual = compuertas;
        Node current = circuitoActual.getHead();
        //         __________________________________________________
        //________/Ciclo que recorre todas las compuertas en pantalla
        while (current.getNext() != null){
            rolAux(current);
            current = current.getNext();
        }
        rolAux(current);
    }

    /**
     * Metodo auxiliar para la identificacion del rol de una compuerta. Y conteo de pines totales de entrada y salida.
     * @param current Nodo de una lista de compuertas.
     */
    private void rolAux(Node current) {
        Compuerta currentGate = (Compuerta) current.getData();
        boolean OutIn = currentGate.isOutIn();
        int unpluggeds = currentGate.getUnpluggeds(currentGate.pinesIn);
        NumEntradas += unpluggeds;

        //         __________________________________
        //________/Evalua la compuerta por sus inputs
        if (unpluggeds > 0) {
            absIn.insertarInicio(currentGate);

            Node currentNode = currentGate.getPinesIn().getHead();
            evaluarPines(currentNode, absInPins);
        }

        if (unpluggeds == currentGate.getNumEntradas()) {
            currentGate.setFirst(true);
        } else {
            currentGate.setFirst(false);
        }

        //         __________________________________
        //________/Evalua la compuerta por sus inputs
        if (currentGate.getTipo() == tipoCompuerta.Custom){
            CustomGate thisCast = (CustomGate) currentGate;
            int unpluggededOuts = thisCast.getUnpluggeds(thisCast.pinesOut);
            NumSalidas += unpluggededOuts;
            if (unpluggededOuts >0 ){
                absOut.insertarInicio(currentGate);
                currentGate.setLast(true);

                Node currentNode = thisCast.pinesOut.getHead();
                evaluarPines(currentNode, absOutPins);
            }else{
                currentGate.setLast(false);
            }

        }else {
            if (OutIn) {
                currentGate.setLast(false);
            } else {
                currentGate.setLast(true);
                absOut.insertarInicio(currentGate);
                absOutPins.insertarInicio(currentGate.pinOut);
                NumSalidas++;
            }
        }
    }

    /**
     * Metodo para evitar repeticion inecesaria de codigo.
     * Evalua los pines y los inserta en una lista de pines importantes para el circuito.
     *
     * @param currentNode Nodo con la data del pin que se esta analizando.
     * @param absPins lista de pines donde se van incertar los pines sin conexion.
     */
    private void evaluarPines(Node currentNode, ListaEnlazada absPins) {
        while (currentNode.getNext() != null) {
            Pin currentPin = (Pin) currentNode.getData();
            if (!currentPin.conectado) {
                absPins.insertarInicio(currentPin);
            }
            currentNode = currentNode.getNext();
        }
        Pin currentPin = (Pin) currentNode.getData();
        if (!currentPin.conectado) {
            absPins.insertarInicio(currentPin);
        }
    }

    //         ______________
    //________/OTROS METODOS

    /**
     * Metodo utilizado para guardar el ciruito, crea una instancia de Saved Circuit.
     *
     * @param customImg La imagen que representa el circuito
     * @return una instacia de Saved Ciruit con las caracteristicas del circuito actual.
     */
    public SavedCircuit saveThis(Image customImg) {
        return new SavedCircuit(NumEntradas,NumSalidas,GUI.TableController.getTController().createTable(),customImg);
    }
}
