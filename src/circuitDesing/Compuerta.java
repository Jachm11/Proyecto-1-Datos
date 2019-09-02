package circuitDesing;

import AbstractFactory.CompuertaLogica;
import listas.ListaEnlazada;
import listas.Node;

/**
 * Clase abstracta que define las propiedades de una compuerta lógica
 * @author Jose Alejandro
 * @since 31-08-19
 */
public abstract class Compuerta implements CompuertaLogica {

    int numEntradas;
    int numConexiones;
    int factorX; // Solo para compuertas X
    ListaEnlazada entradas;
    ListaEnlazada pinesIn;
    ListaEnlazada pinesOut;
    double posX;
    double posY;

    /**
     * Constructor de la clase
     */
    public Compuerta(double X,double Y,int entradas){
        this.posX = X;
        this.posY = Y;
        this.entradas = new ListaEnlazada();
        this.pinesIn = new ListaEnlazada();
        this.numEntradas = entradas;
        int cont = 0;
        while (cont < entradas){
            Pin pin = new Pin(cont);
            this.pinesIn.insertarInicio(pin);
            cont++;
        }
    }

    //public deleteCompuerta(){
        //while ()
    //}

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
            if (this.entradas.getSize() != numEntradas){
                return false;
            }else return true;
        }
    }

    public void conectarIn(int IDpin, Compuerta compuerta){
        buscarIDP(IDpin).setCompuerta(compuerta);
        buscarIDP(IDpin).setConectado(true);
    }
    public void desconectarIn(int IDpin){
        buscarIDP(IDpin).setCompuerta(null);
        buscarIDP(IDpin).setConectado(false);
    }

    protected Pin buscarIDP(int IDpin){
        Node current = this.pinesIn.getHead();
        Pin PinNode = (Pin) current.getData();
        while (current.getNext() != null){
            if (PinNode.id != IDpin) {
                current = current.getNext();
                PinNode = (Pin) current.getData();
            }else{
                return PinNode;
            }
        }
        return (Pin) current.getData();
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

}
