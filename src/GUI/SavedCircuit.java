package GUI;

import circuitDesing.Circuito;
import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import listas.ListaEnlazada;

import javax.swing.*;

public class SavedCircuit extends ImageView {
    int entradas;
    int salidas;
    Circuito circuito;
    String nombre;

    public SavedCircuit(int entradas, int salidas, Circuito circuito, Image image) {
        this.entradas = entradas;
        this.salidas = salidas;
        this.circuito = circuito;
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

    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
