package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Clase main par el diseñador de circuitos, desde aqui se inicialiaza la interfaz principal desde un archivo .fxml.
 *
 * @author Jose Alejandro
 * @since 31-08-19
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("HepheastusConsole.fxml"));
        root.getStylesheets().add("GUI/DarkTheme.css");
        primaryStage.setTitle("Hephaestus Circuit Designer");
        primaryStage.getIcons().add(new Image("GUI/gates/icon.png"));
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
        System.out.println("hola");
    }

    public static void main(String[] args) { launch(args); }
}
