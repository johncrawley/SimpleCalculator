package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.actions.operators.Operator;
import com.jacstuff.simplecalculator.calculator.Calculator;
import com.jacstuff.simplecalculator.calculator.CalculatorActions;
import com.jacstuff.simplecalculator.calculator.StateManager;

public class AbstractState {

    CalculatorActions calculatorActions;
    StateManager stateManager;


    public void setCalculatorActions(CalculatorActions calculatorActions){ this.calculatorActions = calculatorActions;}


    public void setStateManager(StateManager stateManager){
        this.stateManager = stateManager;
    }


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


    public void setNumber(double number) {
        // do nothing
    }



    public void setNumber(double number, String displayValue) {
        // do nothing
    }


    public void evaluate() {
    }


    public void deleteDigit() {

    }

    void updateDisplay(String str){
        stateManager.updateDisplay(str);
    }
}
