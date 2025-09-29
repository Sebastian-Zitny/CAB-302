package com.example.jth_mathgame.mathgame;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerLogicTest {

    private static void set(Object target, String field, Object value) throws Exception {
        Field f = target.getClass().getDeclaredField(field);
        f.setAccessible(true);
        f.set(target, value);
    }
    @SuppressWarnings("unchecked")
    private static <T> T get(Object target, String field) throws Exception {
        Field f = target.getClass().getDeclaredField(field);
        f.setAccessible(true);
        return (T) f.get(target);
    }
    @BeforeAll
    static void initJavaFx() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(2, TimeUnit.SECONDS);
    }

    private GameController newControllerWithUI() throws Exception {
        GameController c = new GameController();
        set(c, "quitButton", new Button());
        set(c, "levelLabel", new Label());
        set(c, "timeLabel", new Label());
        set(c, "questionLabel", new Label());
        set(c, "inputField", new TextField());
        set(c, "answerResponseLabel", new Label());
        set(c, "submitButton", new Button());
        set(c, "score", 1);
        set(c, "int1", 2);
        set(c, "int2", 3);
        return c;
    }

    @Test
    void correctAnswer_incrementsScoreAndDatabaseCorrect() throws Exception {
        GameController controller = newControllerWithUI();
        get(TextField.class.cast(get(controller, "inputField")), "text");
        ((TextField) get(controller, "inputField")).setText("5");

        controller.onSubmitButtonClick();

        assertEquals(1, (int) get(controller, "databaseCorrect"));
        assertEquals(0, (int) get(controller, "databaseIncorrect"));
        assertEquals("Correct!", ((Label) get(controller, "answerResponseLabel")).getText());
        assertEquals("Endless Level:2", ((Label) get(controller, "levelLabel")).getText());
    }

    @Test
    void incorrectAnswer_incrementsDatabaseIncorrect() throws Exception {
        GameController controller = newControllerWithUI();
        ((TextField) get(controller, "inputField")).setText("99");

        controller.onSubmitButtonClick();

        assertEquals(0, (int) get(controller, "databaseCorrect"));
        assertEquals(1, (int) get(controller, "databaseIncorrect"));
        assertEquals("Incorrect!", ((Label) get(controller, "answerResponseLabel")).getText());
    }
}