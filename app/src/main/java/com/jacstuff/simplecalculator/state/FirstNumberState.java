package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;

public class FirstNumberState extends AbstractState implements CalcState {


    private OperandString firstOperandString;

    public FirstNumberState(OperandString firstOperandString){
        this.firstOperandString = firstOperandString;
    }

    @Override
    public void init(){

    }
    @Override
    public void setOperator(Operator operator){
        calculator.setState(State.OPERATOR);
        calculator.setOperator(operator);
    }

    @Override
    public void changeSign() {
        firstOperandString.negate();
    }

    @Override
    public void addDecimal() {
        firstOperandString.addDecimal();
    }

    @Override
    public void addDigit(int digit) {
        firstOperandString.addDigit(digit);
    }

    @Override
    public void clear() {
        calculatorActions.clearNumbersAndDisplayText();
    }

    @Override
    public void evaluate() {
    }


    @Override
    public void deleteDigit() {
        firstOperandString.deleteDigit();
    }
}
