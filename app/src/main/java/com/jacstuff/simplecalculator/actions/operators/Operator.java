package com.jacstuff.simplecalculator.actions.operators;

public interface Operator {

    int execute(int number1, int number2);
    String getSymbol();
    void onLoad();
    boolean hasSingleInput();
}
