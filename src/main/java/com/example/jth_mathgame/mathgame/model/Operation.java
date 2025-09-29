package com.example.jth_mathgame.mathgame.model;

public enum Operation {
    ADD("+") { public int apply(int a, int b){ return a + b; } },
    SUB("-") { public int apply(int a, int b){ return a - b; } },
    MUL("×") { public int apply(int a, int b){ return a * b; } },
    DIV("÷") { public int apply(int a, int b){ return a / b; } };

    private final String symbol;
    Operation(String s){ this.symbol = s; }
    public String symbol(){ return symbol; }
    public abstract int apply(int a, int b);
}