package com.example.jth_mathgame.mathgame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.ButtonType;

import java.io.IOException;
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
    private Timeline timeline;
    @FXML
    private int int1;
    @FXML
    private int int2;
    @FXML
    private String stringInt1;
    @FXML
    private String stringInt2;
    @FXML
    private int question;
    @FXML
    private Random randomGenerator;
    @FXML
    public int score = 1;
    @FXML
    public String[] addModifiers = {};




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
        startTimer();
    }
    // TODO give error message and disable gameplay rather than force back to menu
    private void startTimer() {
        timeline = new Timeline(
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

        int1 = randomGenerator.nextInt(11);
        stringInt1 = String.valueOf(int1);
        int2 = randomGenerator.nextInt(11);
        stringInt2 = String.valueOf(int2);
        if (addModifiers.length == 0) {
            questionLabel.setText(stringInt1 + " + " + stringInt2);
        }
        else {
            questionLabel.setText(stringInt1 + " + " + stringInt2 + addModifiers[0]);
        }



    }


    // Go back to menu when quit button clicked
    @FXML
    protected void onQuitButtonClick() throws IOException {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    private int[][] generateModifier() {
        int[][] modifiers = new int[3][2]; // 3 modifiers, each with {symbol, value}

        for (int i = 0; i < 3; i++) {
            int symbol = randomGenerator.nextInt(3);     // 0=+, 1=-, 2=×
            int addedInt = randomGenerator.nextInt(1, 5); // 1–4
            modifiers[i] = new int[]{symbol, addedInt};
        }

        return modifiers;
    }

    private String numToSymbol(int symbol) {
        return switch (symbol) {
            case 0 -> "+";
            case 1 -> "-";
            case 2 -> "×";
            default -> "?"; // fallback if out of range
        };
    }


    //TODO: Compare user input against answer when clicked
    @FXML
    protected void onSubmitButtonClick() {

        // generate modifiers for future, (has to be done here)
        int[][] modifiers = generateModifier();

        String symbol1 = numToSymbol(modifiers[0][0]);
        String modNum1 = String.valueOf(modifiers[0][1]);
        String symbol2 = numToSymbol(modifiers[1][0]);
        String modNum2 = String.valueOf(modifiers[1][1]);
        String symbol3 = numToSymbol(modifiers[2][0]);
        String modNum3 = String.valueOf(modifiers[2][1]);


        int equalsInt = Integer.parseInt(inputField.getText());

        if (addModifiers.length == 0) {
            question = (int1 + int2);
        }
        else {
            question = (int1 + int2) + Integer.parseInt(addModifiers[0]);
        }

        if (equalsInt == (question)) {
            answerResponseLabel.setText("Correct!");
            answerResponseLabel.setTextFill(Color.GREEN);

            score ++;

            // Failed login

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifier");
            alert.setHeaderText("Choose a modifier");
            alert.setContentText("whichever chosen will be added to the next equation");


            ButtonType modifierButton1= new ButtonType(symbol1 + " " + modNum1);
            ButtonType modifierButton2 = new ButtonType(symbol2 + " " + modNum2);
            ButtonType modifierButton3 = new ButtonType(symbol3 + " " + modNum3);
            alert.getButtonTypes().setAll(modifierButton1, modifierButton2, modifierButton3, ButtonType.CANCEL);



            alert.showAndWait();

            mockQuestion();
            levelLabel.setText("Endless Level:" + score);


            // levelLabel
        }
        else {

            if (score > 1) {
                answerResponseLabel.setText("Incorrect!");
                answerResponseLabel.setTextFill(Color.RED);
                score--;
                mockQuestion();
                levelLabel.setText("Endless Level:" + score);
            }
            else {
                answerResponseLabel.setText("Incorrect!");
                answerResponseLabel.setTextFill(Color.RED);
                mockQuestion();
                levelLabel.setText("Endless Level:" + score);
            }



        }
        //answerCorrect(Objects.equals(inputField.getText(), "19")); //TODO: Delete when no longer needed

    }
}
