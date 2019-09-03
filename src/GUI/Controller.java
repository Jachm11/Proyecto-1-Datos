package GUI;

import AbstractFactory.CompuertaFactory;
import AbstractFactory.tipoCompuerta;
import circuitDesing.Circuito;
import circuitDesing.Compuerta;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


/**
 * Sample Skeleton for 'UI2.fxml' Controller Class
 */
public class Controller {

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    int entradasDefault;
    Image ImageAND = new Image("GUI/gates/AND.png");
    Image ImageNAND = new Image("GUI/gates/NAND.png");
    Image ImageOR = new Image("GUI/gates/OR.png");
    Image ImageNOR = new Image("GUI/gates/NOR.png");
    Image ImageNOT = new Image("GUI/gates/NOT.png");
    Image ImageXOR = new Image("GUI/gates/XOR.png");
    Image ImageXNOR = new Image("GUI/gates/XNOR.png");


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

        @FXML // fx:id="Grid"
        private Circuito Circuito; // Value injected by FXMLLoader


    public void clickedOnAND(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.AND,10,10,entradasDefault,0));
        newCompuerta.setImage(ImageAND);
        setCompuerta(newCompuerta);
    }

    public void clickedOnNAND(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.NAND,10,10,entradasDefault,0));
        newCompuerta.setImage(ImageNAND);
        setCompuerta(newCompuerta);
    }
    public void clickedOnOR(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.OR,10,10,entradasDefault,0));
        newCompuerta.setImage(ImageOR);
        setCompuerta(newCompuerta);
    }
    public void clickedOnNOR(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.NOR,10,10,entradasDefault,0));
        newCompuerta.setImage(ImageNOR);
        setCompuerta(newCompuerta);
    }
    public void clickedOnNOT(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.NOT,10,10,1,0));
        newCompuerta.setImage(ImageNOT);
        setCompuerta(newCompuerta);
    }
    public void clickedOnXOR(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.XOR,10,10,entradasDefault,0));
        newCompuerta.setImage(ImageXOR);
        setCompuerta(newCompuerta);
    }

    public void clickedOnXNOR(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.XNOR,10,10,entradasDefault,0));
        newCompuerta.setImage(ImageXNOR);
        setCompuerta(newCompuerta);
    }
    private void setCompuerta(Compuerta newCompuerta){
        Circuito.add(newCompuerta,3,3);
        Circuito.getCompuertas().insertarInicio(newCompuerta);
        newCompuerta.setCursor(Cursor.HAND);
        newCompuerta.setOnMousePressed(this::handle);
        newCompuerta.setOnMouseDragged(this::handle2);
    }


    public void handle(MouseEvent t) {
        orgSceneX = t.getSceneX();
        orgSceneY = t.getSceneY();
        orgTranslateX = ((Compuerta)(t.getSource())).getTranslateX();
        orgTranslateY = ((Compuerta)(t.getSource())).getTranslateY();
                }

    public void handle2(MouseEvent t) {
        double offsetX = t.getSceneX() - orgSceneX;
        double offsetY = t.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;

        ((Compuerta)(t.getSource())).setTranslateX(newTranslateX);
        ((Compuerta)(t.getSource())).setTranslateY(newTranslateY);
                }

    public void showGrid(MouseEvent event){
        Circuito.setGridLinesVisible(!Circuito.isGridLinesVisible());
    }


}

