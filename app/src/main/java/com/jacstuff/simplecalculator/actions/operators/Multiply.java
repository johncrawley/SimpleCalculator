package com.jacstuff.simplecalculator.actions.operators;

import com.jacstuff.simplecalculator.Calculator;

public class Multiply extends AbstractOperatorAction {

    public int execute(int num1, int num2){
        return num1 * num2;
    }
}
