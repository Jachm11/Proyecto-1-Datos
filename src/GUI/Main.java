package GUI;

import AbstractFactory.CompuertaFactory;
import AbstractFactory.tipoCompuerta;
import circuitDesing.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listas.ListaEnlazada;
import listas.Node;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UI2.fxml"));
        primaryStage.setTitle("Hephaestus Circuit Designer");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
        System.out.println("hola");



        ListaEnlazada listaEnlazada = new ListaEnlazada();
        listaEnlazada.insertarInicio("5");
        System.out.println(listaEnlazada.getSize());
        Node este = listaEnlazada.getHead();
        System.out.println(listaEnlazada.getHead().getNext());
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

        Compuerta hola = (CompuertaFactory.getInstance().crearCompuerta(tipoCompuerta.AND,2,0));
        //AND hola = new AND(10,20,2,1);

        System.out.println(hola.getNumEntradas());
        AND holi = new AND(2,1);
        System.out.println(holi.getNumEntradas());
        System.out.println(holi.getPinesIn().getSize());
        System.out.println(hola.getNumEntradas());
        System.out.println(hola.getPinesIn().getSize());
        //hola.conectarPin(1,holi);
        Pin temp = (Pin) hola.getPinesIn().getHead().getData();
        System.out.println(temp.getId());
        System.out.println(temp.getCompuerta());
        System.out.println(hola.output());



        //System.out.println(hola.checkEntries());
        //hola.input(true);

        //System.out.println(hola.checkEntries());
        //hola.input(false);
        //System.out.println(hola.operar());
        //System.out.println();
    }


    public static void main(String[] args) { launch(args); }
}
