package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;

public class FirstNumberState extends AbstractState implements CalcState {


    private final OperandString firstOperandString, secondOperandString;
    private boolean hasFirstDigitBeenAdded = false;

    public FirstNumberState(OperandString firstOperandString, OperandString secondOperandString){
        this.firstOperandString = firstOperandString;
        this.secondOperandString = secondOperandString;
    }


    @Override
    public void init(){
        hasFirstDigitBeenAdded = false;
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
        if(!hasFirstDigitBeenAdded){
            secondOperandString.init();
        }
        firstOperandString.addDigit(digit);
        hasFirstDigitBeenAdded = true;
    }


    @Override
    public void clear() {
        calculatorActions.clearNumbersAndDisplayText();
    }


    @Override
    public void evaluate() { // do nothing
     }


    @Override
    public void deleteDigit() {
        firstOperandString.deleteDigit();
    }


    @Override
    public void saveNumberToMemory(){
        calculatorActions.saveNumberToMemory(firstOperandString);
    }

    @Override
    public void recallNumberFromMemory(){
        calculatorActions.recallNumberFromMemory(firstOperandString);
    }
}
