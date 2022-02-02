package com.example.counterword;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {

    public static final Set<Character> FORBIDDER_CHARS;

    static {
        FORBIDDER_CHARS = new HashSet<>();

        FORBIDDER_CHARS.add('.');
        FORBIDDER_CHARS.add('\n');
        FORBIDDER_CHARS.add(',');
        FORBIDDER_CHARS.add(' ');
        FORBIDDER_CHARS.add(':');
    }

    @FXML
    private Label welcomeText;

    @FXML
    private BarChart chart;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void OpenFile(MouseEvent actionEvent) throws FileNotFoundException {
        Window window = ((Node)actionEvent.getTarget()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File choosedFile = fileChooser.showOpenDialog(window);
        System.out.println(choosedFile);
        fileFormatter(choosedFile);

        String inputString = fileFormatter(choosedFile);
        Map<Character, Integer> counterMap = new HashMap<>();
        for (int i = 0; i < inputString.length(); i++) {
            char character = inputString.charAt(i);
            if(FORBIDDER_CHARS.contains(character))
                continue;
            if(counterMap.containsKey(character)){
                int value = counterMap.get(character);
                counterMap.put(character, ++value);
            } else{
                counterMap.put(character, 1);
            }
        }

        XYChart.Series series=new XYChart.Series<>();
        for (Map.Entry<Character,Integer> entry : counterMap.entrySet())
            series.getData().add(new XYChart.Data<>("" + entry.getKey(), entry.getValue()));
        chart.getData().add(series);
    }


    public String fileFormatter(File choosedFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(choosedFile));
        //String[] file = new String[Integer.parseInt(scanner.nextLine())];
        StringBuilder stringBuilder = new StringBuilder();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            stringBuilder.append(line + "\n");
    }
        return stringBuilder.toString();
    }
}