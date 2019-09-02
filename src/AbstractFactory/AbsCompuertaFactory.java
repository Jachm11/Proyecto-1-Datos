package AbstractFactory;

import circuitDesing.Compuerta;

public interface AbsCompuertaFactory {
    public Compuerta crearCompuerta(int tipo, int X, int Y , int entradas);
}
