package com.example.jth_mathgame.mathgame.model;

import java.util.*;

public class EquationGenerator {
    private final Random rng = new Random();
    private final List<Operation> allowed;
    private int baseMax = 9;
    private final List<Modifier> modifiers = new ArrayList<>();

    public EquationGenerator(List<Operation> allowedOps){
        this.allowed = new ArrayList<>(allowedOps);
    }

    public void setBaseMax(int baseMax){ this.baseMax = Math.max(3, baseMax); }
    public void clearModifiers(){ modifiers.clear(); }
    public void addModifier(Modifier m){ modifiers.add(m); }

    public Equation next(){
        Operation op = allowed.get(rng.nextInt(allowed.size()));
        int max = baseMax;
        for (Modifier m : modifiers) max = m.applyToRangeMax(max, op);
        max = Math.min(max, 999);

        int a, b;
        switch (op){
            case ADD -> { a = 1 + rng.nextInt(max); b = 1 + rng.nextInt(max); }
            case SUB -> {
                a = 1 + rng.nextInt(max); b = 1 + rng.nextInt(max);
                if (a < b){ int t=a; a=b; b=t; }
            }
            case MUL -> {
                int cap = Math.max(3, max/2);
                a = 1 + rng.nextInt(cap); b = 1 + rng.nextInt(cap);
            }
            case DIV -> {
                int bCap = Math.max(3, max/3);
                b = 1 + rng.nextInt(bCap);
                int q = 1 + rng.nextInt(Math.max(3, max/3));
                a = b * q; // ensures integer result
            }
            default -> throw new IllegalStateException("Unexpected op");
        }
        return new Equation(a,b,op);
    }
}