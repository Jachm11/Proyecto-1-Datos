package GUI;

import circuitDesing.AND;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import listas.ListaEnlazada;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
        primaryStage.setTitle("Hephaestus Circuit Designer");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        System.out.println("hola");
        ListaEnlazada listaEnlazada = new ListaEnlazada();
        listaEnlazada.insertarInicio("hola");
        System.out.println(listaEnlazada.getSize());
        System.out.println(listaEnlazada.getHead().getData());
        System.out.println(listaEnlazada.getHead().getNext());
        AND hola = new AND(10,10);
        System.out.println(hola.checkEntries());
        hola.input(true);
        hola.input(false);
        System.out.println(hola.checkEntries());
        hola.input(true);
        System.out.println(hola.operar());
    }


    public static void main(String[] args) { launch(args); }
}
