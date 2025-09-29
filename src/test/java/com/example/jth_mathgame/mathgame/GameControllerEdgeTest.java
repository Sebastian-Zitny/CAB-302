package com.example.jth_mathgame.mathgame;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerEdgeTest {

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

    private GameController controllerAtMinScore() throws Exception {
        GameController c = new GameController();
        set(c, "levelLabel", new Label());
        set(c, "inputField", new TextField());
        set(c, "answerResponseLabel", new Label());
        set(c, "score", 1);
        set(c, "int1", 2);
        set(c, "int2", 2);
        return c;
    }

    @Test
    void wrongAnswer_atMinScore_doesNotGoBelowOne_andCountsIncorrect() throws Exception {
        GameController controller = controllerAtMinScore();
        ((TextField) get(controller, "inputField")).setText("99");

        controller.onSubmitButtonClick();

        assertEquals(1, (int) get(controller, "score"), "Score should not go below 1");
        assertEquals(1, (int) get(controller, "databaseIncorrect"));
        assertEquals("Incorrect!", ((Label) get(controller, "answerResponseLabel")).getText());
    }
}