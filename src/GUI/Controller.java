package GUI;

import AbstractFactory.CompuertaFactory;
import AbstractFactory.tipoCompuerta;
import circuitDesing.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Clase controller para el UI2, en ella se manejan la mayoria de las interacciones en pantalla. Y se
 * definen los tags para los elementos del fxml
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class Controller implements Initializable {

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
    private Image CustomImg = new Image("GUI/gates/custom.png");
    private static Controller instance = null;

    public Controller() {
        instance = this;
    }
    public static Controller getController(){
            return instance;
        }

        @FXML
        private Label Title;


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

        @FXML
        private VBox customVbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //ToolTips
        Tooltip.install(AND, new Tooltip("AND"));
        Tooltip.install(NAND, new Tooltip("NAND"));
        Tooltip.install(OR, new Tooltip("OR"));
        Tooltip.install(NOR, new Tooltip("NOR"));
        Tooltip.install(NOT, new Tooltip("NOT"));
        Tooltip.install(XOR, new Tooltip("XOR"));
        Tooltip.install(XNOR, new Tooltip("XNOR"));
    }



    public void clickedOnAND(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.AND,entradasDefault,1,null));
        System.out.println(newCompuerta);
        newCompuerta.setImage(ImageAND);
        setCompuerta(newCompuerta);
    }

    public void clickedOnNAND(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.NAND,entradasDefault,1,null));
        newCompuerta.setImage(ImageNAND);
        setCompuerta(newCompuerta);
    }
    public void clickedOnOR(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.OR,entradasDefault,1,null));
        newCompuerta.setImage(ImageOR);
        setCompuerta(newCompuerta);
    }
    public void clickedOnNOR(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.NOR,entradasDefault,1,null));
        newCompuerta.setImage(ImageNOR);
        setCompuerta(newCompuerta);
    }
    public void clickedOnNOT(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.NOT,1,1,null));
        newCompuerta.setImage(ImageNOT);
        setCompuerta(newCompuerta);
    }
    public void clickedOnXOR(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.XOR,entradasDefault,1,null));
        newCompuerta.setImage(ImageXOR);
        setCompuerta(newCompuerta);
    }

    public void clickedOnXNOR(MouseEvent t){
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.XNOR,entradasDefault,1,null));
        newCompuerta.setImage(ImageXNOR);
        setCompuerta(newCompuerta);
    }
    public void clickedOnCustom(MouseEvent t){
        SavedCircuit savedCircuit = (SavedCircuit) (t.getSource());
        Compuerta newCompuerta = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.Custom,savedCircuit.getEntradas(),savedCircuit.getSalidas(),savedCircuit.getTablaDeVerdad()));
        newCompuerta.setImage(CustomImg);
        Tooltip.install(newCompuerta, new Tooltip(savedCircuit.nombre));
        setCompuerta(newCompuerta);

    }

    private void setCompuerta(Compuerta newCompuerta){

        Circuito.getChildren().add(newCompuerta);

        System.out.println("soy tipo:"+(newCompuerta.getTipo().toString()));
        if(newCompuerta.getTipo() != tipoCompuerta.Custom & newCompuerta.getNumEntradas()<4) {
            listas.Node current = newCompuerta.getPinesIn().getHead();
            while (current.getNext() != null) {
                Pin pin = (Pin) current.getData();
                Circuito.getChildren().add(pin);
                Tooltip.install(pin, new Tooltip((pin.getMiCompuerta().getTipo().toString()) + pin.getMiCompuerta().getID() + "_" + pin.IdString()));
                current = current.getNext();
            }
            Pin pin = (Pin) current.getData();
            Circuito.getChildren().add(pin);
            Tooltip.install(pin, new Tooltip((pin.getMiCompuerta().getTipo().toString()) + pin.getMiCompuerta().getID() + "_" + pin.IdString()));

        }
        else { //BigPin
            DoubleProperty startX = new SimpleDoubleProperty(newCompuerta.getX()-10);
            DoubleProperty startY = new SimpleDoubleProperty((newCompuerta.getY()+(40)));
            Color colorRandom = Color.color(Math.random(),Math.random(),Math.random());
            BigPin bigPin = new BigPin(colorRandom,startX, newCompuerta.getX()-10,startY,newCompuerta.getY()+40 ,newCompuerta,true,newCompuerta.getPinesIn());
            newCompuerta.setBigPin(bigPin);
            Circuito.getChildren().add(bigPin);
            Tooltip.install(bigPin, new Tooltip((bigPin.getMiCompuerta().getTipo().toString())+"_x"+bigPin.getSize()+"I"));

        }//BigPin
        if(newCompuerta.getTipo()==tipoCompuerta.Custom){
            DoubleProperty startX = new SimpleDoubleProperty(newCompuerta.getX()+133);
            DoubleProperty startY = new SimpleDoubleProperty((newCompuerta.getY()+(40)));
            Color colorRandom = Color.color(Math.random(),Math.random(),Math.random());
            CustomGate thisCustomGate = (CustomGate) newCompuerta;
            BigPin bigPin = new BigPin(colorRandom,startX, newCompuerta.getX()+133,startY,newCompuerta.getY()+40 ,newCompuerta,false,thisCustomGate.getPinesOut());
            thisCustomGate.setBigPinOut(bigPin);
            Circuito.getChildren().add(bigPin);
            Tooltip.install(bigPin, new Tooltip((bigPin.getMiCompuerta().getTipo().toString())+"_x"+bigPin.getSize()+"O"));

        }else {
            Pin pinOut = newCompuerta.getPinOut();
            Circuito.getChildren().add(pinOut);
            Tooltip.install(pinOut, new Tooltip((pinOut.getMiCompuerta().getTipo().toString()) + pinOut.getMiCompuerta().getID() + "_" + pinOut.IdString()));
        }

        Circuito.getCompuertas().insertarInicio(newCompuerta);
        newCompuerta.setCursor(Cursor.HAND);
        newCompuerta.setOnMousePressed(this::handle);
        newCompuerta.setOnMouseDragged(this::handle2);


        ContextMenu compuertaMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Save Circuit as Custom Gate");
        MenuItem item2 = new MenuItem("Delete");
        compuertaMenu.getItems().addAll(item1, item2);
        newCompuerta.setOnContextMenuRequested(event -> compuertaMenu.show(newCompuerta,event.getScreenX(), event.getScreenY()));
        item1.setOnAction(e -> saveCurrentCircuit());
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

    }

    private void handle2(MouseEvent t) {
        double offsetX = t.getSceneX() - orgSceneX;
        double offsetY = t.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;

        Compuerta compuerta = (Compuerta)(t.getSource());
        compuerta.setTranslateX(newTranslateX);
        compuerta.setTranslateY(newTranslateY);

        if (compuerta.getNumEntradas() > 3 | compuerta.getTipo() == tipoCompuerta.Custom){
            BigPin bigPin = compuerta.getBigPin();
            bigPin.setCenterX(newTranslateX + bigPin.getxI());
            bigPin.setCenterY(newTranslateY + bigPin.getyI());
        }
        else {
            listas.Node current = ((Compuerta) (t.getSource())).getPinesIn().getHead();
            while (current.getNext() != null) {
                Pin pin = (Pin) current.getData();
                pin.setCenterX(newTranslateX + pin.getxI());
                pin.setCenterY(newTranslateY + pin.getyI());
                current = current.getNext();

            }

            Pin pin = (Pin) current.getData();
            pin.setCenterX(newTranslateX + pin.getxI());
            pin.setCenterY(newTranslateY + pin.getyI());
        }
        if (compuerta.getTipo() == tipoCompuerta.Custom){
            CustomGate thisCustomGate = (CustomGate) compuerta;
            BigPin bigPin =  thisCustomGate.getBigPinOut();
            bigPin.setCenterX(newTranslateX + bigPin.getxI());
            bigPin.setCenterY(newTranslateY + bigPin.getyI());
            }
        else {
            Pin pinOut = (Pin) ((Compuerta) (t.getSource())).getPinOut();
            pinOut.setCenterX(newTranslateX + pinOut.getxI());
            pinOut.setCenterY(newTranslateY + pinOut.getyI());
            }
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

        if (Circuito.checkCircuit()){
            Circuito.execute();
        }
    }

    public void generateTable() throws IOException {

        if (Circuito.checkCircuit()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TruthTable.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), 400, 250));
            stage.show();
            TableController.getController().setTable();
        }
    }


    public void saveCurrentCircuit() {

        if(Circuito.checkCircuit()){
            SavedCircuit newCustomC = Circuito.saveThis(CustomImg);
            customVbox.getChildren().add(newCustomC);

        }
    }


}

