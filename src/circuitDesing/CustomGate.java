package circuitDesing;

import AbstractFactory.tipoCompuerta;
import GUI.Controller;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import listas.ListaEnlazada;
import listas.Node;
import java.util.Iterator;

/**
 * Clase que instacia un circuito almacenado para ser tratado como una compuerta.
 *
 * @author Jose Alejandro
 * @since 31-08-19
 *
 */
public class CustomGate extends Compuerta {

    ListaEnlazada pinesOut;
    BigPin bigPinOut;
    private TableView<ObservableList<Integer>> truthTable;
    private ListaEnlazada miFila;
    private int numEntradas;
    private String nombre;
    private int NumSalidas;

    /**
     *Constructor de la clase.
     *
     * @param entradas cantidad de entrdas del circuito.
     * @param salidas cantidad de salidas del circuito.
     * @param ID Identificador numerico para la compuerta.
     * @param truthTable Tabla con la que se calculan las salidas del circuito.
     * @param tipo tipo de compuerta.
     */
    public CustomGate(int entradas, int salidas, int ID, javafx.scene.control.TableView<ObservableList<Integer>> truthTable,tipoCompuerta tipo) {
        super(entradas,salidas,tipo);
        this.NumSalidas = salidas;
        this.tipo = tipoCompuerta.Custom;
        this.truthTable = truthTable;
        this.pinesOut = new ListaEnlazada();
        this.ID = ID;
        pinesOut.insertarInicio(pinOut);
        this.miFila = new ListaEnlazada();
        this.numEntradas = entradas;

        //         ________________________________________
        //________/Ciclo para creacion de pines por salida
        for (int i = 1; i < NumSalidas; i++ ) {
            Color colorRamdom = Color.color(Math.random(),Math.random(),Math.random());
            DoubleProperty startX2 = new SimpleDoubleProperty(this.getX()+ 150);
            DoubleProperty startY2 = new SimpleDoubleProperty(this.getY()+40);
            Pin pin = new Pin(colorRamdom,startX2,this.getX()+ 150,startY2,this.getY()+40,i,this,false,true);
            pinesOut.insertarInicio(pin);
        }
    }

    //         ___________________
    //________/Getters and Setters
    public int getSalidas() {
        return NumSalidas;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //         ___________________
    //________/METODOS PARA OPERAR

    /**
     * No se utiliza en esta clase.
     * @return false
     */
    @Override
    public boolean operar() {
        return false;
    }

    /**
     * Retorna el output para un pin especifico.
     * @param numPin Id del pin del que se busca la salida.
     * @return retorna el valor del pin para la fila en la que se encuetra la configuracion actual de entradas del circuito.
     */
    public boolean customOutput(Integer numPin) {
        setOperar();
        int num = (int) miFila.serchByIndex(numEntradas + numPin);
        return num == 1;
    }

    /**
     * Ordena las entradas para poder buscar una coincidencia con las entradas y alguna fila de la tabla.
     */
    private void setOperar() {
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
    }

    /**
     * Varia respecto a la clase padre porque agreaga el ID del pin que genero la entrada.
     */
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

    /**
     * Este metodo encuetra la fila de la lista que coincide con las entradas.
     *
     * @param entradasOrdenadas lista de entradas en orden para en contrar una coindicencia.
     */
    private void getMyRow(ListaEnlazada entradasOrdenadas) {
        miFila = new ListaEnlazada();
        for(int i = 0; i < truthTable.getItems().size();i++){
            ObservableList<Integer> fila = truthTable.getItems().get(i);
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

    /**
     * Calcula la salida para todos los pines out de la compuerta. Hace prints especiales en la consola para poder visulazar sus salidas.
     */
    void returnCicle() {
        Node current = pinesOut.getHead();
        Controller.getController().Console.appendText("Custom Gate named " + "'"+this.nombre+"'" + " generated the following outputs:"+ "\n");
        while (current.getNext() != null) {
            Pin currentPin = (Pin) current.getData();
            if (!(currentPin.conectado)) {
                boolean result = customOutput(currentPin.pinId);
                System.out.println("Este es mi valor" + result);
                Controller.getController().Console.appendText("        "+"Output #"+currentPin.pinId+ " result: " + result + "\n");
            }
            current = current.getNext();
        }
        Pin currentPin = (Pin) current.getData();
        if (!(currentPin.conectado)) {
            boolean result = customOutput(currentPin.pinId);
            System.out.println("Este es mi valor" + result);
            Controller.getController().Console.appendText("        "+"Output #"+currentPin.pinId+ " result: " + result + "\n"+"\n");
        }
    }

    //         _____________
    //________/OTROS METODOS

    /**
     * Busca un pin por su ID en la lista de pines de Output.
     *
     * @param IDpin Id del pin a buscar.
     * @return la instacia del pin con dicho ID.
     */
    public Pin buscarIDPout(int IDpin){
        Node current = this.pinesOut.getHead();
        return getPin(IDpin, current);
    }

    /**
     * Convierte un valor booleano a integer.
     *
     * @param b valor booleano a convertir.
     * @return 1 para true, 0 para false.
     */
    private Integer ToInt(Boolean b) {
        if (b){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * Si existe almenos un pin Out sin conectar retorna false.
     *
     * @return true si todos los out estan conectados.
     */
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
