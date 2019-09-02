package AbstractFactory;

import circuitDesing.Compuerta;

public interface AbsCompuertaFactory {
    public Compuerta crearAND(int X, int Y , int entradas, int ID);
    public Compuerta crearNAND(int X, int Y , int entradas,int ID);
    public Compuerta crearOR(int X, int Y , int entradas, int ID);
    public Compuerta crearNOR(int X, int Y , int entradas, int ID);
    public Compuerta crearNOT(int X, int Y, int ID);
    public Compuerta crearXOR(int X, int Y , int entradas, int ID);
    public Compuerta crearXNOR(int X, int Y , int entradas, int ID);
    public Compuerta crearCustomGate(int X, int Y , int entradas, int salidas);
}
