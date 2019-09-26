package circuitDesing;

import GUI.Controller;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import listas.ListaEnlazada;
import javax.swing.*;

import static circuitDesing.Circuito.selectedPin;

/**
 * Clase hija de pin, que se engarga de encapsular n cantidad de pines. (n>3).
 *
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class BigPin extends Pin {

    Color color;
    DoubleProperty x;
    DoubleProperty y;
    private ListaEnlazada pines;
    private Boolean Input;
    private int size;
    private Compuerta miCompuerta;
    private Boolean selected;


    /**
     * Constructor de la clase.
     *
     * @param color color que tendra el pin.
     * @param x Propiedad doble para la posicion en X.
     * @param xI Valor doble para la posicion en X.
     * @param y Propiedad doble para la posicion en Y.
     * @param yI Valor doble para la posicion en Y.
     * @param miCompuerta Compuerta a la que pertenece el pin.
     * @param Input Boleano para saber si es un pin de tipo Input.
     * @param pines Lista de pines que en capsula.
     */
    public BigPin (Color color, DoubleProperty x, double xI, DoubleProperty y, double yI, Compuerta miCompuerta, boolean Input,ListaEnlazada pines){

        super(color,x,xI,y,yI,0,miCompuerta,Input,false);
        this.miCompuerta = miCompuerta;
        this.Input = Input;
        this.selected = false;
        this.color = color;
        this.x = x;
        this.y = y;
        this.pines = pines;
        if (Input) {
            this.size = miCompuerta.getNumEntradas();
        }else {
            CustomGate thisCast = (CustomGate) miCompuerta;
            this.size = thisCast.getSalidas();
        }
        this.setRadius(15);
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);
        setCursor(Cursor.HAND);

        setOnMouseClicked(this::SpecialSelect);
        specialEntryMethod();

        x.bind(centerXProperty());
        y.bind(centerYProperty());
    }

    //         ___________________
    //________/Getters and Setters
    public int getSize() {
        return size;
    }

    //         _____________________
    //________/IMPLEMETACION GRAFICA
    /**
     * Metodo que crea el menu con submenus y asigana las funciones para controlar las funciones de un big pin de input en la intefaz grafica.
     */
    private void specialEntryMethod() {
        if (Input) {
            ContextMenu bigPinMenu = new ContextMenu();
            int cont = 0;
            while (cont < pines.getSize()) {
                Menu inputMenu = new Menu("Input "+ cont);
                MenuItem childMenuItem1 = new MenuItem("1");
                MenuItem childMenuItem2 = new MenuItem("0");
                MenuItem childMenuItem3 = new MenuItem("Disconnect");
                inputMenu.getItems().addAll(childMenuItem1,childMenuItem2,childMenuItem3);
                int finalCont = cont;
                childMenuItem1.setOnAction(e -> setUIValue(true,finalCont));
                childMenuItem2.setOnAction(e -> setUIValue(false,finalCont));
                childMenuItem3.setOnAction(e -> ((Pin)(pines.serchByIndex(finalCont))).desconectar());
                bigPinMenu.getItems().add(inputMenu);
                cont++;
            }
            this.setOnContextMenuRequested(event -> bigPinMenu.show(this, event.getScreenX(), event.getScreenY()));
        }
}

    /**
     * Asigna el valor al pin por el metodo grafico.
     *
     * @param b true o false.
     * @param id Id del pin al cualdarle el valor b.
     */
    private void setUIValue(boolean b, int id) {
        Pin pinToSet = (Pin) pines.serchByIndex(id);
        pinToSet.setValor(b);
        pinToSet.setAsignado(true);
        setFill(color.deriveColor(1, 1, 100, 10));

    }

    /**
     * Este metodo es el encargado de la conexion de dos pines en la intefaz grafica. Instacia ademas la linea de conexion. Pero para cuando el pin selecionado es un BigPin.
     *
     * @param e evento de mouse.
     */
    private void SpecialSelect(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            //         ________
            //________/Deselect
            if (selected) {
                setFill(color.deriveColor(1, 1, 1, 0.5));
                selectedPin = null;

                //         ________
                //________/Select
            } else {
                setFill(color.deriveColor(1, 1, 100, 10));
                int P = Integer.parseInt(JOptionPane.showInputDialog("Choose the pin"));
                if (0 <= P & P <= this.size - 1) {
                    Pin aConectar = (Pin) this.pines.serchByIndex(P);
                    if (selectedPin == null) {
                        selectedPin = aConectar;

                    } else {
                        boolean selectedType = selectedPin.isIn();
                        System.out.println(compatibles(aConectar, selectedPin));
                        if (compatibles(aConectar, selectedPin)) {

                            //         ________________________
                            //________/El pin anterior es Input
                            if (selectedType) {

                                selectedPin.miCompuerta.conectarPin(selectedPin.getPinId(), this.miCompuerta, aConectar);

                                if (selectedPin.inBigPin) {
                                    BigPin hisBigPin = selectedPin.getMiCompuerta().getBigPin();
                                    Pin.inputInBigPin(hisBigPin, this.color, x, y);
                                }else {
                                    selectedPin.setFill(this.color);
                                    selectedPin.setStroke(this.color);
                                    CircuitLine newLine = new CircuitLine(x, y, selectedPin.x, selectedPin.y, this.color);
                                    selectedPin.setMyLine(newLine);
                                    Controller.getController().Circuito.getChildren().add(newLine);
                                }

                                selected = !selected;
                                selectedPin.setSelected(false);
                                selectedPin = null;

                                //         ________________________
                                //________/El pin anterior es Output
                            } else {

                                this.miCompuerta.conectarPin(aConectar.getPinId(), selectedPin.miCompuerta, selectedPin);
                                aConectar.setDador(selectedPin);

                                if (selectedPin.inBigPin) {
                                    BigPin hisBigPin = ((CustomGate)(selectedPin.getMiCompuerta())).getBigPinOut();
                                    setFill(hisBigPin.color.deriveColor(1, 1, 100, 10));
                                    setStroke(hisBigPin.color);
                                    CircuitLine newLine = new CircuitLine(hisBigPin.x, hisBigPin.y, x, y, hisBigPin.color);
                                    aConectar.setMyLine(newLine);
                                    Controller.getController().Circuito.getChildren().add(newLine);

                                }else {
                                    setFill(selectedPin.color.deriveColor(1, 1, 100, 10));
                                    setStroke(selectedPin.color);
                                    CircuitLine newLine = new CircuitLine(x, y, selectedPin.x, selectedPin.y, selectedPin.color);
                                    aConectar.setMyLine(newLine);
                                    Controller.getController().Circuito.getChildren().add(newLine);
                                }
                                selected = !selected;
                                selectedPin.setSelected(false);
                                selectedPin = null;
                            }

                            //         ________________________________
                            //________/El pin anterior no es compatible
                        } else {
                            setFill(color.deriveColor(1, 1, 1, 0.5));
                            selectedPin.setFill(selectedPin.color.deriveColor(1, 1, 1, 0.5));
                            selectedPin.setSelected(false);
                            selected = !selected;
                            selectedPin = null;
                        }
                    }
                }
            }
            selected = !selected;
        }
    }
}

