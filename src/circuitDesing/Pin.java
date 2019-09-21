package circuitDesing;

import AbstractFactory.tipoCompuerta;
import GUI.Controller;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import listas.ListaEnlazada;
import listas.Node;

import javax.swing.*;
import java.util.Scanner;

import static circuitDesing.Circuito.selectedPin;
import static java.lang.System.out;

/**
 * Clase dependiente de compuerta se encarga de almancenar las conexiones y valores de las entradas de una compuerta
 */
 public class Pin extends Circle {

    int pinId;
    boolean valor;
    boolean conectado;
    Compuerta compuerta;
    Pin dador;
    Compuerta miCompuerta;
    boolean Input;
    boolean selected;
    Color color;
    final double xI;
    final double yI;
    DoubleProperty x;
    DoubleProperty y;
    boolean simulating;
    boolean simValue;
    boolean asignado;

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    /**
     * Constructor de la clase
     * @param pinId identificador para el numero de pin
     */
    public Pin(Color color, DoubleProperty x,double xI, DoubleProperty y,double yI,int pinId,Compuerta miCompuerta, boolean In){
        super(x.get(), y.get(), 5);
        this.pinId = pinId;
        this.compuerta = null;
        this.conectado = false;
        this.miCompuerta = miCompuerta;
        this.Input = In;
        this.selected = false;
        this.color = color;
        this.xI = xI;
        this.yI = yI;
        this.x = x;
        this.y = y;
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);
        setCursor(Cursor.HAND);


        setOnMouseClicked(this::select);
        entryMethod();

        x.bind(centerXProperty());
        y.bind(centerYProperty());
    }

    private void entryMethod() {
        if (Input) {
            ContextMenu pinMenu = new ContextMenu();
            MenuItem item1 = new MenuItem("1");
            MenuItem item2 = new MenuItem("2");
            pinMenu.getItems().addAll(item1, item2);
            this.setOnContextMenuRequested(event -> pinMenu.show(this, event.getScreenX(), event.getScreenY()));
            item1.setOnAction(e -> setUIValue(true));
            item2.setOnAction(e -> setUIValue(false));
        }
    }

    private void setUIValue(boolean valor) {
        if (!(conectado)){
            this.valor = valor;
            this.asignado = (true);
            if (valor){
                this.setFill(Color.WHITE);
            }else
            {
                this.setFill(Color.BLACK);
            }
        }
    }

    public String IdString() {
        if (Input){
            return "I"+pinId;
        }else {
            return "O"+(pinId+1);
        }
    }

    public int getPinId() {
        return pinId;
    }

    public boolean isAsignado() {
        return asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }

    public double getxI() {
        return xI;
    }

    public Pin getDador() {
        return dador;
    }

    public void setDador(Pin miPinOut) {
        this.dador = miPinOut;
    }

    public boolean IsConectado() {
        return conectado;
    }

    public boolean isIn() {
        return Input;
    }

    public Compuerta getMiCompuerta() {
        return miCompuerta;
    }

    public  double getyI() {
        return yI;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setId(int id) {
        this.pinId = id;
    }

    public boolean isSimulating() {
        return simulating;
    }

    public void setSimulating(boolean simulating) {
        this.simulating = simulating;
    }

    public boolean isSimValue() {
        return simValue;
    }

    public void setSimValue(boolean simValue) {
        this.simValue = simValue;
    }

    public boolean askforinput() {
        if (simulating) {
            out.println("this is simValue"+simValue);
            return simValue;
        } else {
            if (asignado) {
                return valor;
            } else {
                return valor;
            }
        }
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    public Compuerta getCompuerta() {
        return compuerta;
    }

    public void setCompuerta(Compuerta compuerta) {
        this.compuerta = compuerta;
    }

    public boolean isValor() {
        if (conectado){
            if (compuerta.getTipo() == tipoCompuerta.Custom){
                return ((CustomGate)compuerta).CustomOutput(this.dador.pinId);
            }
            else {
                return compuerta.output();
            }
        }
        else{
            return askforinput();
            }
    }


    public void desconectar() {
        if (miCompuerta.getCompuertasOut().getHead() != null) {
            compuerta.getCompuertasOut().eliminarX(miCompuerta);
        }
    }

    public void select(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            if (selected) {
                setFill(color.deriveColor(1, 1, 1, 0.5));
                //System.out.println(this.getCenterX());
                selectedPin = null;
            } else {
                setFill(color.deriveColor(1, 1, 100, 10));
                if (selectedPin == null) {
                    selectedPin = (Pin) e.getSource();
                    out.println(selectedPin);
                } else {
                    boolean selectedType = selectedPin.isIn();
                    out.println(compatibles(this, selectedPin));
                    if (compatibles(this, selectedPin)) {
                        if (selectedType) {  //si la anterior es In
                            selectedPin.setFill(this.color);
                            selectedPin.setStroke(this.color);

                            selectedPin.miCompuerta.conectarPin(selectedPin.getPinId(), this.miCompuerta, this);
                            CircuitLine newLine = new CircuitLine(x, y, selectedPin.x, selectedPin.y, this.color);
                            Controller.getController().Circuito.getChildren().add(newLine);

                            selected = !selected;
                            selectedPin.setSelected(false);
                            selectedPin = null;

                        } else {  // la anterior es out
                            setFill(selectedPin.color.deriveColor(1, 1, 100, 10));
                            setStroke(selectedPin.color);


                            this.miCompuerta.conectarPin(this.getPinId(), selectedPin.miCompuerta, null);
                            this.setDador(selectedPin);
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
            selected = !selected;
        }
    }


    public boolean compatibles(Pin pin1, Pin pin2){
        if (pin1.Input != pin2.Input & pin1.miCompuerta != pin2.miCompuerta){
            if (pin1.Input) {
                return pin2.compatiblesAux(pin1, pin2);
            }else{
                return pin1.compatiblesAux(pin2, pin1);
            }
        }else {
            return false;
        }
    }

    public boolean compatiblesAux(Pin In , Pin Out) {
        if (!In.conectado) {
            ListaEnlazada listaIn = Out.miCompuerta.getPinesIn();
            Compuerta CompuertaDeIn = In.miCompuerta;
            Node current = listaIn.getHead();
            while (current.getNext() != null) {
                Pin currentPin = (Pin) current.getData();
                if (currentPin.compuerta == CompuertaDeIn) {
                    return false;
                }
                current = current.getNext();
            }
            Pin currentPin = (Pin) current.getData();
            return currentPin.compuerta != CompuertaDeIn;
        }
        return false;
    }

}
