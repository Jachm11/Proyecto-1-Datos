package GUI;


import circuitDesing.Compuerta;
import circuitDesing.Pin;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import listas.ListaEnlazada;

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
        this.size = pines.getSize();
        this.setRadius(15);
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);
        setCursor(Cursor.HAND);


        setOnMouseClicked(this::select);

        x.bind(centerXProperty());
        y.bind(centerYProperty());

    }

    public int getSize() {
        return size;
    }
}

