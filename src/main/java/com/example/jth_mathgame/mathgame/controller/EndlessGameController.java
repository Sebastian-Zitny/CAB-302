package com.example.jth_mathgame.mathgame.controller;

import com.example.jth_mathgame.mathgame.model.EndlessGame;
import com.example.jth_mathgame.mathgame.MainApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;


import java.io.IOException;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class EndlessGameController {
    @FXML
    private Button quitButton;
    @FXML
    private Label levelLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private int Time = 0;
    @FXML
    private Label questionLabel;
    @FXML
    private TextField inputField;
    @FXML
    private Label answerResponseLabel;
    @FXML
    private Button submitButton;
    @FXML
    private VBox submitVBox;
    @FXML
    private String stringInt1;
    @FXML
    private String stringInt2;
    @FXML
    private Random randomGenerator;
    @FXML
    public int score = 1;
    @FXML
    ArrayList<Integer> FinalEquation = new ArrayList<>(Arrays.asList(0, 0));
    private int correct = 0;
    private int incorrect = 0;




    @FXML
    public void initialize() {
        // TODO: Maybe convert FXML padding to code
        // Defines some fonts

        Font smallFont = new Font("Arial", 20);
        Font smallFontBold = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20);

        levelLabel.setText("Endless Level:" + score);

        randomGenerator = new Random();

        timeLabel.setText(String.valueOf(Time));
        timeLabel.setStyle("-fx-font-size: 40px;");

        quitButton.setFont(smallFont);

        levelLabel.setFont(new Font("Arial", 26));

        timeLabel.setFont(smallFont);

        questionLabel.setFont(new Font("Arial", 58));

        inputField.setFont(new Font("Arial", 29));

        answerResponseLabel.setFont(smallFontBold);
        answerResponseLabel.setPadding(new Insets(20));

        quitButton.setFont(smallFont);
        submitButton.setFont(smallFont);
        submitVBox.setAlignment(Pos.CENTER);
        mockQuestion();
        questionLabel.setText(stringInt1 + " + " + stringInt2);
        startTimer();
    }
    // TODO give error message and disable gameplay rather than force back to menu
    private void startTimer() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    Time++;
                    if (Time >= 0) {
                        timeLabel.setText(String.valueOf(Time));
                    }
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    // TODO: Delete when no longer needed
    // Creates a hardcoded mock question
    private void mockQuestion() {

        int int1 = randomGenerator.nextInt(11);
        stringInt1 = String.valueOf(int1);
        int int2 = randomGenerator.nextInt(11);
        stringInt2 = String.valueOf(int2);



        FinalEquation.set(0, int1);
        FinalEquation.set(1, int2);





    }


    // Go back to menu when quit button clicked
    @FXML
    protected void onQuitButtonClick() throws IOException {
        EndlessGame.inputEndlessResult(correct,incorrect);
        Stage stage = (Stage) quitButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    private int[][] generateModifier() {
        int[][] modifiers = new int[3][2];

        for (int i = 0; i < 3; i++) {
            int symbol = randomGenerator.nextInt(3);
            int addedInt = randomGenerator.nextInt(1, 5);
            modifiers[i] = new int[]{symbol, addedInt};
        }

        return modifiers;
    }

    private String numToSymbol(int symbol) {
        return switch (symbol) {
            case 0 -> "+";
            case 1 -> "-";
            case 2 -> "×";
            default -> "?"; // shouldn't happen just in case
        };
    }

    // change equation array to readable string
    public String stringEquation()
    {

        StringBuilder sb = new StringBuilder();
        sb.append(FinalEquation.get(0)).append(" + ").append(FinalEquation.get(1));

        for (int i = 2; i < FinalEquation.size(); i += 2) {
            int symbol = FinalEquation.get(i);
            int nextNum = FinalEquation.get(i + 1);
            sb.append(" ").append(numToSymbol(symbol)).append(" ").append(nextNum);
        }

        return sb.toString();

    }

    // change equation array to an answer which can be compared with the users input
    public int equationAnswer()
    {


        int answer = FinalEquation.get(0) + FinalEquation.get(1);


        for (int i = 2; i < FinalEquation.size(); i += 2) {
            int symbol = FinalEquation.get(i);
            int nextNum = FinalEquation.get(i + 1);

            switch (symbol) {
                case 0 -> answer += nextNum;
                case 1 -> answer -= nextNum;
                case 2 -> answer *= nextNum;
                default -> throw new IllegalStateException("Invalid operator code: " + symbol);
            }
        }

        return answer;
    }


    //TODO: Compare user input against answer when clicked
    @FXML
    protected void onSubmitButtonClick() {

        // generate modifiers for future
        int[][] modifiers = generateModifier();

        String symbol1 = numToSymbol(modifiers[0][0]);
        String modNum1 = String.valueOf(modifiers[0][1]);
        String symbol2 = numToSymbol(modifiers[1][0]);
        String modNum2 = String.valueOf(modifiers[1][1]);
        String symbol3 = numToSymbol(modifiers[2][0]);
        String modNum3 = String.valueOf(modifiers[2][1]);

        int equalsInt = Integer.parseInt(inputField.getText());

        int questionAns = equationAnswer();

        if (equalsInt == (questionAns)) {
            answerResponseLabel.setText("Correct!");
            answerResponseLabel.setTextFill(Color.GREEN);
            score ++;
            correct ++;



            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifier");
            alert.setHeaderText("Choose a modifier");
            alert.setContentText("whichever chosen will be added to the next equation");


            ButtonType modifierButton1= new ButtonType(symbol1 + " " + modNum1);
            ButtonType modifierButton2 = new ButtonType(symbol2 + " " + modNum2);
            ButtonType modifierButton3 = new ButtonType(symbol3 + " " + modNum3);
            alert.getButtonTypes().setAll(modifierButton1, modifierButton2, modifierButton3, ButtonType.CANCEL);


            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent()) {
                ButtonType clicked = result.get();

                // add modifiers to equation
                if (clicked == modifierButton1) {
                    FinalEquation.add(modifiers[0][0]);
                    FinalEquation.add(Integer.valueOf(modNum1));


                } else if (clicked == modifierButton2) {
                    FinalEquation.add(modifiers[1][0]);
                    FinalEquation.add(Integer.valueOf(modNum2));

                } else if (clicked == modifierButton3) {
                    FinalEquation.add(modifiers[2][0]);
                    FinalEquation.add(Integer.valueOf(modNum3));

                } else if (clicked == ButtonType.CANCEL) {
                    System.out.println("shouldn't be doing this");
                }
            }



            mockQuestion();
            String textFinalEquation = stringEquation();
            questionLabel.setText(textFinalEquation);
            levelLabel.setText("Endless Level:" + score);


            // levelLabel
        }
        else {

            if (score > 1) {
                answerResponseLabel.setText("Incorrect!");
                answerResponseLabel.setTextFill(Color.RED);
                score--;
                incorrect++;
                mockQuestion();
                String textFinalEquation = stringEquation();
                questionLabel.setText(textFinalEquation);
                levelLabel.setText("Endless Level:" + score);
            }
            else {
                answerResponseLabel.setText("Incorrect!");
                answerResponseLabel.setTextFill(Color.RED);
                incorrect++;
                mockQuestion();
                String textFinalEquation = stringEquation();
                questionLabel.setText(textFinalEquation);
                levelLabel.setText("Endless Level:" + score);
            }



        }

    }
}
