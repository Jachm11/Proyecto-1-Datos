package circuitDesing;

import AbstractFactory.tipoCompuerta;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import listas.ListaEnlazada;
import listas.Node;

import java.util.Iterator;


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
        int numEntradas = entradas.getSize();
        ListaEnlazada entradasOrdenadas = new ListaEnlazada();
        Node current = entradas.getHead();
        for(int x = 0 ; x <numEntradas; x++ ){
            while ((int)current.getData2() != x){
                current = current.getNext();
            }
            entradasOrdenadas.insertarAlFinal(current.getData());
        }

        System.out.println(truthTable.getItems().get(0));
        System.out.println(truthTable.getItems().get(1));
        //System.out.println(truthTable.getItems().get(6));
        System.out.println("size"+truthTable.getItems().size());
        System.out.println("esto es:" + (truthTable.getItems().get(0) == truthTable.getItems().get(0)) );

        ObservableList<Integer> fila = getMyRow(numEntradas,entradasOrdenadas);

        //EN UN CICLO INSERTAR EL VALOR entradas+ID+1 PARA EL PIN DE SALIDA CORRESPONDIENTE
        //NECESITA REPLANTEAR LA LOGICA DE LAS SALIDAS CON REFERENCIA POR PIN< O ALGUNA MAGIA CHINA



        return false;
    }

    private ObservableList<Integer> getMyRow(int numEntradas, ListaEnlazada entradasOrdenadas) {
        for(int i = 0; i < truthTable.getItems().size();i++){
            ObservableList<Integer> fila = (ObservableList<Integer>) truthTable.getItems().get(i);
            Iterator<Integer> iterator = fila.iterator();
            for(int j = 0; j < numEntradas; j++){
                for(int x=0; x<j+1 ; x++){
                    iterator.next();
                }
                if (!(iterator == entradasOrdenadas.serchByIndex(j))){
                    break;
                }else{
                    if (j == numEntradas-1){
                        return fila;
                    }
                }
            }
        }
        return null;
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

}
