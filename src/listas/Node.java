package listas;
/**
 * Clase que contiene lo necesario para hacer un nodo
 * @author Jose Alejandro
 * @since 31-08-19
 */
public class Node {

    private Object data;
    Node next;

    /**
     * Constructor de la clase
     */
    public Node(Object data){
        this.next = null;
        this.data = data;
    }


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
}
