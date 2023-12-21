package com.jacstuff.simplecalculator.calculator.state;


import com.jacstuff.simplecalculator.calculator.operators.PercentOf;
import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.calculator.operators.Operator;

import java.math.BigDecimal;

public class SecondNumberState extends AbstractState implements CalcState {

    private final OperandString secondOperandString;

    public SecondNumberState(OperandString secondOperandString){
        this.secondOperandString = secondOperandString;
    }


    @Override
    public void init(){
        calculatorActions.clearSecondNumberString();
    }


    @Override
    public void setOperator(Operator operator) {
        if(isCompoundPercentageOperation(operator)){
            evaluateCompoundPercentage();
            return;
        }
        evaluateAndDisplayNewResult(operator);
    }


    private boolean isCompoundPercentageOperation(Operator operator){
        return stateManager.getPreviousOperator().isPercentagePreOperator() && operator instanceof PercentOf;
    }


    private void evaluateCompoundPercentage(){
        boolean success = calculatorActions.evaluatePercentageAndDisplay();
        if(success){
            calculatorActions.displayResult();
            stateManager.setState(State.RESULT);
        }
    }


    private void evaluateAndDisplayNewResult(Operator operator){
        boolean success = calculatorActions.evaluateUsingPreviousOperatorAndDisplayResult();
        if (success) {
            calculatorActions.copyResultToFirstNumber();
            stateManager.setState(State.SECOND_NUMBER);
            stateManager.assignOperator(operator);
            calculatorActions.displayResult(); // because we'd rather see the result of the existing operation than the operator symbol
        }
    }


    @Override
    public void changeSign() {
        secondOperandString.negate();
        updateDisplay(secondOperandString.get());
    }


    @Override
    public void addDecimal() {
        secondOperandString.addDecimal();
        updateDisplay(secondOperandString.get());
    }


    @Override
    public void deleteDigit() {
        secondOperandString.deleteDigit();
        updateDisplay(secondOperandString.get());
    }


    @Override
    public void addDigit(int digit) {
        if(secondOperandString.isAtMaxLength()){
            return;
        }
        secondOperandString.addDigit(digit);
        updateDisplay(secondOperandString.get());
    }


    @Override
    public void clear() {
        calculatorActions.clearNumbersAndDisplayText();
        stateManager.setState(State.FIRST_NUMBER);
    }


    @Override
    public void setNumber(double number) {
        BigDecimal bd = BigDecimal.valueOf(number);
        secondOperandString.set(bd);
        updateDisplay(secondOperandString.get());
    }


    @Override
    public void setNumber(double number, String displayValue) {
        BigDecimal bd = BigDecimal.valueOf(number);
        secondOperandString.set(bd);
        updateDisplay(secondOperandString.get());
        updateDisplay(displayValue);
    }


    @Override
    public void evaluate() {
        boolean success = calculatorActions.evaluateAndDisplay();
        if(success){
            stateManager.setState(State.RESULT);
        }
    }


    @Override
    public void saveNumberToMemory(){
        calculatorActions.saveNumberToMemory(secondOperandString);
    }


    @Override
    public void recallNumberFromMemory(){
        calculatorActions.recallAndDisplayNumberFromMemory(secondOperandString);
    }
}
