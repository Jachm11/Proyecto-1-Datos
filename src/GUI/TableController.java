package GUI;

import circuitDesing.Circuito;
import circuitDesing.Compuerta;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import listas.ListaEnlazada;
import listas.Node;

public class TableController {
    private static TableController instance = new TableController();

    @FXML
    private TableView<Valor> TruthTable;

    public TableController() {
        instance = this;
    }
    public static TableController getController(){
        return instance;
    }


    public void setTable() {
        Circuito circuito = Controller.getController().Circuito;
        int entradas = circuito.getEntradas();
        int salidas = circuito.getSalidas();
        ListaEnlazada Inputs = circuito.absIn;
        ListaEnlazada Outputs = circuito.absOut;
        int cont = 0;
        int absPin = 0;
        Node current = Inputs.getHead();
        int posiblidades = (int)Math.pow(2,entradas);
        //System.out.println(Math.pow(2,5));

        System.out.println(entradas);
        System.out.println(cont);
        while (cont < Inputs.getSize()) {
            Compuerta currentGate = (Compuerta)current.getData();
            int contPin = 0;
            while (contPin<currentGate.getNumEntradas()) {
                TableColumn column = new TableColumn<Valor, Integer>(currentGate.getTipo().toString() + currentGate.getID()+"In"+contPin);
                TruthTable.getColumns().addAll(column);
                column.setCellValueFactory(new PropertyValueFactory<>("val"));
                TruthTable.setItems(setValues(posiblidades,absPin));
                contPin++;
                System.out.println(absPin);
                absPin++;
            }
            current = current.getNext();
            cont++;
        }
        cont = 0;
        current = Outputs.getHead();
        while (cont < Outputs.getSize()){
            Compuerta currentGate = (Compuerta)current.getData();
            TableColumn column = new TableColumn<>(currentGate.getTipo().toString() + currentGate.getID()+"Out"+cont);
            TruthTable.getColumns().addAll(column);
            current = current.getNext();
            cont++;
        }

    }

    private ObservableList<Valor> setValues(int posiblidades,int pin) {
        int repeticion = 0;
        ObservableList<Valor> values = FXCollections.observableArrayList();
        while (repeticion < Math.pow(2, pin)) {
            for (int x = 0; x < (posiblidades / (Math.pow(2, pin+1))); x++) {
                System.out.println("added 1");
                values.add(new Valor(1));
            }
            for (int x = 0; x != posiblidades / Math.pow(2, pin+1);x++){
                {
                    System.out.println("added 0");
                    values.add(new Valor(0));
                }
            }
            repeticion++;
        }
        return values;
    }
}

