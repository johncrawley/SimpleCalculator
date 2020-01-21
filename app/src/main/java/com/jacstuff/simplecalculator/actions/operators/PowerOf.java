package com.jacstuff.simplecalculator.actions.operators;

import com.jacstuff.simplecalculator.Calculator;

public class PowerOf extends AbstractOperatorAction {
    public int execute(int num1, int num2){
        return (int)Math.pow(num1, num2);
    }
}
