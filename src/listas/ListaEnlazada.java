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
    public void insertarAlFinal(Object data) {
        if (head == null) {
            insertarInicio(data);
        } else {
            Node newNode = new Node(data);
            Node current = this.head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void eliminarInicio(){
        if (this.head != null){
            this.head = this.head.next;
            this.size--;
        }
    }

    /*public void eliminarX(Object data) {
        if (size>1) {
            Node current = this.getHead();
            while (current.getNext().getNext() != null) {
                if (current.getNext().getData() == data) {
                    current.setNext(current.getNext().getNext());
                    size--;
                    break;
                }
                current = current.getNext();
            }
            if (current.getNext().getData() == data) {
                current.setNext(null);
                size--;
            }else {
                if (current.getData() == data) {
                    eliminarInicio();
                }
            }
        }else{
            if (head.getData() == data){
                head = null;
                size = 0;
            }
        }
    }

     */

    public void eliminarX(Object data){
        if (head != null){
            if (size>1) {
                if (head.getData() == data) {
                    eliminarInicio();
                } else {
                    if (head.getNext().getNext() != null){
                        Node current = this.getHead();
                        while (current.getNext().getNext() != null) {
                            if (current.getNext().getData() == data) {
                                current.setNext(current.getNext().getNext());
                                size--;
                                break;
                            }
                            current = current.getNext();
                        }
                        if (current.getNext().getData() == data) {
                            current.setNext(current.getNext().getNext());
                            size--;
                        }

                    }else{
                        if (head.getNext().getData() == data){
                            head.setNext(null);
                            size = 1;
                        }
                    }

                }
            }else{
                if (head.getData() == data){
                    head = null;
                    size = 0;
                }
            }
        }
    }

    public void setByIndex(int index,Object data){
        Node current= this.getHead();
        for (int x = 0; x < index ; x++ ){
            current = current.getNext();
        }
        current.setData(data);

    }

    public Object serchByIndex(int index) {
        Node current= this.getHead();
        for (int x = 0; x < index ; x++ ){
            current = current.getNext();
        }
        return current.getData();
    }

}



