package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;

import java.math.BigDecimal;

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
        calculator.setOperatorState(operator);
    }


    @Override
    public void changeSign() {
        firstOperandString.negate();
        updateDisplay(firstOperandString.get());
    }


    @Override
    public void addDecimal() {
        firstOperandString.addDecimal();
        updateDisplay(firstOperandString.get());
    }


    @Override
    public void addDigit(int digit) {
        if(!hasFirstDigitBeenAdded){
            secondOperandString.init();
        }
        if(firstOperandString.isAtMaxLength()){
            return;
        }
        firstOperandString.addDigit(digit);
        updateDisplay(firstOperandString.get());
        hasFirstDigitBeenAdded = true;
    }


    @Override
    public void setNumber(double number) {
        BigDecimal bd = BigDecimal.valueOf(number);
        firstOperandString.set(bd);
        updateDisplay(firstOperandString.get());
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
        updateDisplay(firstOperandString.get());
    }


    @Override
    public void saveNumberToMemory(){
        calculatorActions.saveNumberToMemory(firstOperandString);
    }


    @Override
    public void recallNumberFromMemory(){
        calculatorActions.recallNumberFromMemory(firstOperandString);
        updateDisplay(firstOperandString.get());
    }
}
