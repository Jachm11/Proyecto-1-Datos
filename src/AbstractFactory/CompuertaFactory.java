package AbstractFactory;

import circuitDesing.*;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Clase de fabrica concreta, se encarga de la creacion de todos los tipos de compuerta.
 *
 * @author Jose Alejandro
 * @since 1-09-19
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
    private int buffer = 0;
    private static CompuertaFactory instance = null;

    /**
     * Constructor privado para clase singleton
     */
    private CompuertaFactory(){
    }

    /**
     * Metodo de singleton para obtener la instacia de esta clase. Si no existe una istancia la crea.
     * @return retorna la unica intancia existente de esta clase.
     */
    public static CompuertaFactory getInstance(){
        if (instance==null)
            instance = new CompuertaFactory();
        return instance;
    }

    /**
     * Metodo que se encarga de recibir la informacion generica de una compuerta y ejecuta el comando para crear la compuerta especifica.
     * @param tipo recibe un objeto del enum tipoCompuerta.
     * @param entradas cantidad de entradas que tendra la compuerta.
     * @param salidas cantidad de salidas que tendra la compuerta.
     * @param circuito aplica para custom gates, es la tabla bajo la que se calculan las salidas.
     * @return retorna la instancia de la compuerta especifica solicitada.
     */
    public Compuerta crearCompuerta(tipoCompuerta tipo, int entradas, int salidas, TableView<ObservableList<Integer>> circuito){
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

            case Buffer:
                this.buffer++;
                return crearBuffer(this.buffer);
        }
        return null;
    }

    /**
     * Proviene de la intefaz AbsCompuetaFactory, crea una compuerta tipo AND.
     * @param entradas cantidad de entradas que tendra la compuerta.
     * @param ID Identificador para la compuerta.
     * @return retorna una instacia de AND.
     */
    @Override
    public Compuerta crearAND(int entradas, int ID) {
        return new AND(entradas,ID,tipoCompuerta.AND);
    }

    /**
     * Proviene de la intefaz AbsCompuetaFactory, crea una compuerta tipo NAND.
     * @param entradas cantidad de entradas que tendra la compuerta.
     * @param ID Identificador para la compuerta.
     * @return retorna una instacia de NAND
     */
    @Override
    public Compuerta crearNAND(int entradas, int ID) {
        return new NAND(entradas,ID,tipoCompuerta.NAND);
    }

    /**
     * Proviene de la intefaz AbsCompuetaFactory, crea una compuerta tipo OR.
     * @param entradas cantidad de entradas que tendra la compuerta.
     * @param ID Identificador para la compuerta.
     * @return retorna una instacia de OR.
     */
    @Override
    public Compuerta crearOR(int entradas, int ID) {
        return new OR(entradas,ID,tipoCompuerta.OR);
    }

    /**
     * Proviene de la intefaz AbsCompuetaFactory, crea una compuerta tipo NOR.
     * @param entradas cantidad de entradas que tendra la compuerta.
     * @param ID Identificador para la compuerta.
     * @return retorna una instacia de NOR.
     */
    @Override
    public Compuerta crearNOR(int entradas, int ID) {
        return new NOR(entradas,ID,tipoCompuerta.NOR);
    }

    /**
     * Proviene de la intefaz AbsCompuetaFactory, crea una compuerta tipo NOT.
     * @param ID Identificador para la compuerta.
     * @return retorna una instacia de NOT.
     */
    @Override
    public Compuerta crearNOT(int ID) {
        return new NOT(ID,tipoCompuerta.NOT);
    }

    /**
     * Proviene de la intefaz AbsCompuetaFactory, crea una compuerta tipo Buffer.
     * @param ID Identificador para la compuerta.
     * @return retorna una instacia de Buffer.
     */

    @Override
    public Compuerta crearBuffer(int ID) {
        return new Buffer(ID,tipoCompuerta.Buffer);
    }

    /**
     * Proviene de la intefaz AbsCompuetaFactory, crea una compuerta tipo XOR.
     * @param entradas cantidad de entradas que tendra la compuerta.
     * @param ID Identificador para la compuerta.
     * @return retorna una instacia de XOR.
     */
    @Override
    public Compuerta crearXOR(int entradas, int ID) {
        return new XOR(entradas,ID,tipoCompuerta.XOR);
    }

    /**
     * Proviene de la intefaz AbsCompuetaFactory, crea una compuerta tipo XNOR.
     * @param entradas cantidad de entradas que tendra la compuerta.
     * @param ID Identificador para la compuerta.
     * @return retorna una instacia de XNOR.
     */
    @Override
    public Compuerta crearXNOR(int entradas, int ID) {
        return new XNOR(entradas,ID,tipoCompuerta.XNOR);
    }

    /**
     * Proviene de la intefaz AbsCompuetaFactory, crea una compuerta tipo Custom.
     * @param entradas cantidad de entradas que tendra la compuerta.
     * @param salidas cantidad de salidas que tendra la compuerta.
     * @param ID Identificador para la compuerta.
     * @param circuito Es la tabla bajo la que se calculan las salidas.
     * @return retorna una instacia de CustomGate.
     */
    @Override
    public Compuerta crearCustomGate(int entradas, int salidas, int ID, TableView<ObservableList<Integer>> circuito) {
        return new CustomGate(entradas,salidas,ID,circuito,tipoCompuerta.Custom);
    }
}
