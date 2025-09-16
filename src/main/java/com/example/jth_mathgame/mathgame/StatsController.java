package com.example.jth_mathgame.mathgame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

public class StatsController {
    @FXML
    private TableView<User> statsView;
    @FXML
    TableColumn<User, String> username;
    @FXML
    TableColumn<User, Number> score;
    @FXML
    Button backButton;

    private static final IUserDAO dao = new UserDAO();

    public void initialize(){
        username.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
        score.setCellValueFactory(cellData -> cellData.getValue().getScoreProperty());

        List<User> users = dao.getAllUsers();
        ObservableList<User> usersTable = FXCollections.observableArrayList(users);
        statsView.setItems(usersTable);
    }
    @FXML
    protected void onBackButtonClick() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        soundPlayer.playSound("src/sounds/button_3.wav");
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }
}
