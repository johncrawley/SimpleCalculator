package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.actions.operators.Operator;
import com.jacstuff.simplecalculator.calculator.Calculator;
import com.jacstuff.simplecalculator.calculator.CalculatorActions;

public class AbstractState {

    Calculator calculator;
    CalculatorActions calculatorActions;

    public void setCalculator(Calculator calculator){
        this.calculator = calculator;
        this.calculatorActions = calculator.getCalculatorActions();
    }


    public void setCalculatorActions(CalculatorActions calculatorActions){ this.calculatorActions = calculatorActions;}


    public void saveNumberToMemory(){}
    public void recallNumberFromMemory(){}


    public void init(){}

    public void changeSign() {
    }

    public void addDecimal() {
    }

    public void setOperator(Operator operator){
    }

    public void addDigit(int digit){
    }

    public void evaluate() {
    }

    public void deleteDigit() {

    }

    void updateDisplay(String str){
        calculator.updateDisplay(str);
    }
}
