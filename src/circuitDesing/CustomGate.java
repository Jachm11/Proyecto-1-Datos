package circuitDesing;

import AbstractFactory.tipoCompuerta;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import listas.ListaEnlazada;
import listas.Node;


/**
 * Clase donde se almacena un circuito guardado y para ser tratado como compuerta
 */
public class CustomGate extends Compuerta {
    int NumSalidas;
    ListaEnlazada pinesOut;
    BigPin bigPinOut;
    TableView truthTable;

    /**
     * Constructor de la clase
     * @param entradas
     * @param ID identificador para la compuerta
     * @param truthTable
     */
    public CustomGate(int entradas, int salidas, int ID, javafx.scene.control.TableView<ObservableList<Integer>> truthTable) {
        super(entradas,salidas);
        this.NumSalidas = salidas;
        this.tipo = tipoCompuerta.Custom;
        this.truthTable = truthTable;
        this.pinesOut = new ListaEnlazada();
        this.ID = ID;
        pinOut.setId(1);
        pinesOut.insertarInicio(pinOut);

        for (int i = 1; i < NumSalidas; i++ ) {
            Color colorRamdom = Color.color(Math.random(),Math.random(),Math.random());
            DoubleProperty startX2 = new SimpleDoubleProperty(this.getX()+ 150);
            DoubleProperty startY2 = new SimpleDoubleProperty(this.getY()+40);
            Pin pin = new Pin(colorRamdom,startX2,this.getX()+ 150,startY2,this.getY()+40,i,this,false);
            pinesOut.insertarInicio(pin);
        }
    }

    public int getSalidas() {
        return NumSalidas;
    }

    public void setSalidas(int salidas) {
        this.NumSalidas = salidas;
    }

    public ListaEnlazada getPinesOut() {
        return pinesOut;
    }

    public BigPin getBigPinOut() {
        return bigPinOut;
    }

    public void setBigPinOut(BigPin bigPinOut) {
        this.bigPinOut = bigPinOut;
    }

    public Pin buscarIDPout(int IDpin){
        Node current = this.pinesOut.getHead();
        Pin PinNode = (Pin) current.getData();
        while (current.getNext() != null){
            if (PinNode.getPinId() != IDpin) {
                current = current.getNext();
                PinNode = (Pin) current.getData();
            }else{
                return PinNode;
            }
        }
        return (Pin) current.getData();
    }

    @Override
    public boolean operar() {
        System.out.println(entradas.getSize() );
        return false;
    }
    @Override
    public boolean isOutIn(){
        Node current = pinesOut.getHead();
        while (current.getNext() != null){
            Pin currentPin = (Pin)current.getData();
            if (currentPin.IsConectado()){
                current = current.getNext();
            }else{
                return false;
            }
        }
        Pin currentPin = (Pin)current.getData();
        return currentPin.IsConectado();
    }


}
