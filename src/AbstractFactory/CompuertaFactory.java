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

<<<<<<< HEAD
    public Compuerta crearCompuerta (tipoCompuerta tipo, int entradas, int salidas, javafx.scene.control.TableView<ObservableList<Integer>> circuito){
            return crearCompuertaAux(tipo,entradas,salidas,circuito);
        }

    //Facade
    private Compuerta crearCompuertaAux(tipoCompuerta tipo, int entradas, int salidas, TableView<ObservableList<Integer>> circuito){
=======
    public Compuerta crearCompuerta (tipoCompuerta tipo, int entradas, int salidas){
            return crearCompuertaAux(tipo,entradas,salidas);
        }

    //Facade
    private Compuerta crearCompuertaAux(tipoCompuerta tipo,int entradas,int salidas){
>>>>>>> gui
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
<<<<<<< HEAD
                this.custom++;
                return crearCustomGate(entradas,salidas,this.custom,circuito);
=======
                return crearCustomGate(entradas,salidas);
>>>>>>> gui
        }
        return null;
    }

    @Override
    public Compuerta crearAND(int entradas, int ID) {
        return new AND(entradas,ID);
    }

    @Override
    public Compuerta crearNAND(int entradas, int ID) {
        return new NAND(entradas,ID);
    }

    @Override
    public Compuerta crearOR(int entradas, int ID) {
        return new OR(entradas,ID);
    }

    @Override
    public Compuerta crearNOR(int entradas, int ID) {
        return new NOR(entradas,ID);
    }

    @Override
    public Compuerta crearNOT(int ID) {
        return new NOT(ID);
    }

    @Override
    public Compuerta crearXOR(int entradas, int ID) {
        return new XOR(entradas,ID);
    }

    @Override
    public Compuerta crearXNOR(int entradas, int ID) {
        return new XNOR(entradas,ID);
    }

    @Override
<<<<<<< HEAD
    public Compuerta crearCustomGate(int entradas, int salidas, int ID, TableView<ObservableList<Integer>> circuito) {
        return new CustomGate(entradas,salidas,ID,circuito);
=======
    public Compuerta crearCustomGate(int entradas, int salidas) {
        return null;
>>>>>>> gui
    }
}
