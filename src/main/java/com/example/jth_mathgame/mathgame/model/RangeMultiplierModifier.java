package com.example.jth_mathgame.mathgame.model;


public class RangeMultiplierModifier implements Modifier {
    private final int multiply;
    public RangeMultiplierModifier(int multiply){ this.multiply = multiply; }
    @Override public String label(){ return "×" + multiply; }
    @Override public int scaleFor(Operation op){ return multiply; }
}