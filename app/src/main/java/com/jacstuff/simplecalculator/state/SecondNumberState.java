package com.jacstuff.simplecalculator.state;

import android.util.Log;

import com.jacstuff.simplecalculator.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;

public class SecondNumberState extends AbstractState implements CalcState {

    private OperandString firstOperandString;
    private OperandString secondOperandString;

    public SecondNumberState(OperandString firstOperandString, OperandString secondOperandString){
        this.firstOperandString = firstOperandString;
        this.secondOperandString = secondOperandString;
    }

    @Override
    public void init(){

    }

    @Override
    public void setOperator(Operator operator) {
        calculator.evaluate();
        calculatorActions.copyResultToFirstNumber();
        calculator.setState(State.FIRST_NUMBER);
        calculator.setOperator(operator);
    }


    @Override
    public void changeSign() {
        secondOperandString.negate();
    }

    @Override
    public void addDecimal() {
        secondOperandString.addDecimal();
    }

    @Override
    public void deleteDigit() {
        secondOperandString.deleteDigit();
    }

    @Override
    public void addDigit(int digit) {
        secondOperandString.addDigit(digit);
    }

    @Override
    public void clear() {
        calculatorActions.clearNumbersAndDisplayText();
        calculator.setState(State.FIRST_NUMBER);
    }

    @Override
    public void evaluate() {
        calculatorActions.evaluateAndDisplay();
        calculator.setState(State.RESULT);
    }
}
