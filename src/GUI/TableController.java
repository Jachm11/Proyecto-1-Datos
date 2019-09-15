package GUI;

import circuitDesing.Circuito;
import circuitDesing.Compuerta;
import circuitDesing.Pin;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import listas.ListaEnlazada;
import listas.Node;

public class TableController {
    private static TableController instance = new TableController();

    @FXML
    private TableView<ObservableList<Integer>> TruthTable;

    public TableController() {
        instance = this;
    }
    public static TableController getController(){
        return instance;
    }


    public void setTable() {
        Circuito circuito = Controller.getController().Circuito;
        int numEntradas = circuito.getNumEntradas();
        int numSalidas = circuito.getNumSalidas();
        ListaEnlazada Inputs = circuito.absIn;
        ListaEnlazada Outputs = circuito.absOut;
        int cont = 0;
        int absPin = 0;
        Node current = Inputs.getHead();
        int posiblidades = (int) Math.pow(2, numEntradas);

        System.out.println(numEntradas);
        System.out.println(cont);
        while (cont < Inputs.getSize()) {
            Compuerta currentGate = (Compuerta) current.getData();
            int contPin = 0;
            while (contPin < currentGate.getNumEntradas()) {
                Pin currentPin = currentGate.buscarIDP(contPin);
                if (!currentPin.IsConectado()) {
                    TableColumn column = new TableColumn<ObservableList<Integer>, Integer>(currentGate.getTipo().toString() + currentGate.getID() + "In" + contPin);
                    TruthTable.getColumns().addAll(column);
                    System.out.println(absPin);
                    absPin++;
                }
                contPin++;
            }
            current = current.getNext();
            cont++;
        }
        cont = 0;
        current = Outputs.getHead();
        while (cont < Outputs.getSize()) {
            Compuerta currentGate = (Compuerta) current.getData();
            TableColumn column = new TableColumn<>(currentGate.getTipo().toString() + currentGate.getID() + "Out" + cont);
            TruthTable.getColumns().addAll(column);
            current = current.getNext();
            cont++;
        }
    }



    private ObservableList<Integer> setValues(int posiblidades, int pin) {
        int repeticion = 0;
        ObservableList<Integer> values = FXCollections.observableArrayList();
        System.out.println("LimRep" + Math.pow(2, pin));
        while (repeticion < Math.pow(2, pin)) {
            //if pin != posiblidades
            for (int x = 0; x < (posiblidades / (Math.pow(2, pin+1))); x++) {
                System.out.println("Rep1" + posiblidades / (Math.pow(2, pin+1)));
                System.out.println("added 1");
                values.add(1);
            }
            for (int x = 0; x < posiblidades / Math.pow(2, pin+1);x++){
                {
                    System.out.println("added 0");
                    values.add(0);
                }
            }
            repeticion++;
        }
        return values;
    }

    /*
    private void calculateForEachRow(ListaEnlazada inputs, ListaEnlazada outputs,int posibilidades) {
        int columnas = inputs.getSize() + outputs.getSize();
        int filas = posibilidades;
        int cont = 0;
        int absPin = 0;

        Node current = inputs.getHead();

        while (cont < posibilidades) {
            Compuerta currentGate = (Compuerta)current.getData();
            int contPin = 0;
            while (contPin<currentGate.getNumEntradas()) {

                TableColumn<ObservableList, ?> currentColumn = TruthTable.getColumns().get(absPin);
                ? valor = currentColumn.getCellData(cont);


                contPin++;
                System.out.println(absPin);
                absPin++;
            }
            current = current.getNext();
            cont++;
        }
        cont = 0;
        current = Outputs.getHead();
        while (cont < Outputs.getSize()) {
            Compuerta currentGate = (Compuerta) current.getData();
            TableColumn column = new TableColumn<>(currentGate.getTipo().toString() + currentGate.getID() + "Out" + cont);
            TruthTable.getColumns().addAll(column);
            current = current.getNext();
            cont++;
        }
    }

     */


}

