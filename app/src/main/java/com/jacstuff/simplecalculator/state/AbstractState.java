package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.calculator.Calculator;
import com.jacstuff.simplecalculator.calculator.CalculatorActions;

public class AbstractState {

    Calculator calculator;
    CalculatorActions calculatorActions;

    public void setCalculator(Calculator calculator){
        this.calculator = calculator;
    }
    public void setCalculatorActions(CalculatorActions calculatorActions){ this.calculatorActions = calculatorActions;}
}
