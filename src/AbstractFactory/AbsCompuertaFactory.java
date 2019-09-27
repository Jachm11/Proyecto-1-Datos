package AbstractFactory;

import circuitDesing.Compuerta;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Interfaz para la implementacion del abstractact factory de compuertas.
 *
 * @author Jose Alejandro
 * @since 1-09-19
 */

public interface AbsCompuertaFactory {
    Compuerta crearAND(int entradas, int ID);
    Compuerta crearNAND(int entradas, int ID);
    Compuerta crearOR(int entradas, int ID);
    Compuerta crearNOR(int entradas, int ID);
    Compuerta crearNOT(int ID);
    Compuerta crearBuffer(int ID);
    Compuerta crearXOR(int entradas, int ID);
    Compuerta crearXNOR(int entradas, int ID);
    Compuerta crearCustomGate(int entradas, int salidas, int ID, TableView<ObservableList<Integer>> circuito);
}
