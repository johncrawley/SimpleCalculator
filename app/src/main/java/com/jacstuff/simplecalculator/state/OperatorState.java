package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;


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
            calculator.setState(State.SECOND_NUMBER);
            calculator.evaluate();
        }
    }


    @Override
    public void addDigit(int digit) {
        secondOperandString.init();
        calculator.setState(State.SECOND_NUMBER);
        calculator.addDigit(digit);
    }


    @Override
    public void setNumber(double number) {
        calculator.setState(State.SECOND_NUMBER);
        calculator.setNumber(number);
    }


    @Override
    public void setNumber(double number, String displayValue) {
        calculator.setState(State.SECOND_NUMBER);
        calculator.setNumber(number);
        updateDisplay(displayValue);
    }


    @Override
    public void changeSign() {
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
        boolean success = calculatorActions.evaluateAndDisplay();
        if(success){
            calculator.setState(State.RESULT);
        }
    }


    @Override
    public void deleteDigit() {}


    @Override
    public void recallNumberFromMemory(){
        calculator.setState(State.SECOND_NUMBER);
        calculator.recallNumberFromMemory();
    }
}
