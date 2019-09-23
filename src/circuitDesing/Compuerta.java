package circuitDesing;

import AbstractFactory.CompuertaLogica;
import AbstractFactory.tipoCompuerta;
import GUI.Controller;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import listas.ListaEnlazada;
import listas.Node;

import java.io.IOException;

/**
 * Clase abstracta que define las propiedades y metodos de una compuerta lógica
 * @author Jose Alejandro
 * @since 31-08-19
 */
public abstract class Compuerta extends ImageView implements CompuertaLogica {

    private int numEntradas;
    private int numConexiones;
    private int factorX; // Solo para compuertas X
    ListaEnlazada entradas;
    ListaEnlazada pinesIn;
    ListaEnlazada compuertasOut;
    tipoCompuerta tipo;
    int ID;
    Pin pinOut;
    boolean last;
    boolean mid;
    boolean first;
    BigPin bigPin = null;

    /**
     * Constructor de la clase
     */
    public Compuerta(int entradas, int ID,tipoCompuerta tipo) {
        this.entradas = new ListaEnlazada();
        this.pinesIn = new ListaEnlazada();
        this.tipo = tipo;
        int cont = 0;
        this.ID = ID;
        this.numEntradas = entradas;
        this.compuertasOut = new ListaEnlazada();
        DoubleProperty startX = new SimpleDoubleProperty(this.getX());
        while (cont < entradas) {
            DoubleProperty startY = new SimpleDoubleProperty((this.getY()+(cont*40))+20);
            Color colorRandom = Color.color(Math.random(),Math.random(),Math.random());
            if (numEntradas < 4 & this.tipo != tipoCompuerta.Custom) {
                Pin pin = new Pin(colorRandom, startX, this.getX(), startY, this.getY() + (cont * 40) + 20, cont, this, true,false);
                this.pinesIn.insertarInicio(pin);
            }
            else {
                Pin pin = new Pin(colorRandom, startX, this.getX(), startY, this.getY() + (cont * 40) + 20, cont, this, true,true);
                this.pinesIn.insertarInicio(pin);
            }

            System.out.println("Pin creado");
            cont++;
        }
        Color colorRamdom = Color.color(Math.random(),Math.random(),Math.random());
        DoubleProperty startX2 = new SimpleDoubleProperty(this.getX()+ 150);
        DoubleProperty startY2 = new SimpleDoubleProperty(this.getY()+40);
        if (this.tipo == tipoCompuerta.Custom){
            pinOut = new Pin(colorRamdom, startX2, this.getX() + 150, startY2, this.getY() + 40, 0, this, false,true);
        }else {
            pinOut = new Pin(colorRamdom, startX2, this.getX() + 150, startY2, this.getY() + 40, 0, this, false,false);
        }

    }

    public Pin getPinOut() {
        return pinOut;
    }

    public tipoCompuerta getTipo() {
        return tipo;
    }

    public void setTipo(tipoCompuerta tipo) {
        this.tipo = tipo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumEntradas() {
        return numEntradas;
    }

    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }

    public int getNumConexiones() {
        return numConexiones;
    }

    public void setNumConexiones(int numConexiones) {
        this.numConexiones = numConexiones;
    }

    public ListaEnlazada getPinesIn() {
        return pinesIn;
    }

    public void setPinesIn(ListaEnlazada pinesIn) {
        this.pinesIn = pinesIn;
    }

    public void setCompuertasOut(Compuerta compuerta) {
        this.compuertasOut.insertarInicio(compuerta);
    }

    public ListaEnlazada getCompuertasOut() {
        return compuertasOut;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isMid() {
        return mid;
    }

    public void setMid(boolean mid) {
        this.mid = mid;
    }

    public BigPin getBigPin() {
        return bigPin;
    }

    public void setBigPin(BigPin bigPin) {
        this.bigPin = bigPin;
    }

    public void deleteCompuerta(Compuerta this) {
        Circuito.compuertas.eliminarX(this);

        Node currentInNode = pinesIn.getHead();
        System.out.println(pinesIn.getHead());
        //System.out.println(currentInNode);
        while (currentInNode.getNext() != null) {
            Pin currentPin = (Pin) currentInNode.getData();
            currentPin.desconectar();
            Controller.getController().Circuito.getChildren().remove(currentPin);
            currentInNode = currentInNode.getNext();
            }
        Pin currentPin = (Pin) currentInNode.getData();
        currentPin.desconectar();
        Controller.getController().Circuito.getChildren().remove(currentPin);
        if (bigPin != null){
            Controller.getController().Circuito.getChildren().remove(bigPin);
        }

        Controller.getController().Circuito.getChildren().remove(pinOut);

        Node currentOutNode2 = compuertasOut.getHead();
        if (currentOutNode2 != null){
            while (currentOutNode2.getNext() != null) {
                Compuerta currentCompuerta = (Compuerta) currentOutNode2.getData();
                currentCompuerta.desconectarPinesCon(this);
                if (currentOutNode2.getNext() != null) {
                    currentOutNode2 = currentOutNode2.getNext();
                }
            }
                Compuerta currentCompuerta = (Compuerta) currentOutNode2.getData();
                currentCompuerta.desconectarPinesCon(this);
            }
        if (this.tipo == tipoCompuerta.Custom){
            CustomGate thisCast = (CustomGate) this;
            Controller.getController().Circuito.getChildren().remove(thisCast.bigPinOut);
        }
        Controller.getController().Circuito.getChildren().remove(this);
    }


    private void desconectarPinesCon(Compuerta compuerta){
        Node current = pinesIn.getHead();
        while (current.getNext() != null){
            Pin currentPin = (Pin)current.getData();
            if (currentPin.getCompuerta() == compuerta){
                currentPin.desconectar();
            }
            current = current.getNext();
        }
        Pin currentPin = (Pin)current.getData();
        if (currentPin.getCompuerta() == compuerta) {
            currentPin.desconectar();
        }
    }


    /**
     * Metodo abstracto que se sobreescribe segun el tipo de compuerta
     * @return true or false dependiendo deloperardor y las entradas
     */
    public abstract boolean operar();
    //public abstract boolean output(int pinId);

    /**
     * Inserta el valor a la lista enlazada de entradas
     * @param entrada corresponde la valor booleano de la entrada electronica
     */
    public void input(boolean entrada){
        this.entradas.insertarInicio(entrada);
    }

    public void input(boolean valor, int pinId) {
        this.entradas.insertarInicio(valor);
        this.entradas.getHead().setData2(pinId);
    }

    /**
     * Checkea si todas las entradas estan asignadas
     * @return true or false
     */
    public boolean checkEntries(){
        if (this.entradas.getHead() == null){
            return false;
        }
        else {
            return this.entradas.getSize() == numEntradas;
        }
    }

    public void conectarPin(int IDpin, Compuerta compuerta, Pin dador){
        Pin conectando = buscarIDP(IDpin);
        conectando.setCompuerta(compuerta);
        conectando.setConectado(true);
        conectando.setDador(dador);
        compuerta.getPinOut().setConectado(true);
        compuerta.getCompuertasOut().insertarInicio(this);

    }
    public void desconectarPin(int IDpin){
        buscarIDP(IDpin).setCompuerta(null);
        buscarIDP(IDpin).setConectado(false);
    }


    public Pin buscarIDP(int IDpin){
        Node current = this.pinesIn.getHead();
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

    /**
     * Herramienta de desarrollo, se utiliza para simular entradas de manera no grafica
     * @param IDpin Id del pin al que se desea asignar el valor
     * @param valor Valor para el pin
     */
    public void setValorPinX(int IDpin,boolean valor){
        buscarIDP(IDpin).setValor(valor);
        buscarIDP(IDpin).setConectado(false);
    }
    /**
     * Metedo para compuetas NXOR y XOR
     * @return retorna la cantidad de entradas verdaderas a la compuerta
     */
    public int xop(){
        factorX = 0;
        Node current = this.entradas.getHead();
        while( current.getNext() != null) {
            if (current.getData().equals(true)) {
                current = current.getNext();
                factorX++;
            }else {
                current = current.getNext();
            }
        }
        if (current.getData().equals(true)){
            factorX++;
        }
        return factorX;
    }
    public void askPins() {
        Node current = this.pinesIn.getHead();
        Pin pin = (Pin) current.getData();
        while (current.getNext() != null){
            this.input(pin.isValor());
            current = current.getNext();
            pin = (Pin) current.getData();
        }
        this.input(pin.isValor());
    }

        //Facade
    public boolean output(){
        if (checkEntries()){
            return this.operar();
        }
        else{
            this.askPins();
        }
        return this.operar();
    }

    public boolean isOutIn() {
        return pinOut.IsConectado();
    }

    public int getUnpluggeds(ListaEnlazada listaDePines) {
        Node current = listaDePines.getHead();
        int unplugged = 0;
        while (current.getNext() != null){
            Pin currentPin = (Pin)current.getData();
            if (!currentPin.IsConectado()){
                unplugged++;
            }
            current = current.getNext();
        }
        Pin currentPin = (Pin)current.getData();
        if (!currentPin.IsConectado()){
            unplugged++;
        }
        return unplugged;
    }

    public void setFirst(boolean b) {
        this.first = b;
    }
    public boolean isFirst(){
        return first;
    }

}
