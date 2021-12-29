import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


//TODO

public class Main extends Application {

    private Stage window;
    private Scene scene1, scene2;
    private Button buttonCalculate, buttonAddCurrency, buttonReturn, buttonCancel;
    private ListView<String> listView1;
    private ListView<String> listView2;
    private int value;
    private ArrayList<String> valutaStringList = new ArrayList<String>();

    private Valuta[] valutaer1 = {
            new Valuta("Euro", 8.10, 1), new Valuta("US Dollar", 6.23, 1),
            new Valuta("Britiske pund", 12.27, 1), new Valuta("Svenske kroner", 88.96, 100),
            new Valuta("Danske kroner", 108.75, 100), new Valuta("Yen", 5.14, 100),
            new Valuta("Islandske kroner", 9.16, 100), new Valuta("Norske kroner", 100, 100)
    };

    private ArrayList<Valuta> valutaer = new ArrayList<Valuta>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        valutaer.addAll(Arrays.asList(valutaer1));
        //lager valutaStringList
        for (int i = 0; i < valutaer.size(); i++) {
            valutaStringList.add(valutaer.get(i).getNavn());
        }

        window = primaryStage;
        window.setTitle("currency converter");

        //textfield for value
        TextField valueInput = new TextField();

        //listview1
        listView1 = new ListView<>();
        listView1.getItems().addAll(valutaStringList);
        listView1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //listview2
        listView2 = new ListView<>();
        listView2.getItems().addAll(valutaStringList);
        listView2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //select button
        buttonCalculate = new Button("calculate");
        buttonCalculate.setOnAction(e -> buttonSelectClicked(valueInput.getText()));

        //add currency button
        buttonAddCurrency = new Button("add new currency");
        buttonAddCurrency.setOnAction(e -> window.setScene(scene2));

        //layout1
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(valueInput, listView1, listView2, buttonCalculate, buttonAddCurrency);
        scene1 = new Scene(layout, 400, 700);

        //children layout2
        Label labelInputNavn = new Label("Valutanavn");
        TextField inputNavn = new TextField();
        Label labelInputKurs = new Label("Valutakurs");
        TextField inputKurs = new TextField();
        Label labelinputEnhet = new Label("Valutaenhet");
        TextField inputEnhet = new TextField();
        buttonReturn = new Button("confirm");
        buttonReturn.setOnAction(e -> addCurrency(inputNavn.getText(), inputKurs.getText(), inputEnhet.getText()));
        buttonCancel = new Button("cancel");
        buttonCancel.setOnAction(e -> window.setScene(scene1));

        //layout2
        VBox layout2 = new VBox(10);
        layout2.getChildren().addAll(labelInputNavn, inputNavn, labelInputKurs, inputKurs, labelinputEnhet, inputEnhet, buttonReturn, buttonCancel);
        scene2 = new Scene(layout2, 600, 300);

        window.setScene(scene1);
        window.show();
    }

    //konverter
    private void buttonSelectClicked(String valueInput) {
        if (isInt(valueInput)) {
            int valutaI1 = listView1.getSelectionModel().getSelectedIndex();
            Valuta valuta1 = valutaer.get(valutaI1);
            System.out.println(valuta1.getNavn());

            int valutaI2 = listView2.getSelectionModel().getSelectedIndex();
            Valuta valuta2 = valutaer.get(valutaI2);
            System.out.println(valuta2.getNavn());

            System.out.println(valuta1.konverter(value, valuta2));
        }
    }

    private boolean isInt(String valueInput){
        System.out.println(valueInput);
        try{
            int value = Integer.parseInt(valueInput);
            System.out.println("value: " + value);
            this.value = value;
            return true;
        }catch(NumberFormatException e){
            System.out.println("Error: " + valueInput + " is not a number");
            return false;
        }
    }

    private void addCurrency(String navn, String kurs, String enhhet) {
        valutaer.add(new Valuta(navn, Double.parseDouble(kurs), Integer.parseInt(enhhet)));
        listView1.getItems().add(navn);
        listView2.getItems().add(navn);
        window.setScene(scene1);
        return;
    }
}