package com.jacstuff.simplecalculator.calculator.state;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.calculator.operators.Operator;


public class OperatorState extends AbstractState implements CalcState {

    private final OperandString secondOperandString;

    public OperatorState(OperandString secondOperandString){
        this.secondOperandString = secondOperandString;
    }


    @Override
    public void init(){
        //do nothing
    }


    @Override
    public void setOperator(Operator operator){
        calculatorActions.loadAndDisplayOperator();
        if(operator.hasSingleInput()) {
            stateManager.setState(State.SECOND_NUMBER);
            stateManager.evaluate();
        }
    }


    @Override
    public void addDigit(int digit) {
        secondOperandString.init();
        stateManager.setState(State.SECOND_NUMBER);
        stateManager.addDigit(digit);
    }


    @Override
    public void setNumber(double number) {
        stateManager.setState(State.SECOND_NUMBER);
        stateManager.setNumber(number);
    }


    @Override
    public void setNumber(double number, String displayValue) {
        stateManager.setState(State.SECOND_NUMBER);
        stateManager.setNumber(number);
        updateDisplay(displayValue);
    }


    @Override
    public void changeSign() {
    }


    @Override
    public void addDecimal() {
        secondOperandString.init();
        stateManager.setState(State.SECOND_NUMBER);
        stateManager.addDecimal();
    }


    @Override
    public void clear() {
        calculatorActions.clearNumbersAndDisplayText();
        stateManager.setState(State.FIRST_NUMBER);
    }


    @Override
    public void evaluate() {
        boolean success = calculatorActions.evaluateAndDisplay();
        if(success){
            stateManager.setState(State.RESULT);
        }
    }


    @Override
    public void deleteDigit() {}


    @Override
    public void recallNumberFromMemory(){
        stateManager.setState(State.SECOND_NUMBER);
        stateManager.recallNumberFromMemory();
    }
}
