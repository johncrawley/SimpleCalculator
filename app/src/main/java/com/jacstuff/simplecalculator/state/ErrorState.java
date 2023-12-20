package com.jacstuff.simplecalculator.state;


public class ErrorState extends AbstractState implements CalcState {


    @Override
    public void clear() {
        calculatorActions.clearNumbersAndDisplayText();
        stateManager.setState(State.FIRST_NUMBER);
    }

}
