package AbstractFactory;

/**
 * Interfaz basica de los metodos par una cmpuerta logica.
 *
 * @author Jose Alejandro
 * @since 1-09-19
 */
public interface CompuertaLogica {
    boolean output();
    void askPins();
    boolean checkEntries();
    int xop();
    boolean operar();
}
