package AbstractFactory;

/**
 * Interfaz basica de los metodos par una cmpuerta logica
 */
public interface CompuertaLogica {
    boolean output();
    void askPins();
    boolean checkEntries();
    int xop();
    boolean operar();
}
