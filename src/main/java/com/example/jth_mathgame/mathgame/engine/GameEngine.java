package com.example.jth_mathgame.mathgame.engine;

import com.example.jth_mathgame.mathgame.model.Equation;
import com.example.jth_mathgame.mathgame.model.EquationGenerator;
import com.example.jth_mathgame.mathgame.model.Operation;
import com.example.jth_mathgame.mathgame.model.RangeMultiplierModifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameEngine {
    private final EquationGenerator generator;
    private int level = 1;
    private int score = 0;
    private int correctCount = 0;
    private int secondsLeft = 0;
    private final List<String> earnedModifiers = new ArrayList<>();

    public GameEngine(){
        generator = new EquationGenerator(Arrays.asList(
                Operation.ADD, Operation.SUB, Operation.MUL, Operation.DIV
        ));
        generator.setBaseMax(9);
    }

    public void startRound(Duration limit){
        level = 1;
        score = 0;
        correctCount = 0;
        secondsLeft = (int) limit.getSeconds();
        earnedModifiers.clear();
        generator.clearModifiers();
    }

    public Equation nextEquation(){ return generator.next(); }

    public boolean submit(Equation eq, int userAnswer){
        boolean ok = eq.check(userAnswer);
        if (ok){
            score += 10;
            level++;
            correctCount++;
            if (correctCount % 3 == 0){
                int tier = Math.min(5, 2 + (correctCount/3) - 1); // ×2..×5
                RangeMultiplierModifier mod = new RangeMultiplierModifier(tier);
                generator.addModifier(mod);
                earnedModifiers.add(mod.label());
            }
        } else {
            score = Math.max(0, score - 2);
        }
        return ok;
    }

    public void tickOneSecond(){ if (secondsLeft > 0) secondsLeft--; }
    public boolean timeUp(){ return secondsLeft <= 0; }
    public int getSecondsLeft(){ return secondsLeft; }
    public int getLevel(){ return level; }
    public int getScore(){ return score; }
    public List<String> getEarnedModifiers(){ return earnedModifiers; }
}