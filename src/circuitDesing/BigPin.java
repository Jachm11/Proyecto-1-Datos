package circuitDesing;


import GUI.Controller;
import circuitDesing.CircuitLine;
import circuitDesing.Compuerta;
import circuitDesing.CustomGate;
import circuitDesing.Pin;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import listas.ListaEnlazada;

import javax.swing.*;

import static circuitDesing.Circuito.selectedPin;

public class BigPin extends Pin {
    ListaEnlazada pines;
    Boolean Input;
    int size;
    Compuerta miCompuerta;
    Boolean selected;
    Color color;
    final double xI;
    final double yI;
    DoubleProperty x;
    DoubleProperty y;

    public BigPin (Color color, DoubleProperty x, double xI, DoubleProperty y, double yI, Compuerta miCompuerta, boolean In,ListaEnlazada pines){

        super(color,x,xI,y,yI,0,miCompuerta,In);
        this.miCompuerta = miCompuerta;
        this.Input = In;
        this.selected = false;
        this.color = color;
        this.xI = xI;
        this.yI = yI;
        this.x = x;
        this.y = y;
        this.pines = pines;
        if (In) {
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

    private void specialEntryMethod() {
        if (Input) {
            ContextMenu bigPinMenu = new ContextMenu();
            int cont = 0;
            while (cont < pines.getSize()) {
                Menu inputMenu = new Menu("Input "+cont);
                MenuItem childMenuItem1 = new MenuItem("1");
                MenuItem childMenuItem2 = new MenuItem("2");
                inputMenu.getItems().addAll(childMenuItem1,childMenuItem2);
                int finalCont = cont;
                childMenuItem1.setOnAction(e -> setUIValue(true,finalCont));
                childMenuItem2.setOnAction(e -> setUIValue(false,finalCont));
                bigPinMenu.getItems().add(inputMenu);
                cont++;
            }
            this.setOnContextMenuRequested(event -> bigPinMenu.show(this, event.getScreenX(), event.getScreenY()));
        }
}

    private void setUIValue(boolean b, int id) {
        Pin pinToSet = (Pin) pines.serchByIndex(id);
        pinToSet.setValor(b);
        pinToSet.setAsignado(true);
        setFill(color.deriveColor(1, 1, 100, 10));

    }


    public int getSize() {
        return size;
    }

    public void SpecialSelect(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            if (selected) {
                setFill(color.deriveColor(1, 1, 1, 0.5));
                //System.out.println(this.getCenterX());
                selectedPin = null;
            } else {
                setFill(color.deriveColor(1, 1, 100, 10));
                int P = Integer.parseInt(JOptionPane.showInputDialog("Escoja su pin"));
                if (0 <= P & P <= this.size - 1) {

                    Pin aConectar = (Pin) this.pines.serchByIndex(P);

                    if (selectedPin == null) {
                        selectedPin = this;
                        System.out.println(selectedPin);

                    } else {
                        boolean selectedType = selectedPin.isIn();
                        System.out.println(compatibles(aConectar, selectedPin));
                        if (compatibles(aConectar, selectedPin)) {
                            if (selectedType) {  //si la anterior es In
                                selectedPin.setFill(this.color);
                                selectedPin.setStroke(this.color);

                                selectedPin.miCompuerta.conectarPin(selectedPin.getPinId(), this.miCompuerta, aConectar);
                                CircuitLine newLine = new CircuitLine(x, y, selectedPin.x, selectedPin.y, this.color);
                                Controller.getController().Circuito.getChildren().add(newLine);

                                selected = !selected;
                                selectedPin.setSelected(false);
                                selectedPin = null;

                            } else {  // la anterior es out
                                setFill(selectedPin.color.deriveColor(1, 1, 100, 10));
                                setStroke(selectedPin.color);

                                this.miCompuerta.conectarPin(aConectar.getPinId(), selectedPin.miCompuerta, null);
                                aConectar.setDador(selectedPin);
                                CircuitLine newLine = new CircuitLine(selectedPin.x, selectedPin.y, x, y, selectedPin.color);
                                Controller.getController().Circuito.getChildren().add(newLine);

                                selected = !selected;
                                selectedPin.setSelected(false);
                                selectedPin = null;
                            }

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

