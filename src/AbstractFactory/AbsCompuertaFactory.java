package AbstractFactory;

import circuitDesing.Compuerta;

/**
 * Interfaz para la implementacion del abstractact factory de compuertas
 * @author Jose Alejandro
 */

public interface AbsCompuertaFactory {
    public Compuerta crearAND(int entradas, int ID);
    public Compuerta crearNAND(int entradas,int ID);
    public Compuerta crearOR(int entradas, int ID);
    public Compuerta crearNOR(int entradas, int ID);
    public Compuerta crearNOT(int ID);
    public Compuerta crearXOR(int entradas, int ID);
    public Compuerta crearXNOR( int entradas, int ID);
    public Compuerta crearCustomGate(int entradas, int salidas,int ID);
}
