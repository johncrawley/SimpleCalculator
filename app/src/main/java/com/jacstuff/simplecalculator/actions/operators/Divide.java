package com.jacstuff.simplecalculator.actions.operators;

import com.jacstuff.simplecalculator.Calculator;
import com.jacstuff.simplecalculator.state.State;

public class Divide  extends AbstractOperatorAction {

    public int execute(int num1, int num2){
        if(num2 == 0){
            calculator.setState(State.ERROR);
            return 0;
        }
        return num1 / num2;
    }
}