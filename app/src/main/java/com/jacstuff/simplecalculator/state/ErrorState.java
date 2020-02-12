package com.jacstuff.simplecalculator.state;

import android.util.Log;

import com.jacstuff.simplecalculator.actions.operators.Operator;

public class ErrorState extends AbstractState implements CalcState {



    @Override
    public void clear() {
        calculatorActions.clearNumbersAndDisplayText();
        calculator.setState(State.FIRST_NUMBER);
    }

}
