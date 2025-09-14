package com.example.jth_mathgame.mathgame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class StatsController {
    @FXML
    private TableView<User> statsView;
    @FXML
    TableColumn<User, String> username;
    @FXML
    TableColumn<User, Number> score;

    private static final IUserDAO dao = new UserDAO();

    public void initialize(){
        username.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
        score.setCellValueFactory(cellData -> cellData.getValue().getScoreProperty());

        List<User> users = dao.getAllUsers();
        ObservableList<User> usersTable = FXCollections.observableArrayList(users);
        statsView.setItems(usersTable);
    }
}
