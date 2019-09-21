package circuitDesing;

import AbstractFactory.tipoCompuerta;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import listas.ListaEnlazada;
import listas.Node;

import java.io.IOException;
import java.util.Iterator;


/**
 * Clase donde se almacena un circuito guardado y para ser tratado como compuerta
 */
public class CustomGate extends Compuerta {
    int NumSalidas;
    ListaEnlazada pinesOut;
    BigPin bigPinOut;
    TableView<ObservableList<Integer>> truthTable;
    ListaEnlazada miFila;
    Boolean operatedOnce;
    int numEntradas;

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
        pinesOut.insertarInicio(pinOut);
        this.miFila = new ListaEnlazada();
        this.operatedOnce = false;
        this.numEntradas = entradas;

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

    public void endProcess(){
        this.operatedOnce = false;
        this.entradas = new ListaEnlazada();
        this.miFila = new ListaEnlazada();
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


    public void setOperar() {
        entradas = new ListaEnlazada();
        askPins();
        ListaEnlazada entradasOrdenadas = new ListaEnlazada();
        Node current = entradas.getHead();
        for(int x = 0 ; x <numEntradas; x++ ){
            while ((int)current.getData2() != x){
                current = current.getNext();
            }
            entradasOrdenadas.insertarAlFinal(current.getData());
        }

        getMyRow(entradasOrdenadas);
        //this.operatedOnce = true;

        //EN UN CICLO INSERTAR EL VALOR entradas+ID+1 PARA EL PIN DE SALIDA CORRESPONDIENTE
        //NECESITA REPLANTEAR LA LOGICA DE LAS SALIDAS CON REFERENCIA POR PIN< O ALGUNA MAGIA CHINA

    }

    private void getMyRow(ListaEnlazada entradasOrdenadas) {
        miFila = new ListaEnlazada();
        for(int i = 0; i < truthTable.getItems().size();i++){
            ObservableList<Integer> fila = truthTable.getItems().get(i);
            //Iterator<Integer> iterator = fila.iterator();
            for(int j = 0; j < numEntradas; j++){
                Iterator<Integer> iterator = fila.iterator();
                for(int x=0; x<j ; x++){
                    iterator.next();
                }
                if (!((iterator.next()).equals(ToInt((Boolean) entradasOrdenadas.serchByIndex(j))))){
                    break;
                }else{
                    if (j == numEntradas-1){
                        fila.forEach((n) -> miFila.insertarAlFinal(n));
                        break;
                    }
                }
            }
        }
    }

    private Integer ToInt(Boolean b) {
        if (b){
            return 1;
        }else{
            return 0;
        }
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


    public boolean CustomOutput(Integer numPin) {
            setOperar();
            int num = (int) miFila.serchByIndex(numEntradas + numPin);
            return num == 1;
        }

    public void returnCicle() {
        Node current = pinesOut.getHead();
        while (current.getNext() != null) {
            Pin currentPin = (Pin) current.getData();
            if (!(currentPin.conectado)) {
                System.out.println(CustomOutput(currentPin.pinId));
            }
            current = current.getNext();
        }
        Pin currentPin = (Pin) current.getData();
        if (!(currentPin.conectado)) {
            System.out.println(CustomOutput(currentPin.pinId));
        }
    }


    @Override
    public boolean operar() {
        return false;
    }

    @Override
    public void askPins() {
            Node current = this.pinesIn.getHead();
            Pin pin = (Pin) current.getData();
            while (current.getNext() != null){
                this.input(pin.isValor(),pin.getPinId());
                current = current.getNext();
                pin = (Pin) current.getData();
            }
            this.input(pin.isValor(),pin.getPinId());
        }

    public void setOperatedOnce(boolean b) {
        this.operatedOnce = b;
    }
}
