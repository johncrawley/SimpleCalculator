package com.jacstuff.simplecalculator.state;

import android.util.Log;

import com.jacstuff.simplecalculator.actions.operators.Operator;

public class ErrorState extends AbstractState implements CalcState {

    @Override
    public void init(){}

    @Override
    public void changeSign() {

    }

    @Override
    public void addDecimal() {

    }

    @Override
    public void setOperator(Operator operator){


    }


    @Override
    public void addDigit(int digit){
    }

    @Override
    public void clear() {
        calculatorActions.clearNumbersAndDisplayText();
        calculator.setState(State.FIRST_NUMBER);
    }

    @Override
    public void evaluate() {

    }

    @Override
    public void deleteDigit() {

    }

}
