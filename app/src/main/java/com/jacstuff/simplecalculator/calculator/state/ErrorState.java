package com.jacstuff.simplecalculator.calculator.state;


public class ErrorState extends AbstractState implements CalcState {


    @Override
    public void clear() {
        calculatorActions.clearNumbersAndDisplayText();
        stateManager.setState(State.FIRST_NUMBER);
    }


    @Override
    public void recallNumberFromMemory(){
        stateManager.setState(State.FIRST_NUMBER);
        stateManager.recallNumberFromMemory();
    }

    @Override
    public void addDigit(int digit) {
        calculatorActions.clearNumbersAndDisplayText();
        stateManager.setState(State.FIRST_NUMBER);
        stateManager.addDigit(digit);
    }
}
