package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;

public class ResultState extends AbstractState implements CalcState {


    private OperandString firstOperandString;
    private OperandString secondOperandString;

    public ResultState(OperandString firstOperandString, OperandString secondOperandString){
        this.firstOperandString = firstOperandString;
        this.secondOperandString = secondOperandString;
    }

    @Override
    public void init(){

    }
    @Override
    public void setOperator(Operator operator) {
        calculatorActions.copyResultToFirstNumber();
        secondOperandString.init();
        calculator.setState(State.FIRST_NUMBER);
        calculator.setOperator(operator);
    }

    @Override
    public void addDigit(int digit) {
        calculatorActions.clearNumbersAndDisplayText();
        calculator.setState(State.FIRST_NUMBER);
        calculator.addDigit(digit);
    }

    @Override
    public void changeSign() {
        calculatorActions.copyResultToFirstNumber();
        calculator.setState(State.FIRST_NUMBER);
        calculator.changeSign();
    }

    @Override
    public void addDecimal() {
        calculatorActions.copyResultToFirstNumber();
        secondOperandString.init();
        calculator.setState(State.FIRST_NUMBER);
        firstOperandString.addDecimal();
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
        secondOperandString.init();
        calculatorActions.copyResultToFirstNumber();
        calculator.setState(State.FIRST_NUMBER);
        calculator.backSpace();
    }
}
