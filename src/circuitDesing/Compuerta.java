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

/**
 * Clase abstracta que define las propiedades y metodos de una compuerta lógica.
 *
 * @author Jose Alejandro
 * @since 31-08-19
 */
public abstract class Compuerta extends ImageView implements CompuertaLogica {

    ListaEnlazada entradas;
    ListaEnlazada pinesIn;
    ListaEnlazada compuertasOut;
    tipoCompuerta tipo;
    int ID;
    Pin pinOut;
    private int numEntradas;
    private boolean last;
    private boolean first;
    private BigPin bigPin = null;

    /**
     * Constructor de la clase.
     *
     * @param entradas cantidad de entradas de la compuerta.
     * @param ID numero entero que indentifica a la compuerta.
     * @param tipo tipo de compuerta segun el enum tipoCompuerta.
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
        DoubleProperty startX2 = new SimpleDoubleProperty(this.getX()+ 150);
        DoubleProperty startY2 = new SimpleDoubleProperty(this.getY()+40);

        //         ________________________________________
        //________/Ciclo para creacion de pines por entrada
        while (cont < entradas) {
            DoubleProperty startY = new SimpleDoubleProperty(((cont * 40) + 20)*2/numEntradas);
            Color colorRandomIn = Color.color(Math.random(),Math.random(),Math.random());
            if (numEntradas < 4 & this.tipo != tipoCompuerta.Custom) {
                Pin pin = new Pin(colorRandomIn, startX, this.getX(), startY, this.getY() + (((cont * 40) + 20)*2/numEntradas), cont, this, true,false);
                this.pinesIn.insertarInicio(pin);
            }
            else {
                Pin pin = new Pin(colorRandomIn, startX, this.getX(), startY, this.getY() + (cont * 40) + 20, cont, this, true,true);
                this.pinesIn.insertarInicio(pin);
            }
            cont++;
        }

        Color colorRandomOut = Color.color(Math.random(),Math.random(),Math.random());
        //         ____________________________________
        //________/Diferenciacion para tipo custom Gate
        if (this.tipo == tipoCompuerta.Custom){
            pinOut = new Pin(colorRandomOut, startX2, this.getX() + 150, startY2, this.getY() + 40, 0, this, false,true);
        }else {
            pinOut = new Pin(colorRandomOut, startX2, this.getX() + 150, startY2, this.getY() + 40, 0, this, false,false);
        }
    }

    //         ____________________
    //________/Getters and Setters
    public Pin getPinOut() {
        return pinOut;
    }

    public tipoCompuerta getTipo() {
        return tipo;
    }

    public void setTipo(tipoCompuerta tipo) {
        this.tipo = tipo;
    }

    public int getID() { return ID; }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumEntradas() {
        return numEntradas;
    }

    public ListaEnlazada getPinesIn() {
        return pinesIn;
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

    public BigPin getBigPin() {
        return bigPin;
    }

    public void setBigPin(BigPin bigPin) {
        this.bigPin = bigPin;
    }

    void setFirst(boolean b) { this.first = b; }

    boolean isFirst(){ return first; }

    //         ____________________
    //________/METODOS ABSTRACTOS

    /**
     * Metodo abstracto que se sobreescribe segun el tipo de compuerta.
     *
     * @return true or false dependiendo deloperardor y las entradas
     */
    public abstract boolean operar();


    //         ____________________
    //________/BORRADO DE COMPUERTAS

    /**
     * Este es el metodo que se encarga de eliminar una compuerta con todos sus componentes tanto de la interfaz como de las estructuras de datos.
     * Asi como de desconectarse todas las compuertas con las que se comunica.
     */
    public void deleteCompuerta() {
        Circuito.compuertas.eliminarX(this);

        //         _________________________________________________
        //________/Ciclo para desconeccion y borrado de los pines In
        Node currentInNode = pinesIn.getHead();
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

        //         _____________________________________________________
        //________/Ciclo para desconeccion y borrado del pin (pines) Out
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

    /**
     * Toma una compuerta y elimina la conexion en sus pines de Input con un otra compuerta.
     *
     * @param compuerta Compuerta de la que se recibe Output que se desea desconectar.
     */
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

    //         ____________________
    //________/CONEXION DE COMPUERTAS

    /**
     * Metodo para conectar pines unos con otros.
     *
     * @param InpPinID ID del pin Input que quiero conectar.
     * @param compuerta Compuerta a la que me voy a conectar.
     * @param dador Output al que me voy a conectar.
     */
    void conectarPin(int InpPinID, Compuerta compuerta, Pin dador){
        Pin conectando = buscarIDP(InpPinID);
        conectando.setCompuerta(compuerta);
        conectando.setConectado(true);
        conectando.setDador(dador);
        compuerta.getPinOut().setConectado(true);
        compuerta.getCompuertasOut().insertarInicio(this);
    }

    /**
     * Busca un pin por su ID en la lista de pines de Input.
     *
     * @param IDpin Id del pin a buscar.
     * @return la instacia del pin con dicho ID.
     */
    public Pin buscarIDP(int IDpin){
        Node current = this.pinesIn.getHead();
        return getPin(IDpin, current);
    }

    /**
     * Busca pin en una lista
     * @param IDpin ID del pin a buscar.
     * @param current Nodo con la cabeza de la lista de pines.
     * @return la instacia del pin con dicho ID.
     */
    static Pin getPin(int IDpin, Node current) {
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

    //         _______________________
    //________/EJECUCION DE COMPUERTAS

    /**
     * Inserta el valor a la lista enlazada de entradas.
     *
     * @param entrada corresponde la valor booleano de la entrada electronica.
     */
    public void input(boolean entrada){
        this.entradas.insertarInicio(entrada);
    }

    public void input(boolean valor, int pinId) {
        this.entradas.insertarInicio(valor);
        this.entradas.getHead().setData2(pinId); //Valor adicional para conocer de que pin proviene la salida (Para customs)
    }

    /**
     * Metodo que se encarga de iniciar el ciclo de operaciones para el calculo de una salida en una compuerta.
     *
     * @return retorna el valor de salida de la operacion de la compuerta
     */
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

    /**
     * Checkea si todas las entradas estan asignadas.
     *
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

    /**
     * Pregunta a cada pin por su valor asignado.
     */
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

    /**
     * Para una lista de pines contabiliza la cantidad de pines sin conectar que hay.
     * @param listaDePines La lista de pines a analizar.
     * @return un numero entero con la cantidad de pines desconectados.
     */
    int getUnpluggeds(ListaEnlazada listaDePines) {
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

    //         _____________
    //________/OTROS METODOS

    /**
     * Metedo para compuetas NXOR y XOR
     * @return retorna la cantidad de entradas verdaderas a la compuerta
     */
    public int xop(){
        int factorX = 0;
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

    /**
     * Metodo que verifica si el pin de output esta conectado o no.
     *
     * @return true o false dependiendo del estado del pin de salida.
     */
    public boolean isOutIn() {
        return pinOut.IsConectado();
    }

}
