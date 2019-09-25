package listas;

/**
 * Clase que contiene lo necesario para la implementacion de una lista enlazada.
 *
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

    //         ____________________
    //________/Getters and Setters
    public Node getHead() {
        return head;
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    public int getSize() {
        return size;
    }

    /**
     * Metodo que se encarga de insertar un nodo con un dato al inicio de la lista enlazada.
     *
     * @param data Dato tipo Object que se desea que el nuevo nodo contenga.
     */
    public void insertarInicio(Object data){
        Node newNode = new Node(data);
        newNode.next = this.head;
        this.head = newNode;
        this.size++;
    }

    /**
     * Metodo que se encarga de insertar un nodo con un dato al final de la lista enlazada.
     *
     * @param data Dato tipo Object que se desea que el nuevo nodo contenga.
     */
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

    /**
     * Metodo que se encarga de eliminar el nodo al inicio de la lista enlazada.
     */
    public void eliminarInicio(){
        if (this.head != null){
            this.head = this.head.next;
            this.size--;
        }
    }

    /**
     * Metodo que se encarga de eliminar el nodo con un dato especifico en la primer posicion en que lo encuentre en lista enlazada.
     *
     * @param data Dato tipo Object que se desea eliminar en una posicion desconocida.
     */
    public void eliminarX(Object data){
        //Primero valida para por si es una lista de 2 nodos
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


    /**
     * Metodo que se encarga de cambiar el dato del nodo en una posicion X de la lista enlazada.
     *
     * @param index Indice de la lista donde se quiere insetar el dato. (Empezando en 0).
     * @param data Dato tipo Object que se desea que el nuevo nodo contenga.
     */
    public void setByIndex(int index,Object data){
        Node current= this.getHead();
        for (int x = 0; x < index ; x++ ){
            current = current.getNext();
        }
        current.setData(data);

    }

    /**
     * Metodo que se encarga de buscar el dato que contiene un nodo en X posicion de la lista.
     *
     * @param index Indice de la lista donde se quiere extraer el dato. (Empezando en 0).
     * @return
     */
    public Object serchByIndex(int index) {
        Node current= this.getHead();
        for (int x = 0; x < index ; x++ ){
            current = current.getNext();
        }
        return current.getData();
    }

}



