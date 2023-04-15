package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.calculator.display.UpdatableDisplay;

public class ErrorState extends AbstractState implements CalcState {

    public ErrorState(UpdatableDisplay updatableDisplay){
        super(updatableDisplay);
    }

    @Override
    public void clear() {
        calculatorActions.clearNumbersAndDisplayText();
        calculator.setState(State.FIRST_NUMBER);
    }

}
