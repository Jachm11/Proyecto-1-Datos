package AbstractFactory;

import circuitDesing.*;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


/**
 * Clase de fabrica concreta, se encarga de la creacion de todos los tipos de compuerta
 * @author Jose Alejandro
 */
public class CompuertaFactory implements AbsCompuertaFactory {

    private int AND = 0;
    private int NAND = 0;
    private int OR = 0;
    private int NOR = 0;
    private int NOT = 0;
    private int XOR = 0;
    private int XNOR = 0;
    private int custom = 0;
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

    public Compuerta crearCompuerta (tipoCompuerta tipo, int entradas, int salidas, javafx.scene.control.TableView<ObservableList<Integer>> circuito){
            return crearCompuertaAux(tipo,entradas,salidas,circuito);
        }

    //Facade
    private Compuerta crearCompuertaAux(tipoCompuerta tipo, int entradas, int salidas, TableView<ObservableList<Integer>> circuito){
        switch (tipo){
            case AND:
                this.AND++;
                return crearAND(entradas,this.AND);
            case NAND:
                this.NAND++;
                return crearNAND(entradas,this.NAND);
            case OR:
                this.OR++;
                return crearOR(entradas,this.OR);
            case NOR:
                this.NOR++;
                return crearNOR(entradas,this.NOR);
            case NOT:
                this.NOT++;
                return crearNOT(this.NOT);
            case XOR:
                this.XOR++;
                return crearXOR(entradas,this.XOR);
            case XNOR:
                this.XNOR++;
                return crearXNOR(entradas,this.XNOR);
            case Custom:
                this.custom++;
                return crearCustomGate(entradas,salidas,this.custom,circuito);
        }
        return null;
    }

    @Override
    public Compuerta crearAND(int entradas, int ID) {
        return new AND(entradas,ID,tipoCompuerta.AND);
    }

    @Override
    public Compuerta crearNAND(int entradas, int ID) {
        return new NAND(entradas,ID,tipoCompuerta.NAND);
    }

    @Override
    public Compuerta crearOR(int entradas, int ID) {
        return new OR(entradas,ID,tipoCompuerta.OR);
    }

    @Override
    public Compuerta crearNOR(int entradas, int ID) {
        return new NOR(entradas,ID,tipoCompuerta.NOR);
    }

    @Override
    public Compuerta crearNOT(int ID) {
        return new NOT(ID,tipoCompuerta.NOT);
    }

    @Override
    public Compuerta crearXOR(int entradas, int ID) {
        return new XOR(entradas,ID,tipoCompuerta.XOR);
    }

    @Override
    public Compuerta crearXNOR(int entradas, int ID) {
        return new XNOR(entradas,ID,tipoCompuerta.XNOR);
    }

    @Override
    public Compuerta crearCustomGate(int entradas, int salidas, int ID, TableView<ObservableList<Integer>> circuito) {
        return new CustomGate(entradas,salidas,ID,circuito,tipoCompuerta.Custom);
    }
}
