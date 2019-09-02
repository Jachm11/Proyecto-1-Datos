package AbstractFactory;

import circuitDesing.*;

public class CompuertaFactory implements AbsCompuertaFactory {

    private int AND = 0;
    private int NAND = 0;
    private int OR = 0;
    private int NOR = 0;
    private int NOT = 0;
    private int XOR = 0;
    private int XNOR = 0;
    private static CompuertaFactory instance = null;

    /**
     * constructor privado para clse singleton
     */
    private CompuertaFactory(){
    }
    public static  CompuertaFactory getInstance(){
        if (instance==null)
            instance = new CompuertaFactory();
        return instance;
    }

    public Compuerta crearCompuerta (tipoCompuerta tipo, int X, int Y, int entradas, int salidas){
        if (entradas==0){
           return crearCompuertaAux(tipo,X,Y,2,salidas);
        }else{
            return crearCompuertaAux(tipo,X,Y,entradas,salidas);
        }
    }
    //Facade
    private Compuerta crearCompuertaAux(tipoCompuerta tipo, int X, int Y, int entradas,int salidas){
        switch (tipo){
            case AND:
                this.AND++;
                return crearAND(X,Y,entradas,this.AND);
            case NAND:
                this.NAND++;
                return crearNAND(X,Y,entradas,this.NAND);
            case OR:
                this.OR++;
                return crearOR(X,Y,entradas,this.OR);
            case NOR:
                this.NOR++;
                return crearNOR(X,Y,entradas,this.NOR);
            case NOT:
                this.NOT++;
                return crearNOT(X,Y,this.NOT);
            case XOR:
                this.XOR++;
                return crearXOR(X,Y,entradas,this.XOR);
            case XNOR:
                this.XNOR++;
                return crearXNOR(X,Y,entradas,this.XNOR);
            case Custom:
                return crearCustomGate(X,Y,entradas,salidas);
        }
        return null;
    }

    @Override
    public Compuerta crearAND(int X, int Y, int entradas, int ID) {
        return new AND(X,Y,entradas,ID);
    }

    @Override
    public Compuerta crearNAND(int X, int Y, int entradas, int ID) {
        return new NAND(X,Y,entradas,ID);
    }

    @Override
    public Compuerta crearOR(int X, int Y, int entradas, int ID) {
        return new OR(X,Y,entradas,ID);
    }

    @Override
    public Compuerta crearNOR(int X, int Y, int entradas, int ID) {
        return new NOR(X,Y,entradas,ID);
    }

    @Override
    public Compuerta crearNOT(int X, int Y, int ID) {
        return new NOT(X,Y,ID);
    }

    @Override
    public Compuerta crearXOR(int X, int Y, int entradas, int ID) {
        return new XOR(X,Y,entradas,ID);
    }

    @Override
    public Compuerta crearXNOR(int X, int Y, int entradas, int ID) {
        return new XNOR(X,Y,entradas,ID);
    }

    @Override
    public Compuerta crearCustomGate(int X, int Y, int entradas, int salidas) {
        return null;
    }
}
