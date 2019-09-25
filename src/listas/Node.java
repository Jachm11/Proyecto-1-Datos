package listas;

/**
 * Clase que contiene lo necesario para hacer un nodo.
 *
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class Node {

    private Object data;
    Node next;
    private Object data2;

    /**
     * Constructor de la clase.
     */
    public Node(Object data){
        this.next = null;
        this.data = data;
    }

    //         ____________________
    //________/Getters and Setters
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getData2() { return data2; }

    public void setData2(Object data2) { this.data2 = data2; }
}
