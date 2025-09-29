package com.example.jth_mathgame.mathgame.model;

public class Equation {
    private final int a, b;
    private final Operation op;

    public Equation(int a, int b, Operation op){
        this.a = a; this.b = b; this.op = op;
    }
    public int answer(){ return op.apply(a,b); }
    public boolean check(int user){ return user == answer(); }
    public String prompt(){ return a + " " + op.symbol() + " " + b + " = ?"; }
}