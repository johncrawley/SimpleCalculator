package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.actions.operators.Operator;

public class ResultState extends AbstractState implements CalcState {


    @Override
    public void init(){

    }
    @Override
    public void setOperator(Operator operator) {
        calculatorActions.assignResultToFirstNumber();
        calculatorActions.setAndDisplayOperatorAction(operator);
        if(operator.hasSingleInput()){
            calculator.setState(State.SECOND_NUMBER);
            calculator.evaluate();
            return;
        }
        calculator.setState(State.OPERATOR);
    }

    @Override
    public void addDigit(int digit) {

        calculatorActions.clearNumbers();
        calculatorActions.addDigitToFirstNumber(digit);
        calculator.setState(State.FIRST_NUMBER);

    }

    @Override
    public void changeSign() {
        calculatorActions.changeSignOfResult();
    }

    @Override
    public void addDecimal() {

    }

    @Override
    public void clear() {

        calculatorActions.clearNumbers();
    }

    @Override
    public void evaluate() {

    }
}
