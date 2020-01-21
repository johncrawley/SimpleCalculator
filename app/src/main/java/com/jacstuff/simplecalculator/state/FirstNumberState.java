package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.actions.operators.Operator;

public class FirstNumberState extends AbstractState implements CalcState {


    @Override
    public void init(){

    }
    @Override
    public void setOperator(Operator operator){

        calculatorActions.setAndDisplayOperatorAction(operator);
        if(operator.hasSingleInput()){
            calculator.setState(State.SECOND_NUMBER);
            calculator.evaluate();
            return;
        }
        calculator.setState(State.OPERATOR);
    }

    @Override
    public void changeSign() {
        calculatorActions.changeSignOfFirstNumber();
    }

    @Override
    public void addDecimal() {

    }

    @Override
    public void addDigit(int digit) {
        calculatorActions.addDigitToFirstNumber(digit);
    }

    @Override
    public void clear() {
        calculatorActions.clearNumbers();
    }

    @Override
    public void evaluate() {
    }
}
