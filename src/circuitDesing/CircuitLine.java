package circuitDesing;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

/**
 * Clase que se encarga de la apariencia grafica de las unione entre circuitos.
 *
 * @author Java Buddy
 * @source http://java-buddy.blogspot.com/
 *
 */
class CircuitLine extends Line {
    CircuitLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY,Color color) {
        startXProperty().bind(startX);
        startYProperty().bind(startY);
        endXProperty().bind(endX);
        endYProperty().bind(endY);
        setStrokeWidth(2);
        setStroke(color);
        setStrokeLineCap(StrokeLineCap.BUTT);
        getStrokeDashArray().setAll(10.0, 5.0);
        setMouseTransparent(true);
    }
}
