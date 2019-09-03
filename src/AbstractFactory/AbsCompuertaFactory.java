package AbstractFactory;

import circuitDesing.Compuerta;

public interface AbsCompuertaFactory {
    public Compuerta crearAND(double X, double Y , int entradas, int ID);
    public Compuerta crearNAND(double X, double Y , int entradas,int ID);
    public Compuerta crearOR(double X, double Y , int entradas, int ID);
    public Compuerta crearNOR(double X, double Y , int entradas, int ID);
    public Compuerta crearNOT(double X, double Y, int ID);
    public Compuerta crearXOR(double X, double Y , int entradas, int ID);
    public Compuerta crearXNOR(double X, double Y , int entradas, int ID);
    public Compuerta crearCustomGate(double X, double Y , int entradas, int salidas);
}
