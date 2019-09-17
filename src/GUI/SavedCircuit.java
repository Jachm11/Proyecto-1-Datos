package GUI;

import circuitDesing.Circuito;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import listas.ListaEnlazada;

import javax.swing.*;

public class SavedCircuit extends ImageView {
    int entradas;
    int salidas;
    TableView<ObservableList<Integer>> TablaDeVerdad;
    String nombre;

    public SavedCircuit(int entradas, int salidas, TableView<ObservableList<Integer>> TablaDeVerdad, Image image) {
        this.entradas = entradas;
        this.salidas = salidas;
        this.TablaDeVerdad = TablaDeVerdad;
        String nomine = (JOptionPane.showInputDialog("Nombre su nueva compuerta"));
        this.nombre = nomine;
        this.setCursor(Cursor.HAND);
        this.setImage(image);
        Tooltip.install(this,new Tooltip(nomine));
        setOnMouseClicked(e ->Controller.getController().clickedOnCustom(e));
    }

    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public int getSalidas() {
        return salidas;
    }

    public void setSalidas(int salidas) {
        this.salidas = salidas;
    }

    public TableView<ObservableList<Integer>> getTablaDeVerdad() {
        return TablaDeVerdad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
