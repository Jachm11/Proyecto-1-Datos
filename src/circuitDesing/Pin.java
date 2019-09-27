package circuitDesing;

import AbstractFactory.tipoCompuerta;
import GUI.Controller;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import listas.ListaEnlazada;
import listas.Node;
import java.util.Optional;

import static circuitDesing.Circuito.selectedPin;

/**
 * Clase dependiente de compuerta, se encarga de almancenar las conexiones y valores de las entradas de una compuerta.
 *
 * @author Jose Alejandro
 * @since 2-09-19
 */
 public class Pin extends Circle {

    int pinId;
    boolean conectado;
    Compuerta miCompuerta;
    Color color;
    DoubleProperty x;
    DoubleProperty y;
    Boolean inBigPin;
    private Compuerta compuerta;
    private Pin dador;
    private boolean valor;
    private boolean simulating;
    private boolean simValue;
    private boolean asignado;
    private CircuitLine myLine;
    private boolean Input;
    private boolean selected;
    private final double xI;
    private final double yI;

    /**
     *Constructor de la clase.
     *
     * @param color color que tendra el pin.
     * @param x Propiedad doble para la posicion en X.
     * @param xI Valor doble para la posicion en X.
     * @param y Propiedad doble para la posicion en Y.
     * @param yI Valor doble para la posicion en Y.
     * @param pinId Identificador entero para el pin.
     * @param miCompuerta Compuerta a la que pertenece el pin.
     * @param Input Boleano para saber si es un pin de tipo Input.
     * @param inBigPin Boleano para saber si esta encapsulado dentro de un BigPin.
     */
    Pin(Color color, DoubleProperty x, double xI, DoubleProperty y, double yI, int pinId, Compuerta miCompuerta, boolean Input, boolean inBigPin){
        super(x.get(), y.get(), 5);
        this.pinId = pinId;
        this.compuerta = null;
        this.conectado = false;
        this.miCompuerta = miCompuerta;
        this.Input = Input;
        this.selected = false;
        this.color = color;
        this.xI = xI;
        this.yI = yI;
        this.x = x;
        this.y = y;
        this.inBigPin = inBigPin;
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);
        setCursor(Cursor.HAND);

        setOnMouseClicked(this::select);
        entryMethod();

        x.bind(centerXProperty());
        y.bind(centerYProperty());
    }

    //         ___________________
    //________/Getters and Setters
    public void setConectado(boolean conectado) { this.conectado = conectado; }

    public int getPinId() { return pinId; }

    public void setAsignado(boolean asignado) { this.asignado = asignado; }

    public double getxI() { return xI; }

    public void setDador(Pin miPinOut) { this.dador = miPinOut; }

    public boolean IsConectado() { return conectado; }

    public boolean isIn() { return Input; }

    public Compuerta getMiCompuerta() { return miCompuerta; }

    public  double getyI() { return yI; }

    public void setSelected(boolean selected) { this.selected = selected; }

    public void setId(int id) { this.pinId = id; }

    public void setSimulating(boolean simulating) { this.simulating = simulating; }

    public void setSimValue(boolean simValue) { this.simValue = simValue; }

    public void setMyLine(CircuitLine myLine) { this.myLine = myLine; }

    public void setValor(boolean valor) { this.valor = valor; }

    public Compuerta getCompuerta() { return compuerta; }

    public void setCompuerta(Compuerta compuerta) { this.compuerta = compuerta; }

    //         _____________________
    //________/SIMULACION DE SALIDAS

    /**
     * Se encarga de llamar conseguir el valor para el pin, si esta conectado inicia de nuevo el ciclo. Y si no pide un input ya asigando.
     *
     * @return el valor de verdad del pin.
     */
    boolean isValor(){
        if (conectado){
            if (compuerta.getTipo() == tipoCompuerta.Custom){
                return ((CustomGate)compuerta).customOutput(this.dador.pinId);
            }
            else {
                return compuerta.output();
            }
        }
        else{
            return askForInput();
        }
    }

    /**
     * Metodo que se encarga de retornar el valor especifico para una compueta input no conectada.
     *
     * @return retorna el valor ororgado por el usuario o el programa segun la condicion que se cumpla.
     */
    private boolean askForInput(){
        if (simulating) {
            return simValue;
        } else {
            if (asignado) {
                return valor;
            } else {
                //         _____________________
                //________/Peticion de entrada
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Input Entry Confirmation");
                alert.setHeaderText("Initial gates without inputs found");
                alert.setContentText("Please choose an input for "+"pin number " + this.pinId + " of gate " + this.miCompuerta.getTipo().toString() +" " + this.miCompuerta.getID());
                ButtonType uno = new ButtonType("1");
                ButtonType cero = new ButtonType("0");
                alert.getButtonTypes().setAll(uno,cero);

                Optional<ButtonType>result = alert.showAndWait();
                if (result.get() == uno){
                    setUIValue(true);
                    return askForInput();
                }else{
                    setUIValue(false);
                    return askForInput();
                }
            }
        }
    }


    //         _____________________
    //________/IMPLEMETACION GRAFICA
    /**
     * Metodo que crea el menu y asigana las funciones para controlar las funciones de un pin input en la intefaz grafica.
     */
    private void entryMethod() {
        if (Input) {
            ContextMenu pinMenu = new ContextMenu();
            MenuItem item1 = new MenuItem("1");
            MenuItem item2 = new MenuItem("0");
            MenuItem item3 = new MenuItem("Disconnect");
            pinMenu.getItems().addAll(item1, item2,item3);
            this.setOnContextMenuRequested(event -> pinMenu.show(this, event.getScreenX(), event.getScreenY()));
            item1.setOnAction(e -> setUIValue(true));
            item2.setOnAction(e -> setUIValue(false));
            item3.setOnAction(e -> desconectar());
        }
    }

    /**
     * Asigna el valor al pin por el metodo grafico.
     *
     * @param valor true o false.
     */
    private void setUIValue(boolean valor) {
        if (!(conectado)){
            this.valor = valor;
            this.asignado = (true);
            setColorValue(valor);
        }
    }

    /**
     * Cambia el color segun el valor del pin. Blanco true, negro false.
     *
     * @param valor true o false.
     */
    void setColorValue(Boolean valor){
        if (valor){
            this.setFill(Color.WHITE);
        }else{
            this.setFill(Color.BLACK);
        }
    }

    /**
     * Este metodo es el encargado de la conexion de dos pines en la intefaz grafica. Instacia ademas la linea de conexion.
     *
     * @param e evento de mouse.
     */
    public void select(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            //         ________
            //________/Deselect
            if (selected) {
                setFill(color.deriveColor(1, 1, 1, 0.5));
                selectedPin = null;

                //         ______
                //________/Select
            } else {
                setFill(color.deriveColor(1, 1, 100, 10));
                if (selectedPin == null) {
                    selectedPin = (Pin) e.getSource();
                } else {
                    boolean selectedType = selectedPin.isIn();
                    if (compatibles(this, selectedPin)) {

                        //         ______________________________
                        //________/El anterior es un pin de Input
                        if (selectedType) {
                            selectedPin.setFill(this.color.deriveColor(1, 1, 100, 10));
                            selectedPin.setStroke(this.color);
                            setFill(color.deriveColor(1, 1, 100, 10));
                            selectedPin.miCompuerta.conectarPin(selectedPin.getPinId(), this.miCompuerta, this);
                            if (selectedPin.inBigPin){
                                BigPin hisBigPin = (selectedPin.getMiCompuerta()).getBigPin();
                                inputInBigPin(hisBigPin, this.color, x, y);
                            }
                            else{
                                CircuitLine newLine = new CircuitLine(x, y, selectedPin.x, selectedPin.y, this.color);
                                selectedPin.setMyLine(newLine);
                                Controller.getController().Circuito.getChildren().add(newLine);
                            }
                            selected = !selected;
                            selectedPin.setSelected(false);
                            selectedPin = null;

                            //         _______________________________
                            //________/El anterior es un pin de Output
                        } else {
                            setFill(selectedPin.color.deriveColor(1, 1, 100, 10));
                            setStroke(selectedPin.color);

                            this.miCompuerta.conectarPin(this.getPinId(), selectedPin.miCompuerta, selectedPin);
                            this.setDador(selectedPin);

                            if (selectedPin.inBigPin){
                                BigPin hisBigPin = ((CustomGate)(selectedPin.getMiCompuerta())).getBigPinOut();
                                CircuitLine newLine = new CircuitLine(hisBigPin.x, hisBigPin.y, x, y, hisBigPin.color);
                                this.myLine = newLine;
                                Controller.getController().Circuito.getChildren().add(newLine);

                            }else{
                                CircuitLine newLine = new CircuitLine(selectedPin.x, selectedPin.y, x, y, selectedPin.color);
                                this.myLine = newLine;
                                Controller.getController().Circuito.getChildren().add(newLine);
                            }
                            selected = !selected;
                            selectedPin.setSelected(false);
                            selectedPin = null;
                        }

                        //         __________________
                        //________/No son compatibles
                    } else {
                        setFill(color.deriveColor(1, 1, 1, 0.5));
                        selectedPin.setFill(selectedPin.color.deriveColor(1, 1, 1, 0.5));
                        selectedPin.setSelected(false);
                        selected = !selected;
                        selectedPin = null;
                    }
                }
            }
            selected = !selected;
        }
    }

    /**
     * Metodo para evitar repeticion innecesaria de codigo. Corre cuando un input se encuentra en un BigPin y desea realizarse una conexion.
     *
     * @param hisBigPin Big pin del input al que se quiere conectar.
     * @param color color del Output.
     * @param x Posicion en X del output.
     * @param y Posicion en Y del output.
     */
    static void inputInBigPin(BigPin hisBigPin, Color color, DoubleProperty x, DoubleProperty y) {
        hisBigPin.setFill(color.deriveColor(1, 1, 100, 10));
        hisBigPin.setStroke(color);
        CircuitLine newLine = new CircuitLine(x, y, hisBigPin.x, hisBigPin.y, color);
        selectedPin.setMyLine(newLine);
        Controller.getController().Circuito.getChildren().add(newLine);
    }

    /**
     * Metodo que compara dos pines para ver si con compatibles para realizar una conexion.
     * Criterios:
     * Si no esta conectado a otro Pin (si en input).
     * Que no este conectado a esta misma compuerta pero por sentido contrario.
     * Que no sean del mismo tipo de pin.
     *
     * @param pin1 Pin a comparar con otro.
     * @param pin2 Pin con el que se compara.
     * @return True or false para compatibilidad.
     */
    boolean compatibles(Pin pin1, Pin pin2){
        if (pin1.Input != pin2.Input & pin1.miCompuerta != pin2.miCompuerta){
            if (pin1.Input) {
                return pin2.compatiblesAux(pin1, pin2);
            }else{
                return pin1.compatiblesAux(pin2, pin1);
            }
        }else {
            return false;
        }
    }

    /**
     * Metodo auxiliar que recibe los pines por tipo. Realiza las comparaciones por los criterios de compatibilidad.
     *
     * @param In Pin tipo Input
     * @param Out Pint tipo Output
     * @return true or false
     */
    private boolean compatiblesAux(Pin In, Pin Out) {
        if (!In.conectado) {
            ListaEnlazada listaIn = Out.miCompuerta.getPinesIn();
            Compuerta CompuertaDeIn = In.miCompuerta;
            Node current = listaIn.getHead();
            while (current.getNext() != null) {
                Pin currentPin = (Pin) current.getData();
                if (currentPin.compuerta == CompuertaDeIn) {
                    return false;
                }
                current = current.getNext();
            }
            Pin currentPin = (Pin) current.getData();
            return currentPin.compuerta != CompuertaDeIn;
        }
        return false;
    }

    //         ______________
    //________/OTROS METODOS

    /**
     *Se encarga de desconectar un pin Input de quien le este dando Output.
     */
    void desconectar() {
        if (conectado) {
            dador.miCompuerta.compuertasOut.eliminarX(miCompuerta);
            if(dador.miCompuerta.compuertasOut.getSize() == 0){
                dador.setAsignado(false);
                dador.setConectado(false);
            }

            conectado = false;
            Controller.getController().Circuito.getChildren().remove(myLine);
            myLine = null;
            dador = null;
            compuerta = null;
            setFill(color.deriveColor(1, 1, 1, 0.5));
            setStroke(color);
        }
    }

    /**
     * Otorga un caracter String segun el tipo de pin.
     * @return I para input, O para Output.
     */
    public String IdString() {
        if (Input){
            return "I"+pinId;
        }else {
            return "O"+(pinId+1);
        }
    }

}
