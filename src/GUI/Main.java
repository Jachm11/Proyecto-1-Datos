package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import listas.ListaEnlazada;
import listas.Node;

/**
 * Clase main par el diseñador de circuitos, desde aqui se inicialiaza la interfaz principal
 * @author Jose Alejandro
 * @since 31-08-19
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Hepheastus.fxml"));
        primaryStage.setTitle("Hephaestus Circuit Designer");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
        System.out.println("hola");




        ListaEnlazada listaEnlazada = new ListaEnlazada();
        listaEnlazada.insertarInicio("5");
        //System.out.println(listaEnlazada.getSize());
        Node este = listaEnlazada.getHead();
        //System.out.println(listaEnlazada.getHead().getNext());
        /*while (este.getNext() != null) {
            System.out.println(este.getData());
            este = este.getNext();
        }

         */


        //listaEnlazada.insetarEn("3",2);
        /*Node este2 = listaEnlazada.getHead();
        while (este2.getNext() != null) {
            System.out.println(este2.getData());
            este2 = este2.getNext();
        }

         */
        //System.out.println(listaEnlazada.getHead().getNext());

<<<<<<< HEAD
        //Compuerta and1 = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.XOR,2,1));
        //Compuerta and2 = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.AND,2,1));
        //Compuerta and3 = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.AND,2,1));

        //System.out.println(hola.getCompuertasOut());
=======
        Compuerta hola = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.AND,2,0));
        //AND hola = new AND(10,20,2,1);

        System.out.println(hola.getNumEntradas());
        AND holi = new AND(2,1);
        System.out.println(holi.getNumEntradas());
        System.out.println(holi.getPinesIn().getSize());
        System.out.println(hola.getNumEntradas());
        System.out.println(hola.getPinesIn().getSize());
        hola.conectarIn(1,holi);
        Pin temp = (Pin) hola.getPinesIn().getHead().getData();
        System.out.println(temp.getId());
        System.out.println(temp.getCompuerta());
        System.out.println(hola.output());
>>>>>>> gui

        //hola.input(false);
        //hola.input(true);
        //System.out.println(hola.operar());

        //and3.conectarPin(1,and1);
        //and3.conectarPin(2,and3);

        //and1.setValorPinX(1,true);
        //and1.setValorPinX(2,true);

        //and2.setValorPinX(1,true);
        //and2.setValorPinX(2,true);

<<<<<<< HEAD
        //System.out.println(and3.output());
        //System.out.println(and1.output());
        //System.out.println(and2.output());


        //hola.conectarPin(1,hola2);
        //System.out.println(hola.output());
        //Pin pinHola2 = (Pin) hola.getPinesIn().getHead().getData();
        //pinHola2.setValor(false);
        //Pin pinHola1 = (Pin) hola.getPinesIn().getHead().getNext().getData();
        //pinHola1.setValor(true);
        //System.out.println(hola2.output());


        //AND hola = new AND(10,20,2,1);

        //System.out.println(hola.getNumEntradas());
        //AND holi = new AND(2,1);
        //System.out.println(holi.getNumEntradas());
        //System.out.println(holi.getPinesIn().getSize());
        //System.out.println(hola.getNumEntradas());
        //System.out.println(hola.getPinesIn().getSize());
        //hola.conectarPin(1,holi);
        //Pin temp = (Pin) hola.getPinesIn().getHead().getData();
        //System.out.println(temp.getId());
        //System.out.println(temp.getCompuerta());
        //System.out.println(hola.output());



=======
>>>>>>> gui
        //System.out.println(hola.checkEntries());
        //hola.input(true);

        //System.out.println(hola.checkEntries());
        //hola.input(false);
        //System.out.println(hola.operar());
        //System.out.println();
    }



    public static void main(String[] args) { launch(args); }
}
