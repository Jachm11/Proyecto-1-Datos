package circuitDesing;

import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

import java.util.Scanner;

/**
 * Clase dependiente de compuerta se encarga de almancenar las conexiones y valores de las entradas de una compuerta
 */
 public class Pin extends Circle {

    int pinId;
    boolean valor;
    boolean conectado;
    Compuerta compuerta;
    Compuerta miCompuerta;

    public boolean IsConectado() {
        return conectado;
    }


    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    /**
     * Constructor de la clase
     * @param pinId identificador para el numero de pin
     */
    public Pin(Color color, DoubleProperty x, DoubleProperty y,int pinId,Compuerta miCompuerta){
        super(x.get(), y.get(), 5);
        this.pinId = pinId;
        this.compuerta = null;
        this.conectado = false;
        this.miCompuerta = miCompuerta;
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);

        x.bind(centerXProperty());
        y.bind(centerYProperty());
    }

    public int getPinId() {
        return pinId;
    }

    public void setId(int id) {
        this.pinId = id;
    }

    public boolean isValor() {
        if (conectado){
            return compuerta.output();
        }
        else{
            return askforinput();
            }
    }

    public boolean askforinput() {
        return true;
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

    public void desconectar() {
        if (miCompuerta.getCompuertasOut().getHead() != null) {
            compuerta.getCompuertasOut().eliminarX(miCompuerta);
        }
    }
}
