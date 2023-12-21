package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;

public class ResultState extends AbstractState implements CalcState {


    private final OperandString firstOperandString, secondOperandString, resultString;

    public ResultState(OperandString firstOperandString, OperandString secondOperandString, OperandString resultString){
        this.firstOperandString = firstOperandString;
        this.secondOperandString = secondOperandString;
        this.resultString = resultString;
    }


    @Override
    public void init(){
        // do nothing
    }


    @Override
    public void setOperator(Operator operator) {
        calculatorActions.copyResultToFirstNumber();
        stateManager.setState(State.FIRST_NUMBER);
        stateManager.setOperatorState(operator);
    }


    @Override
    public void addDigit(int digit) {
        calculatorActions.clearNumbersAndDisplayText();
        stateManager.setState(State.FIRST_NUMBER);
        stateManager.addDigit(digit);
    }


    @Override
    public void setNumber(double number) {
        calculatorActions.clearNumbersAndDisplayText();
        stateManager.setState(State.FIRST_NUMBER);
        stateManager.setNumber(number);
    }


    @Override
    public void setNumber(double number, String displayValue) {
        calculatorActions.clearNumbersAndDisplayText();
        stateManager.setState(State.FIRST_NUMBER);
        stateManager.setNumber(number);
        updateDisplay(displayValue);
    }


    @Override
    public void changeSign() {
        calculatorActions.copyResultToFirstNumber();
        stateManager.setState(State.FIRST_NUMBER);
        stateManager.changeSign();
    }


    @Override
    public void addDecimal() {
        calculatorActions.copyResultToFirstNumber();
        secondOperandString.init();
        stateManager.setState(State.FIRST_NUMBER);
        firstOperandString.addDecimal();
        updateDisplay(firstOperandString.get());
    }


    @Override
    public void clear() {
        stateManager.setState(State.FIRST_NUMBER);
        calculatorActions.clearNumbersAndDisplayText();
    }


    @Override
    public void evaluate() {
        //do nothing
    }


    @Override
    public void deleteDigit() {
        secondOperandString.init();
        calculatorActions.copyResultToFirstNumber();
        stateManager.setState(State.FIRST_NUMBER);
        stateManager.backSpace();
    }


    @Override
    public void saveNumberToMemory(){
        calculatorActions.saveNumberToMemory(resultString);
    }


    @Override
    public void recallNumberFromMemory(){
        stateManager.setState(State.FIRST_NUMBER);
        stateManager.recallNumberFromMemory();
    }
}
