module Proyecto {

    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    exports GUI;
    exports circuitDesing;
    exports AbstractFactory;
    exports listas;
    opens GUI;
}