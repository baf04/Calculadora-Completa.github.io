package Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class ControladorPeso extends Stage implements Initializable {
    private Stage stage;
    private Scene scene;

    @javafx.fxml.FXML
    private TextField resultado;
    private String primernumero = "";
    private String operador = "";
    private String numeroActual = "";
    private double res = 0.0;

    @javafx.fxml.FXML
    private Button siete;
    @javafx.fxml.FXML
    private Button ocho;
    @javafx.fxml.FXML
    private Button dos;
    @javafx.fxml.FXML
    private Button seis;
    @javafx.fxml.FXML
    private Button nueve;
    @javafx.fxml.FXML
    private Button cuatro;
    @javafx.fxml.FXML
    private VBox VBoxPrincipal;
    @javafx.fxml.FXML
    private Button cinco;
    @javafx.fxml.FXML
    private Button cero;
    @javafx.fxml.FXML
    private Button borrar;
    @javafx.fxml.FXML
    private Button uno;
    @javafx.fxml.FXML
    private Button tres;
    @javafx.fxml.FXML
    private Button igual;
    @javafx.fxml.FXML
    private Button decimal;
    @javafx.fxml.FXML
    private ComboBox conversionInicial;
    @javafx.fxml.FXML
    private ComboBox conversionFinal;

    String[] unidadesPeso = {
            "Kilogramo", "Gramo", "Miligramo", "Onza", "Libra", "Tonelada"
    };
    @javafx.fxml.FXML
    private Button Peso;
    @javafx.fxml.FXML
    private Button monetaria;
    @javafx.fxml.FXML
    private Button temperatura;

    public void initialize(URL arg0, ResourceBundle arg1){
        conversionFinal.setPromptText("conversion");
        conversionInicial.setPromptText("convertir");
        conversionFinal.getItems().addAll(unidadesPeso);
        conversionInicial.getItems().addAll(unidadesPeso);
    }

    @javafx.fxml.FXML
    public void clickDos(ActionEvent actionEvent) {
        addNumber("2");
    }

    @javafx.fxml.FXML
    public void clickNueve(ActionEvent actionEvent) {
        addNumber("9");
    }

    @javafx.fxml.FXML
    public void clickUno(ActionEvent actionEvent) {
        addNumber("1");
    }

    @javafx.fxml.FXML
    public void clickCuatro(ActionEvent actionEvent) {
        addNumber("4");
    }

    @javafx.fxml.FXML
    public void clickTres(ActionEvent actionEvent) {
        addNumber("3");
    }

    @javafx.fxml.FXML
    public void clickCinco(ActionEvent actionEvent) {
        addNumber("5");
    }

    @javafx.fxml.FXML
    public void clickCero(ActionEvent actionEvent) {
        addNumber("0");
    }

    @javafx.fxml.FXML
    public void clickSiete(ActionEvent actionEvent) {
        addNumber("7");
    }

    @javafx.fxml.FXML
    public void clickSeis(ActionEvent actionEvent) {
        addNumber("6");
    }

    @javafx.fxml.FXML
    public void clickOcho(ActionEvent actionEvent) {
        addNumber("8");
    }

    public void updateTextField(){
        resultado.setText("");
        resultado.setText(numeroActual);
    }

    public void addNumber(String num){
        numeroActual += num;
        updateTextField();
    }

    @javafx.fxml.FXML
    public void clickBorrar(ActionEvent actionEvent) {
        numeroActual = "";
        resultado.setText("0");
        res = 0.0;
    }

    @javafx.fxml.FXML
    public void clickDecimal(ActionEvent actionEvent) {
        if (!numeroActual.contains(".")) {
            numeroActual += ".";
            resultado.setText(numeroActual);
        }
    }
    @javafx.fxml.FXML
    public void clickIgual(ActionEvent actionEvent) throws IOException {

        applyConversion();
    }
    @javafx.fxml.FXML
    public void clickBasica(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/calc.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void clickCientifica(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/calculadoraCientifica.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @Deprecated
    public void clickConversion(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/calculadoraConversion.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @javafx.fxml.FXML
    public void clickConversionPeso(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/calculadoraConversionPeso.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void clickConversionMonetaria(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/calculadoraConversion.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void clickConversionTemperatura(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/calculadoraConversionTemperatura.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    void applyConversion() {
        if (conversionInicial.getValue() != null && conversionFinal.getValue() != null) {
            double valor = Double.parseDouble(resultado.getText());

            if (conversionInicial.getValue().equals("Kilogramo") && conversionFinal.getValue().equals("Gramo")) {
                resultado.setText(String.valueOf(valor * 1000));
            } else if (conversionInicial.getValue().equals("Kilogramo") && conversionFinal.getValue().equals("Miligramo")) {
                resultado.setText(String.valueOf(valor * 1_000_000));
            } else if (conversionInicial.getValue().equals("Kilogramo") && conversionFinal.getValue().equals("Onza")) {
                resultado.setText(String.valueOf(valor * 35.27396));
            } else if (conversionInicial.getValue().equals("Kilogramo") && conversionFinal.getValue().equals("Libra")) {
                resultado.setText(String.valueOf(valor * 2.20462));
            } else if (conversionInicial.getValue().equals("Kilogramo") && conversionFinal.getValue().equals("Tonelada")) {
                resultado.setText(String.valueOf(valor / 1000));
            } else if (conversionInicial.getValue().equals("Gramo") && conversionFinal.getValue().equals("Kilogramo")) {
                resultado.setText(String.valueOf(valor / 1000));
            } else if (conversionInicial.getValue().equals("Gramo") && conversionFinal.getValue().equals("Miligramo")) {
                resultado.setText(String.valueOf(valor * 1000));
            } else if (conversionInicial.getValue().equals("Gramo") && conversionFinal.getValue().equals("Onza")) {
                resultado.setText(String.valueOf(valor / 28.34952));
            } else if (conversionInicial.getValue().equals("Gramo") && conversionFinal.getValue().equals("Libra")) {
                resultado.setText(String.valueOf(valor / 453.59237));
            } else if (conversionInicial.getValue().equals("Gramo") && conversionFinal.getValue().equals("Tonelada")) {
                resultado.setText(String.valueOf(valor / 1_000_000));
            } else if (conversionInicial.getValue().equals("Miligramo") && conversionFinal.getValue().equals("Kilogramo")) {
                resultado.setText(String.valueOf(valor / 1_000_000));
            } else if (conversionInicial.getValue().equals("Miligramo") && conversionFinal.getValue().equals("Gramo")) {
                resultado.setText(String.valueOf(valor / 1000));
            } else if (conversionInicial.getValue().equals("Miligramo") && conversionFinal.getValue().equals("Onza")) {
                resultado.setText(String.valueOf(valor / 28349.52));
            } else if (conversionInicial.getValue().equals("Miligramo") && conversionFinal.getValue().equals("Libra")) {
                resultado.setText(String.valueOf(valor / 453592.37));
            } else if (conversionInicial.getValue().equals("Miligramo") && conversionFinal.getValue().equals("Tonelada")) {
                resultado.setText(String.valueOf(valor / 1_000_000_000));
            } else if (conversionInicial.getValue().equals("Onza") && conversionFinal.getValue().equals("Kilogramo")) {
                resultado.setText(String.valueOf(valor / 35.27396));
            } else if (conversionInicial.getValue().equals("Onza") && conversionFinal.getValue().equals("Gramo")) {
                resultado.setText(String.valueOf(valor * 28.34952));
            } else if (conversionInicial.getValue().equals("Onza") && conversionFinal.getValue().equals("Miligramo")) {
                resultado.setText(String.valueOf(valor * 28349.52));
            } else if (conversionInicial.getValue().equals("Onza") && conversionFinal.getValue().equals("Libra")) {
                resultado.setText(String.valueOf(valor / 16));
            } else if (conversionInicial.getValue().equals("Onza") && conversionFinal.getValue().equals("Tonelada")) {
                resultado.setText(String.valueOf(valor / 35273.96));
            } else if (conversionInicial.getValue().equals("Libra") && conversionFinal.getValue().equals("Kilogramo")) {
                resultado.setText(String.valueOf(valor / 2.20462));
            } else if (conversionInicial.getValue().equals("Libra") && conversionFinal.getValue().equals("Gramo")) {
                resultado.setText(String.valueOf(valor * 453.59237));
            } else if (conversionInicial.getValue().equals("Libra") && conversionFinal.getValue().equals("Miligramo")) {
                resultado.setText(String.valueOf(valor * 453592.37));
            } else if (conversionInicial.getValue().equals("Libra") && conversionFinal.getValue().equals("Onza")) {
                resultado.setText(String.valueOf(valor * 16));
            } else if (conversionInicial.getValue().equals("Libra") && conversionFinal.getValue().equals("Tonelada")) {
                resultado.setText(String.valueOf(valor / 2204.62));
            } else if (conversionInicial.getValue().equals("Tonelada") && conversionFinal.getValue().equals("Kilogramo")) {
                resultado.setText(String.valueOf(valor * 1000));
            } else if (conversionInicial.getValue().equals("Tonelada") && conversionFinal.getValue().equals("Gramo")) {
                resultado.setText(String.valueOf(valor * 1_000_000));
            } else if (conversionInicial.getValue().equals("Tonelada") && conversionFinal.getValue().equals("Miligramo")) {
                resultado.setText(String.valueOf(valor * 1_000_000_000));
            } else if (conversionInicial.getValue().equals("Tonelada") && conversionFinal.getValue().equals("Onza")) {
                resultado.setText(String.valueOf(valor * 35273.96));
            } else if (conversionInicial.getValue().equals("Tonelada") && conversionFinal.getValue().equals("Libra")) {
                resultado.setText(String.valueOf(valor * 2204.62));
            }
        }
    }
}
