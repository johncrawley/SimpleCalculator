package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.Calculator;
import com.jacstuff.simplecalculator.CalculatorActions;

public class AbstractState {

    Calculator calculator;
    CalculatorActions calculatorActions;

    public void setCalculator(Calculator calculator){
        this.calculator = calculator;
    }
    public void setCalculatorActions(CalculatorActions calculatorActions){ this.calculatorActions = calculatorActions;}
}
