package AbstractFactory;

import circuitDesing.*;

public class CompuertaFactory implements AbsCompuertaFactory {

    @Override
    public Compuerta crearCompuerta(int tipo ,int X, int Y, int entradas) {
        if (tipo!=7) {
            switch (tipo) {
                case 1:
                    return new AND(X, Y, entradas);
                case 2:
                    return new NAND(X, Y, entradas);
                case 3:
                    return new OR(X, Y, entradas);
                case 4:
                    return new NOR(X, Y, entradas);
                case 5:
                    return new NOT(X, Y);
                case 6:
                    return new XOR(X, Y, entradas);
            }
        }
        return new NXOR(X, Y, entradas);
    }
}
