package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.actions.operators.Operator;
import com.jacstuff.simplecalculator.calculator.Calculator;
import com.jacstuff.simplecalculator.calculator.CalculatorActions;
import com.jacstuff.simplecalculator.calculator.display.UpdatableDisplay;

public class AbstractState {

    Calculator calculator;
    CalculatorActions calculatorActions;
    UpdatableDisplay updatableDisplay;

    AbstractState(UpdatableDisplay updatableDisplay){
        this.updatableDisplay = updatableDisplay;
    }

    public void setCalculator(Calculator calculator){
        this.calculator = calculator;
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
}
