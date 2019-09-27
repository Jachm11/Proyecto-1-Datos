package GUI;

import AbstractFactory.tipoCompuerta;
import circuitDesing.Circuito;
import circuitDesing.Compuerta;
import circuitDesing.CustomGate;
import circuitDesing.Pin;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import listas.ListaEnlazada;
import listas.Node;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Clase controlador para la tabla de verdad del circuito en pantalla.
 *
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class TableController implements Initializable {
    private static TableController instance = new TableController();

    @FXML
    private TableView<ObservableList<Integer>> TruthTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TruthTable.getStylesheets().add("GUI/DarkTheme.css");
    }

    /**
     * Contructor de la clase
     */
    public TableController() {
        instance = this;
    }

    /**
     * Metodo para adquirir la instacia del controlador.
     *
     * @return retorna la instacia del controlador.
     */
    public static TableController getTController(){
        return instance;
    }

    //         ___________________________________________
    //________/METODOS PARA LA CREACION Y LLENADO DE TABLA

    /**
     * Crea una nueva tabla de verdad basada en el circuito para ser retornada.
     *
     * @return un objeto TableView con las caracteristicas de circuito actual.
     */
    public TableView<ObservableList<Integer>> createTable() {
        TableView<ObservableList<Integer>> circuitTable = new TableView<>();
        setTableAux(circuitTable);
        return circuitTable;
    }

    /**
     * Inicia la preparacion de una tabla de verdad.
     */
    void setTable() {
        setTableAux(TruthTable);
    }


    /**
     * Metodo auxiliar para creacion de columnas de una tabla.
     *
     * @param TruthTable tabla a la que se le agregaran las columnas.
     */
    private void setTableAux(TableView<ObservableList<Integer>> TruthTable) {
        Circuito circuito = Controller.getController().Circuito;
        int numEntradas = circuito.getNumEntradas();
        ListaEnlazada Inputs = circuito.absIn;
        ListaEnlazada Outputs = circuito.absOut;
        ListaEnlazada AbsInputPins = new ListaEnlazada();
        int cont = 0;
        int absPin = 0;
        Node current = Inputs.getHead();
        int posiblidades = (int) Math.pow(2, numEntradas);

        //         ________________________________
        //________/CREACION DE COLUMNAS PARA INPUTS
        while (cont < Inputs.getSize()) {
            Compuerta currentGate = (Compuerta) current.getData();
            int contPin = 0;
            while (contPin < currentGate.getNumEntradas()) {
                Pin currentPin = currentGate.buscarIDP(contPin);
                if (!currentPin.IsConectado()) {
                    TableColumn<ObservableList<Integer>, Integer> column = new TableColumn<>(currentGate.getTipo().toString() + currentGate.getID() + "In" + contPin);
                    AbsInputPins.insertarAlFinal(currentPin);
                    setColumnConfig(absPin, column);

                    TruthTable.getColumns().add(column);
                    absPin++;
                }
                contPin++;
            }
            current = current.getNext();
            cont++;
        }

        //         ________________________________
        //________/CREACION DE COLUMNAS PARA OUTPUTS
        cont = 0;
        current = Outputs.getHead();
        System.out.println("estos son las compuertas con outputs " +Outputs.getSize());
        while (cont < Outputs.getSize()) {
            Compuerta currentGate = (Compuerta) current.getData();
            int salidas;
            if (!(currentGate.getTipo() == tipoCompuerta.Custom)) {
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
                        TableColumn<ObservableList<Integer>, Integer> column = new TableColumn<>(currentGate.getTipo().toString() + currentGate.getID() + "Out" + contPin);
                        setColumnConfig(absPin, column);
                        TruthTable.getColumns().addAll(column);
                        absPin++;
                    }
                    contPin++;
                }
                }else {
                    TableColumn<ObservableList<Integer>, Integer> column = new TableColumn<>(currentGate.getTipo().toString() + currentGate.getID() + "Out");
                setColumnConfig(absPin, column);
                TruthTable.getColumns().addAll(column);
                    absPin++;
                }
            current = current.getNext();
            cont++;
        }

        populate(posiblidades, numEntradas,AbsInputPins,Outputs,TruthTable);
    }


    /**
     * Configura mediante un setCellValueFactory la posicion en una lista de la cual deberá tomar su valor.
     *
     * @param absPin Numero de pin (o bien colimna)
     * @param column Columna en la que se va a trabajar
     */
    private void setColumnConfig(int absPin, TableColumn<ObservableList<Integer>, Integer> column) {
        int currentAbsPin = absPin;
        column.setCellValueFactory(row -> {
            Iterator<Integer> iterator = row.getValue().iterator();
            for(int i = 0; i < currentAbsPin; ++i) {
                iterator.next();
            }
            return new SimpleIntegerProperty(iterator.next()).asObject();
        });
    }

    /**
     * Esta funcion se encarga de crear mediante un ciclo las filas que se insertan en la tabla.
     *
     * @param posibilidades numero de entradas elevado al cuadrado, corresponde al numero de filas a crear.
     * @param NumEntradas numero de entradas del circuito.
     * @param Inputs Lista de inputs (pines) del circuito.
     * @param OutGates Lista de outputs (compuertas) del circuito.
     * @param TruthTable la tabla donde se agregan las filas.
     */
    private void populate(int posibilidades, int NumEntradas,ListaEnlazada Inputs, ListaEnlazada OutGates,TableView<ObservableList<Integer>> TruthTable)  {
        ListaEnlazada valores = new ListaEnlazada();

        //         __________________________________________________________
        //________/Se crea una lista donde se toman los valores segun la fila
        for(int x=0; x < NumEntradas; x++){
            valores.insertarInicio(1);
        }

        //         ___________________________________
        //________/Ciclo para la creacion de las filas
        for(int i = 0 ; i < posibilidades; i++){
            ObservableList<Integer> fila = FXCollections.observableArrayList();

            //         ________________________________________________
            //________/Ciclo que crea los valores de inputs en la tabla
            for(int j = 0; j < NumEntradas; j++){

                int IntDato = (Integer) valores.serchByIndex(j);
                boolean BoolDato = toBool(IntDato);
                fila.add(IntDato);
                Pin currentPin = (Pin)Inputs.serchByIndex(j);
                currentPin.setSimulating(true);
                currentPin.setSimValue(BoolDato);

                //         _________________________________________________
                //________/Condicion (validacion) para el cambio de variable
                if ((int)(i%(posibilidades/Math.pow(2,j+1)))==0){

                    valores.setByIndex(j,reverseIntBool((int)valores.serchByIndex(j)));
                    System.out.println("valor " + j );
                }

                //         _______________________________________
                //________/Condicion para comenzar a llenar salidas
                if (j==NumEntradas-1) {

                    //         _________________________________________________
                    //________/Cicloe de simulaciones por entradas de simulacion
                    for (int x = 0; x < OutGates.getSize(); x++) {
                        Compuerta currentGate = (Compuerta) OutGates.serchByIndex(x);

                        if (currentGate.getTipo() == tipoCompuerta.Custom){
                            CustomGate customGate = (CustomGate) currentGate;
                            Node current = customGate.getPinesOut().getHead();
                            while (current.getNext() != null) {
                                Pin thisCurrentPin = (Pin) current.getData();
                                boolean BoolDato2 = customGate.customOutput(thisCurrentPin.getPinId());
                                int result = toInt(BoolDato2);
                                fila.add(result);

                                current = current.getNext();
                            }
                            Pin thisCurrentPin = (Pin) current.getData();
                            boolean BoolDato2 = customGate.customOutput(thisCurrentPin.getPinId());
                            int result = toInt(BoolDato2);
                            fila.add(result);


                        }else {
                            boolean BoolDato2 = currentGate.output();
                            int result = toInt(BoolDato2);
                            fila.add(result);
                        }
                    }
                }
            }
            //Agrega la fila
            TruthTable.getItems().add(fila);
        }
        //         _______________________________________________________
        //________/Ciclo que finaliza el estado de simulacion de los pines
        for(int j = 0; j < NumEntradas; j++) {
            Pin currentPin = (Pin) Inputs.serchByIndex(j);
            currentPin.setSimulating(false);
        }
    }

    //         ______________
    //________/OTROS METODOS
    /**
     * Funcion que convierte de Booleano a Int.
     *
     * @param boolDato true or false
     * @return 1 or 0
     */
    private int toInt(boolean boolDato){
        if (boolDato){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * Funcion que convierte de Int a Booleano.
     *
     * @param intDato numero entero, solo si es 1 retorna true.
     * @return true or false.
     */
    private boolean toBool(int intDato) {
        return intDato == 1;
    }

    /**
     *Funcion que invierte el valor "booleano" de un integer.
     *
     * @param bool un entero 1 o 0.
     * @return 1 si 0, 0 si 1.
     */
    private int reverseIntBool(int bool) {
        if (bool==1){
            return 0;
        }else {
            return 1;
        }
    }
}

