package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;

public class OperatorState extends AbstractState implements CalcState {

    private OperandString secondOperandString;

    public OperatorState(OperandString secondOperandString){
        this.secondOperandString = secondOperandString;
    }

    @Override
    public void init(){

    }
    @Override
    public void setOperator(Operator operator){
        calculatorActions.setAndDisplayOperatorAction(operator);
    }


    @Override
    public void addDigit(int digit) {
        secondOperandString.init();
        calculator.setState(State.SECOND_NUMBER);
        calculator.addDigit(digit);
    }

    @Override
    public void changeSign() {
        calculator.setState(State.SECOND_NUMBER);
        secondOperandString.init();
        secondOperandString.addDecimal();
    }

    @Override
    public void addDecimal() {
        secondOperandString.init();
        calculator.setState(State.SECOND_NUMBER);
        calculator.addDecimal();
    }

    @Override
    public void clear() {
        calculatorActions.clearNumbersAndDisplayText();
        calculator.setState(State.FIRST_NUMBER);
    }

    @Override
    public void evaluate() {
        calculator.setState(State.ERROR);
    }


    @Override
    public void deleteDigit() {

    }
}
