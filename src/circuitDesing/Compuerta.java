package circuitDesing;

import AbstractFactory.CompuertaLogica;
import AbstractFactory.tipoCompuerta;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import listas.ListaEnlazada;
import listas.Node;

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

    /**
     * Constructor de la clase
     */
    public Compuerta(int entradas, int ID) {
        this.entradas = new ListaEnlazada();
        this.pinesIn = new ListaEnlazada();
        int cont = 0;
        DoubleProperty startX = new SimpleDoubleProperty(this.getX());
        while (cont < entradas) {
            DoubleProperty startY = new SimpleDoubleProperty((this.getY()+(cont*40))+20);
            Color colorRamdom = Color.color(Math.random(),Math.random(),Math.random());
            Pin pin = new Pin(colorRamdom,startX, this.getX(),startY,this.getY()+(cont*40)+20 ,cont,this,true);
            this.pinesIn.insertarInicio(pin);
            System.out.println("Pin creado");
            cont++;
        }
        Color colorRamdom = Color.color(Math.random(),Math.random(),Math.random());
        DoubleProperty startX2 = new SimpleDoubleProperty(this.getX()+ 150);
        DoubleProperty startY2 = new SimpleDoubleProperty(this.getY()+40);
        pinOut = new Pin(colorRamdom,startX2,this.getX()+ 150,startY2,this.getY()+40,-1,this,false);
        this.numEntradas = entradas;
        this.compuertasOut = new ListaEnlazada();
        this.ID = ID;

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

    public void deleteCompuerta(Compuerta this) {
        Circuito.compuertas.eliminarX(this);

        Node currentNode = pinesIn.getHead();
        System.out.println(pinesIn.getHead());
        System.out.println(currentNode);
        if (currentNode.getNext() == null) {
            Pin currentPin = (Pin) currentNode.getData();
            currentPin.desconectar();
        } else {
            while (currentNode.getNext() != null) {
                Pin currentPin = (Pin) currentNode.getData();
                currentPin.desconectar();
                currentNode = currentNode.getNext();
            }
        }

        Node currentNode2 = compuertasOut.getHead();
        if (currentNode2 != null){
            if (currentNode2.getNext() == null) {
                Compuerta currentCompuerta = (Compuerta) currentNode.getData();
                currentCompuerta.desconectarPinesCon(this);
            } else {
                while (currentNode2.getNext() != null) {
                    Compuerta currentCompuerta = (Compuerta) currentNode.getData();
                    currentCompuerta.desconectarPinesCon(this);
                    currentNode2 = currentNode.getNext();
                }
            }
        }

        this.setImage(null);
    }


    private void desconectarPinesCon(Compuerta compuerta){
        Node current = compuerta.pinesIn.getHead();
        Pin firstPin = (Pin)current.getData();
        if (firstPin.getCompuerta() == compuerta){
            firstPin.setCompuerta(null);
            firstPin.setConectado(false);
        }
        while (current.getNext() != null){
            Pin currentPin = (Pin)current.getData();
            if (currentPin.getCompuerta() == compuerta){
                currentPin.setCompuerta(null);
                currentPin.setConectado(false);
            }
            current = current.getNext();
        }

    }


    /**
     * Metedo abstracto que se sobreescribe segun el tipo de compuerta
     * @return true or false dependiendo deloperardor y las entradas
     */
    public abstract boolean operar();

    /**
     * Inserta el valor a la lista enlazada de entradas
     * @param entrada corresponde la valor booleano de la entrada electronica
     */
    public void input(boolean entrada){
        this.entradas.insertarInicio(entrada);
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

    public void conectarPin(int IDpin, Compuerta compuerta){
        buscarIDP(IDpin).setCompuerta(compuerta);
        buscarIDP(IDpin).setConectado(true);
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
    public void askPins(){
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
    public boolean output() {
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

    public int getUnpluggeds() {
        Node current = pinesIn.getHead();
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

}
