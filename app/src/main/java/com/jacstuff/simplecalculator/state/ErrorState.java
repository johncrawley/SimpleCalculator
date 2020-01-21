package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.actions.operators.Operator;

public class ErrorState extends AbstractState implements CalcState {


    @Override
    public void init(){
        calculatorActions.displayError();
    }

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
    public void addDigit(int digit) {

    }

    @Override
    public void clear() {
        calculatorActions.clearNumbers();
        calculator.setState(State.FIRST_NUMBER);
    }

    @Override
    public void evaluate() {

    }
}
