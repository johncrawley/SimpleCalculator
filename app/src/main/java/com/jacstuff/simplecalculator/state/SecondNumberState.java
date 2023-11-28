package com.jacstuff.simplecalculator.state;


import com.jacstuff.simplecalculator.actions.operators.PercentOf;
import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;

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
        return calculator.getExistingOperator().isPercentagePreOperator() && operator instanceof PercentOf;
    }


    private void evaluateCompoundPercentage(){
        boolean success = calculatorActions.evaluatePercentageAndDisplay();
        if(success){
            calculatorActions.displayResult();
            calculator.setState(State.RESULT);
        }
    }


    private void evaluateAndDisplayNewResult(Operator operator){
        boolean success = calculatorActions.evaluateUsingPreviousOperatorAndDisplayResult();
        if (success) {
            calculatorActions.copyResultToFirstNumber();
            calculator.setState(State.SECOND_NUMBER);
            calculator.assignOperator(operator);
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
        calculator.setState(State.FIRST_NUMBER);
    }


    @Override
    public void setNumber(double number) {
        BigDecimal bd = BigDecimal.valueOf(number);
        secondOperandString.set(bd);
        updateDisplay(secondOperandString.get());
    }


    @Override
    public void evaluate() {
        boolean success = calculatorActions.evaluateAndDisplay();
        if(success){
            calculator.setState(State.RESULT);
        }
    }


    @Override
    public void saveNumberToMemory(){
        calculatorActions.saveNumberToMemory(secondOperandString);
    }


    @Override
    public void recallNumberFromMemory(){
        calculatorActions.recallNumberFromMemory(secondOperandString);
        updateDisplay(secondOperandString.get());
    }
}
