package GUI;

import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.*;

/**
 * Clase que almacena las propiedades de un circuito, para mas tarde ser creado como una custom Gate.
 *
 * @author Jose Alejandro
 * @since 16-09-19
 */
public class SavedCircuit extends ImageView {

    String nombre;
    private int entradas;
    private int salidas;
    private TableView<ObservableList<Integer>> TablaDeVerdad;

    /**
     * Constructor de la clase.
     *
     * @param entradas cantidad de entradas del circuito a guardar.
     * @param salidas catidad de salidas del circuito a guardar.
     * @param TablaDeVerdad tabla de verdad del ciruito.
     * @param image imagen con la que se va a representar el circuito.
     */
    public SavedCircuit(int entradas, int salidas, TableView<ObservableList<Integer>> TablaDeVerdad, Image image) {
        this.entradas = entradas;
        this.salidas = salidas;
        this.TablaDeVerdad = TablaDeVerdad;
        String nomine = (JOptionPane.showInputDialog("Give a name to your new Custom Gate:"));
        this.nombre = nomine;
        this.setCursor(Cursor.HAND);
        this.setImage(image);
        Tooltip.install(this,new Tooltip(nomine));
        setOnMouseClicked(e ->Controller.getController().clickedOnCustom(e));
    }

    //         ___________________
    //________/Getters and Setters
    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public int getSalidas() {
        return salidas;
    }

    public TableView<ObservableList<Integer>> getTablaDeVerdad() {
        return TablaDeVerdad;
    }
}
