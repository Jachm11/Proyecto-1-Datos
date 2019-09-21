package AbstractFactory;

import java.io.IOException;

/**
 * Interfaz basica de los metodos par una cmpuerta logica
 */
public interface CompuertaLogica {
    boolean output() throws IOException;
    void askPins() throws IOException;
    boolean checkEntries();
    int xop();
    boolean operar();
}
