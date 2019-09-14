package circuitDesing;

import GUI.Controller;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
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

/**
 * Clase dependiente de compuerta se encarga de almancenar las conexiones y valores de las entradas de una compuerta
 */
 public class Pin extends Circle {

    int pinId;
    boolean valor;
    boolean conectado;
    Compuerta compuerta;
    Compuerta miCompuerta;
    boolean In;
    boolean selected;
    Color color;
    final double xI;
    final double yI;
    DoubleProperty x;
    DoubleProperty y;

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
        this.In = In;
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

        x.bind(centerXProperty());
        y.bind(centerYProperty());
    }

    public int getPinId() {
        return pinId;
    }

    public double getxI() {
        return xI;
    }

    public boolean IsConectado() {
        return conectado;
    }

    public boolean isIn() {
        return In;
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

    public boolean askforinput() {
        boolean valor = Boolean.parseBoolean(JOptionPane.showInputDialog("Pin numero "+this.pinId+"de compuerta "+this.miCompuerta.getTipo().toString()+this.miCompuerta.getID()));
        //boolean valor = Boolean.parseBoolean(JOptionPane.showInputDialog("True or False"));
        return valor;
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
            return compuerta.output();
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
        if (selected) {
            setFill(color.deriveColor(1, 1, 1, 0.5));
            //System.out.println(this.getCenterX());
            selectedPin = null;
        } else {
            setFill(color.deriveColor(1, 1, 100, 10));
            if (selectedPin == null) {
                selectedPin = (Pin) e.getSource();
                System.out.println(selectedPin);
            } else {
                boolean selectedType = selectedPin.isIn();
                System.out.println(compatibles(this,selectedPin));
                if (compatibles(this,selectedPin)) {
                    if (selectedType)  {  //si la anterior es In
                        selectedPin.setFill(this.color);
                        selectedPin.setStroke(this.color);

                        selectedPin.miCompuerta.conectarPin(selectedPin.getPinId(), this.miCompuerta);
                        CircuitLine newLine = new CircuitLine(x,y,selectedPin.x,selectedPin.y,this.color);
                        Controller.getController().Circuito.getChildren().add(newLine);
                        //this.miCompuerta.conectarPin(this.getPinId(), selectedPin.getCompuerta());


                        selected = !selected;
                        selectedPin.setSelected(false);
                        selectedPin = null;

                    }else{  // la anterior es out
                        setFill(selectedPin.color.deriveColor(1, 1, 100, 10));
                        setStroke(selectedPin.color);


                        this.miCompuerta.conectarPin(this.getPinId(), selectedPin.miCompuerta);
                        CircuitLine newLine = new CircuitLine(selectedPin.x,selectedPin.y,x,y,selectedPin.color);
                        Controller.getController().Circuito.getChildren().add(newLine);
                        //GUI.Controller.getCircuito();
                        //selectedPin.miCompuerta.conectarPin(selectedPin.getPinId(), this.getCompuerta());


                        selected = !selected;
                        selectedPin.setSelected(false);
                        selectedPin = null;
                    }

                    }else{
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


    public boolean compatibles(Pin pin1, Pin pin2){
        if (pin1.In != pin2.In & pin1.miCompuerta != pin2.miCompuerta){
            if (pin1.In) {
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
