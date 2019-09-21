package GUI;

import AbstractFactory.tipoCompuerta;
import circuitDesing.Circuito;
import circuitDesing.Compuerta;
import circuitDesing.CustomGate;
import circuitDesing.Pin;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import listas.ListaEnlazada;
import listas.Node;

import java.io.IOException;
import java.util.Iterator;

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


    public void setTableAux(TableView<ObservableList<Integer>> TruthTable ) throws IOException {
        Circuito circuito = Controller.getController().Circuito;
        int numEntradas = circuito.getNumEntradas();
        int numSalidas = circuito.getNumSalidas();
        ListaEnlazada Inputs = circuito.absIn;
        ListaEnlazada Outputs = circuito.absOut;
        ListaEnlazada AbsInputPins = new ListaEnlazada();
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
                    TableColumn<ObservableList<Integer>, Integer> column = new TableColumn<>(currentGate.getTipo().toString() + currentGate.getID() + "In" + contPin);
                    AbsInputPins.insertarAlFinal(currentPin);
                    int currentAbsPin = absPin;
                    column.setCellValueFactory(row -> {
                        Iterator<Integer> iterator = row.getValue().iterator();
                        for(int i = 0; i < currentAbsPin; ++i) {
                            iterator.next();
                        }
                        return new SimpleIntegerProperty(iterator.next()).asObject();
                    });

                    TruthTable.getColumns().add(column);
                    absPin++;
                }
                contPin++;
            }
            current = current.getNext();
            cont++;
        }
        cont = 0;
        current = Outputs.getHead();
        System.out.println("estos son las compuertas con outputs " +Outputs.getSize());
        while (cont < Outputs.getSize()) {
            Compuerta currentGate = (Compuerta) current.getData();
            int salidas;
            if (!(currentGate.getTipo() == tipoCompuerta.Custom)) {
                //System.out.println();
                salidas = 1;
            }
            else{
                CustomGate thisCast = (CustomGate)currentGate;
                salidas = thisCast.getSalidas();
            }
            if (salidas != 1) {
                CustomGate thisCast = (CustomGate)currentGate;
                int contPin = 0;
                while (contPin<thisCast.getSalidas()) {
                    Pin currentPin = thisCast.buscarIDPout(contPin);
                    if (!currentPin.IsConectado()) {
                        TableColumn<ObservableList<Integer>, Integer> column = new TableColumn<>(currentGate.getTipo().toString() + currentGate.getID() + "Out" + cont);
                        int finalAbsPin = absPin;
                        column.setCellValueFactory(row -> {
                            Iterator<Integer> iterator = row.getValue().iterator();
                            for (int i = 0; i < finalAbsPin; ++i) {
                                iterator.next();
                            }
                            return new SimpleIntegerProperty(iterator.next()).asObject();
                        });
                        TruthTable.getColumns().addAll(column);
                    }
                    contPin++;
                }
                }else {
                    TableColumn<ObservableList<Integer>, Integer> column = new TableColumn<>(currentGate.getTipo().toString() + currentGate.getID() + "Out" + cont);
                    int finalAbsPin = absPin;
                    column.setCellValueFactory(row -> {
                        Iterator<Integer> iterator = row.getValue().iterator();
                        for (int i = 0; i < finalAbsPin; ++i) {
                            iterator.next();
                        }
                        for (int x = 1; x < numSalidas; ++x) {
                            iterator.next();
                        }
                        return new SimpleIntegerProperty(iterator.next()).asObject();
                    });
                    TruthTable.getColumns().addAll(column);
                }
            current = current.getNext();
            cont++;
        }

        populate(posiblidades, numEntradas,numSalidas,AbsInputPins,Outputs,TruthTable);
    }

    private void populate(int posibilidades, int NumEntradas, int NumSalidas,ListaEnlazada Inputs, ListaEnlazada OutGates,TableView<ObservableList<Integer>> TruthTable) throws IOException {
        ListaEnlazada valores = new ListaEnlazada();
        for(int x=0; x < NumEntradas; x++){
            valores.insertarInicio(1);
        }
        for(int i = 0 ; i < posibilidades; i++){
            ObservableList<Integer> values = FXCollections.observableArrayList();
            for(int j = 0; j < NumEntradas; j++){


                int IntDato = (Integer) valores.serchByIndex(j);
                boolean BoolDato = toBool(IntDato);
                values.add(IntDato);
                Pin currentPin = (Pin)Inputs.serchByIndex(j);
                currentPin.setSimulating(true);
                //System.out.println("this is pin data"+BoolDato);
                currentPin.setSimValue(BoolDato);


                //System.out.println(i+1%(posibilidades/Math.pow(2,i+1)));
                if ((int)(i%(posibilidades/Math.pow(2,j+1)))==0){

                    valores.setByIndex(j,reverseIntBool((int)valores.serchByIndex(j)));
                    System.out.println("valor " + j );
                }
                System.out.println(j==NumEntradas-1);
                if (j==NumEntradas-1) {
                    for (int x = 0; x < OutGates.getSize(); x++) {
                        Compuerta currentGate = (Compuerta) OutGates.serchByIndex(x);


                        if (currentGate.getTipo() == tipoCompuerta.Custom){
                            CustomGate customGate = (CustomGate) currentGate;
                            Node current = customGate.getPinesOut().getHead();
                            customGate.setOperar();
                            while (current.getNext() != null) {
                                Pin thisCurrentPin = (Pin) current.getData();
                                boolean BoolDato2 = customGate.CustomOutput(thisCurrentPin.getPinId());
                                int result = toInt(BoolDato2);
                                values.add(result);

                                current = current.getNext();
                            }
                            Pin thisCurrentPin = (Pin) current.getData();
                            boolean BoolDato2 = customGate.CustomOutput(thisCurrentPin.getPinId());
                            int result = toInt(BoolDato2);
                            values.add(result);

                            customGate.endProcess();

                        }else {
                            boolean BoolDato2 = currentGate.output();
                            int result = toInt(BoolDato2);
                            values.add(result);
                        }

                    }
                }

            }

            //System.out.println(values.size());
            TruthTable.getItems().add(values);
        }
        for(int j = 0; j < NumEntradas; j++) {

            Pin currentPin = (Pin) Inputs.serchByIndex(j);
            currentPin.setSimulating(false);
        }


    }
    private int toInt(boolean boolDato){
        if (boolDato){
            return 1;
        }else {
            return 0;
        }
    }

    private boolean toBool(int intDato) {
        return intDato == 1;
    }

    private int reverseIntBool(int bool) {
        if (bool==1){
            return 0;
        }else {
            return 1;
        }
    }


    public void setTable() throws IOException {
        setTableAux(TruthTable);
    }

    public TableView<ObservableList<Integer>> createTable() throws IOException {
        TableView<ObservableList<Integer>> circuitTable = new TableView<>();
        setTableAux(circuitTable);
        return circuitTable;

    }
}

