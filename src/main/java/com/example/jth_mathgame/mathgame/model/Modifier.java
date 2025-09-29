package com.example.jth_mathgame.mathgame.model;

public interface Modifier {
    String label();                          // e.g., ×2
    int scaleFor(Operation op);              // usually >1 for harder

    default int applyToRangeMax(int baseMax, Operation op){
        return Math.max(1, baseMax * scaleFor(op));
    }
}