package com.example.jth_mathgame.mathgame.controller;

import com.example.jth_mathgame.mathgame.MainApplication;
import com.example.jth_mathgame.mathgame.ResultDAO;
import com.example.jth_mathgame.mathgame.Score;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class StatsController {
    @FXML
    Label headerText;
    @FXML
    private TableView<Score> statsView;
    @FXML
    TableColumn<Score, String> username;
    @FXML
    TableColumn<Score, Number> score;
    @FXML
    TableColumn<Score, Number> correct;
    @FXML
    TableColumn<Score, Number> incorrect;

    @FXML
    private TableView<Score> endlessStatsView;
    @FXML
    TableColumn<Score, String> endlessUsername;
    @FXML
    TableColumn<Score, Number> endlessScore;
    @FXML
    TableColumn<Score, Number> endlessCorrect;
    @FXML
    TableColumn<Score, Number> endlessIncorrect;

    @FXML
    Button backButton;

    private final ResultDAO RESULTDAO = ResultDAO.getInstance();

    public void initialize(){
        headerText.setText("Stats");
        headerText.setFont(new Font("Impact", 29));
        username.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
        score.setCellValueFactory(cellData -> cellData.getValue().getScoreProperty());
        correct.setCellValueFactory(cellData -> cellData.getValue().getCorrectAnswersProperty());
        incorrect.setCellValueFactory(cellData -> cellData.getValue().getIncorrectAnswersProperty());

        List<Score> scores = RESULTDAO.getScores();
        ObservableList<Score> scoresTable = FXCollections.observableArrayList(scores);
        statsView.setItems(scoresTable);

        endlessUsername.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
        endlessScore.setCellValueFactory(cellData -> cellData.getValue().getScoreProperty());
        endlessCorrect.setCellValueFactory(cellData -> cellData.getValue().getCorrectAnswersProperty());
        endlessIncorrect.setCellValueFactory(cellData -> cellData.getValue().getIncorrectAnswersProperty());

        List<Score> endlessScores = RESULTDAO.getEndlessScores();
        ObservableList<Score> endlessScoresTable = FXCollections.observableArrayList(endlessScores);
        endlessStatsView.setItems(endlessScoresTable);
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }
}
