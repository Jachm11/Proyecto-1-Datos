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
            Node temp = this.head;
            this.head = this.head.next;
            this.size--;
        }
    }

}



