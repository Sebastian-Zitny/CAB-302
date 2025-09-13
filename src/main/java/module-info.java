module com.example.jth_mathgame.mathgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires java.desktop;


    opens com.example.jth_mathgame.mathgame to javafx.fxml;
    exports com.example.jth_mathgame.mathgame;
}