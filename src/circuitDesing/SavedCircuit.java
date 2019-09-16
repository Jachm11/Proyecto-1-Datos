package circuitDesing;

import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import listas.ListaEnlazada;

import javax.swing.*;

public class SavedCircuit extends ImageView {
    int entradas;
    int salidas;
    ListaEnlazada circuito;
    String nombre;

    public SavedCircuit(int entradas, int salidas, ListaEnlazada circuito, Image image){
        this.entradas = entradas;
        this.salidas = salidas;
        this.circuito = circuito;
        String nomine = (JOptionPane.showInputDialog("Nombre su nueva compuerta"));
        this.nombre = nomine;
        this.setCursor(Cursor.HAND);
        this.setImage(image);
        Tooltip.install(this,new Tooltip(nomine));
    }
}
