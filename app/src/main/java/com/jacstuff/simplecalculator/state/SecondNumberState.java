package com.jacstuff.simplecalculator.state;

import android.util.Log;

import com.jacstuff.simplecalculator.actions.operators.PercentOf;
import com.jacstuff.simplecalculator.calculator.display.OperandString;
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

    private void log(String msg){
        Log.i("2ndNumState", msg);
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
        boolean success = calculatorActions.evaluateAndDisplay();
        if (success) {
            calculatorActions.copyResultToFirstNumber();
            calculator.setState(State.FIRST_NUMBER);
            calculator.setOperator(operator);
            calculatorActions.displayResult(); // because we'd rather see the result of the existing operation than the operator symbol
        }

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
    }
}
