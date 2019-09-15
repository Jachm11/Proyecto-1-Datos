package GUI;

import AbstractFactory.CompuertaFactory;
import AbstractFactory.tipoCompuerta;
import circuitDesing.Circuito;
import circuitDesing.Compuerta;
import circuitDesing.Pin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;


/**
 * Clase controller para el UI2, en ella se manejan la mayoria de las interacciones en pantalla. Y se
 * definen los tags para los elementos del fxml
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class Controller {

    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private int entradasDefault = 2;
    private Image ImageAND = new Image("GUI/gates/AND.png");
    private Image ImageNAND = new Image("GUI/gates/NAND.png");
    private Image ImageOR = new Image("GUI/gates/OR.png");
    private Image ImageNOR = new Image("GUI/gates/NOR.png");
    private Image ImageNOT = new Image("GUI/gates/NOT.png");
    private Image ImageXOR = new Image("GUI/gates/XOR.png");
    private Image ImageXNOR = new Image("GUI/gates/XNOR.png");
    private static Controller instance = null;

    public Controller() {
        instance = this;
    }
    public static Controller getController(){
            return instance;
        }

        @FXML // fx:id="x1"
        private Font x1; // Value injected by FXMLLoader

        @FXML // fx:id="x2"
        private Color x2; // Value injected by FXMLLoader

        @FXML // fx:id="AND"
        private ImageView AND; // Value injected by FXMLLoader

        @FXML // fx:id="NAND"
        private ImageView NAND; // Value injected by FXMLLoader

        @FXML // fx:id="OR"
        private ImageView OR; // Value injected by FXMLLoader

        @FXML // fx:id="NOR"
        private ImageView NOR; // Value injected by FXMLLoader

        @FXML // fx:id="NOT"
        private ImageView NOT; // Value injected by FXMLLoader

        @FXML // fx:id="XOR"
        private ImageView XOR; // Value injected by FXMLLoader

        @FXML // fx:id="XNOR"
        private ImageView XNOR; // Value injected by FXMLLoader

        @FXML // fx:id="ShowGrid"
        private CheckBox ShowGrid; // Value injected by FXMLLoader

        @FXML // fx:id="x3"
        private Font x3; // Value injected by FXMLLoader

        @FXML // fx:id="x4"
        private Color x4; // Value injected by FXMLLoader

        @FXML// fx:id="Grid"
        private GridPane Grid;

        @FXML // fx:id="Circuito"
        public Circuito Circuito; // Value injected by FXMLLoader

        @FXML
        private AnchorPane Content;

        @FXML // fx:id="GateEntries"
        private MenuButton GateEntries; // Value injected by FXMLLoader

        @FXML // fx:id="DosEntradas"
        private MenuItem DosEntradas; // Value injected by FXMLLoader

        @FXML // fx:id="TresEntradas"
        private MenuItem TresEntradas; // Value injected by FXMLLoader

        @FXML // fx:id="CuatroEntradas"
        private MenuItem CuatroEntradas; // Value injected by FXMLLoader

        @FXML // fx:id="CustomEntradas"
        private MenuItem CustomEntradas; // Value injected by FXMLLoader

        @FXML // fx:id="Run"
        private Button Run; // Value injected by FXMLLoader



    public void clickedOnAND(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.AND,entradasDefault,1));
        System.out.println(newCompuerta);
        newCompuerta.setImage(ImageAND);
        setCompuerta(newCompuerta);
    }

    public void clickedOnNAND(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.NAND,entradasDefault,1));
        newCompuerta.setImage(ImageNAND);
        setCompuerta(newCompuerta);
    }
    public void clickedOnOR(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.OR,entradasDefault,1));
        newCompuerta.setImage(ImageOR);
        setCompuerta(newCompuerta);
    }
    public void clickedOnNOR(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.NOR,entradasDefault,1));
        newCompuerta.setImage(ImageNOR);
        setCompuerta(newCompuerta);
    }
    public void clickedOnNOT(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.NOT,1,1));
        newCompuerta.setImage(ImageNOT);
        setCompuerta(newCompuerta);
    }
    public void clickedOnXOR(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.XOR,entradasDefault,1));
        newCompuerta.setImage(ImageXOR);
        setCompuerta(newCompuerta);
    }

    public void clickedOnXNOR(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.XNOR,entradasDefault,1));
        newCompuerta.setImage(ImageXNOR);
        setCompuerta(newCompuerta);
    }

    private void setCompuerta(Compuerta newCompuerta){

        Circuito.getChildren().add(newCompuerta);
        listas.Node current = newCompuerta.getPinesIn().getHead();
        while ( current.getNext() != null){
            Pin pin = (Pin) current.getData();
            Circuito.getChildren().add(pin);
            current = current.getNext();
        }
        Pin pin = (Pin) current.getData();
        Circuito.getChildren().add(pin);
        Circuito.getChildren().add(newCompuerta.getPinOut());
        Circuito.getCompuertas().insertarInicio(newCompuerta);
        newCompuerta.setCursor(Cursor.HAND);
        newCompuerta.setOnMousePressed(this::handle);
        newCompuerta.setOnMouseDragged(this::handle2);


        ContextMenu compuertaMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Connect");
        MenuItem item2 = new MenuItem("Delete");
        compuertaMenu.getItems().addAll(item1, item2);
        newCompuerta.setOnContextMenuRequested(event -> compuertaMenu.show(newCompuerta,event.getScreenX(), event.getScreenY()));
        //item1.setOnAction(e -> conectar(newCompuerta));
        item2.setOnAction(e -> delete(newCompuerta));
    }


    public void delete(Compuerta compuerta){
        compuerta.deleteCompuerta();
    }


        //FRAGMENTO NO ORIGINAL
    public void handle(MouseEvent t) {
        orgSceneX = t.getSceneX();
        orgSceneY = t.getSceneY();
        orgTranslateX = ((Compuerta)(t.getSource())).getTranslateX();
        orgTranslateY = ((Compuerta)(t.getSource())).getTranslateY();
        listas.Node current = ((Compuerta)(t.getSource())).getPinesIn().getHead();

    }

    private void handle2(MouseEvent t) {
        double offsetX = t.getSceneX() - orgSceneX;
        double offsetY = t.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;

        ((Compuerta)(t.getSource())).setTranslateX(newTranslateX);
        ((Compuerta)(t.getSource())).setTranslateY(newTranslateY);
        listas.Node current = ((Compuerta)(t.getSource())).getPinesIn().getHead();
        while ( current.getNext() != null){
            Pin pin = (Pin) current.getData();
            pin.setCenterX(newTranslateX + pin.getxI());
            pin.setCenterY(newTranslateY + pin.getyI());
            current = current.getNext();

        }

        Pin pin = (Pin) current.getData();
        pin.setCenterX(newTranslateX + pin.getxI());
        pin.setCenterY(newTranslateY + pin.getyI());
        //System.out.println(pin.getX());


        Pin pinOut = (Pin)((Compuerta)(t.getSource())).getPinOut();
        pinOut.setCenterX(newTranslateX + pinOut.getxI());
        pinOut.setCenterY(newTranslateY + pinOut.getyI());


                }

    public void showGrid(MouseEvent event){
        Grid.setGridLinesVisible(!Grid.isGridLinesVisible());
    }

    public void its2Entries(){ this.entradasDefault = 2; }
    public void its3Entries(){
        this.entradasDefault = 3;
    }
    public void its4Entries(){
        this.entradasDefault = 4;
    }
    public void itsXEntries(){int numEntrdas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de entradas"));
    this.entradasDefault = numEntrdas;
    }

    public void runCircuit(){
        //ListaEnlazada compuertasActuales = Circuito.getCompuertas();
        Circuito.setRol();
        Circuito.execute();
    }

    public void generateTable() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TruthTable.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load(), 400, 250));
        stage.show();

        Circuito.setRol();
        TableController.getController().setTable();




    }

    //public ObservableList<Integer> getValues(){
        //ObservableList<Integer> Values = FXCollections.observableArrayList();
        //Values.add(1);
        //return Values;
    //}

}

