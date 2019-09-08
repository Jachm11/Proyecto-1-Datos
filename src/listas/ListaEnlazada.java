package listas;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Clase que contiene lo necesario para hacer una lista enlazada
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class ListaEnlazada{

    private Node head;
    private int size;

    /**
     * Constructor de la clase
     */
    public ListaEnlazada(){
        this.head = null;
        this.size = 0;
    }

    public Node getHead() {
        return head;
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    public int getSize() {
        return size;
    }

    public void insertarInicio(Object data){
        Node newNode = new Node(data);
        newNode.next = this.head;
        this.head = newNode;
        this.size++;
    }
    public void eliminarInicio(){
        if (this.head != null){
            this.head = this.head.next;
            this.size--;
        }
    }

    public void eliminarX(Object data) {
        Node current= this.getHead();
        if (current.getNext() == null){
            if (current.getData() == data){
                eliminarInicio();
            }
        }
        else {
            while (current.getNext().getNext() != null) {
                if (current.getNext().getData()==data){
                    current.setNext(current.getNext().getNext());
                }
                current = current.getNext();
            }
            if (current.getNext().getData() == data) {
                current.setNext(null);
            }
        }
    }
    /*public void insetarEn(Object data, int indice){
        Node newNode = new Node(data);
        Node current = this.head;
        int cont = 0;
        if (this.size >= indice) {
            if (indice == 0) {
                insertarInicio(data);
            } else {
                while (cont < indice-1) {
                    current = current.getNext();
                    indice++;
                }
                newNode.setNext(current.getNext().getNext());
                (current.getNext()).setNext(newNode);
                this.size++;
            }
        }
    }
     */

}



