package GUI;

import AbstractFactory.CompuertaFactory;
import AbstractFactory.tipoCompuerta;
import circuitDesing.Circuito;
import circuitDesing.Compuerta;
import circuitDesing.Pin;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import java.net.URL;
import java.util.ResourceBundle;


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

        @FXML
        private GridPane Grid;

        @FXML // fx:id="Grid"
        public Circuito Circuito; // Value injected by FXMLLoader

        @FXML
        private AnchorPane Content;




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
        //newCompuerta.setOnMouseReleased(this::handle3);


        ContextMenu compuertaMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Connect");
        MenuItem item2 = new MenuItem("Delete");
        compuertaMenu.getItems().addAll(item1, item2);
        newCompuerta.setOnContextMenuRequested(event -> compuertaMenu.show(newCompuerta,event.getScreenX(), event.getScreenY()));
        //item1.setOnAction(e -> conectar(newCompuerta));
        item2.setOnAction(e -> delete(newCompuerta));
        Line line = new Line();
    }


    public void delete(Compuerta compuerta){
        compuerta.deleteCompuerta();
    }
    public void compuertaKey() {
        System.out.println("jola");
        }


        //FRAGMENTO NO ORIGINAL
    public void handle(MouseEvent t) {
        orgSceneX = t.getSceneX();
        orgSceneY = t.getSceneY();
        orgTranslateX = ((Compuerta)(t.getSource())).getTranslateX();
        orgTranslateY = ((Compuerta)(t.getSource())).getTranslateY();
        listas.Node current = ((Compuerta)(t.getSource())).getPinesIn().getHead();

    }

    public void handle2(MouseEvent t) {
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

}

