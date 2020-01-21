package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.actions.operators.Operator;

public class OperatorState extends AbstractState implements CalcState {


    @Override
    public void init(){

    }
    @Override
    public void setOperator(Operator operator){
        calculatorActions.setAndDisplayOperatorAction(operator);
    }


    @Override
    public void addDigit(int digit) {
        calculatorActions.clearSecondNumber();
        calculatorActions.addDigitToSecondNumber(digit);
        calculatorActions.appendSecondNumberToDisplay();
        calculator.setState(State.SECOND_NUMBER);
    }

    @Override
    public void changeSign() {

    }

    @Override
    public void addDecimal() {

    }

    @Override
    public void clear() {
        calculatorActions.clearNumbers();
        calculator.setState(State.FIRST_NUMBER);
    }

    @Override
    public void evaluate() {
        calculator.setState(State.ERROR);
    }
}
