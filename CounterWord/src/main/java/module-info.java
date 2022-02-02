module com.example.counterword {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.counterword to javafx.fxml;
    exports com.example.counterword;
}