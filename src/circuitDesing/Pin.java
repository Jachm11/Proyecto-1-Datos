package circuitDesing;

import java.util.Scanner;

/**
 * Clase dependiente de compuerta se encarga de almancenar las conexiones y valores de las entradas de una compuerta
 */
 public class Pin {

    int id;
    boolean valor;
    boolean conectado;
    Compuerta compuerta;

    public boolean IsConectado() {
        return conectado;
    }


    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    /**
     * Constructor de la clase
     * @param id identificador para el numero de pin
     */
    public Pin(int id){
        this.id = id;
        this.compuerta = null;
        this.conectado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void desconectar(Compuerta miCompuerta) {
        System.out.println(miCompuerta);
        if (miCompuerta.getCompuertasOut().getHead() != null) {
            compuerta.getCompuertasOut().eliminarX(miCompuerta);
        }
    }
}
