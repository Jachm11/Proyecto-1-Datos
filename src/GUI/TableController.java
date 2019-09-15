package GUI;

import circuitDesing.Circuito;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableController {
    private static TableController instance = new TableController();

    @FXML
    private TableView<Integer> TruthTable;

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
        int cont = 0;

        while(cont < entradas)
        TableColumn column = new TableColumn<>("P");
        TruthTable.getColumns().addAll(P1);
    }
}
