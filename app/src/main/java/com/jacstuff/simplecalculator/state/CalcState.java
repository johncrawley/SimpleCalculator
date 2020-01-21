package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.Calculator;
import com.jacstuff.simplecalculator.CalculatorActions;
import com.jacstuff.simplecalculator.actions.operators.Operator;

public interface CalcState {

    void setOperator(Operator operator);
    void addDigit(int digit);
    void clear();
    void evaluate();
    void setCalculator(Calculator calculator);
    void setCalculatorActions(CalculatorActions calculatorActions);
    void init();
    void changeSign();
    void addDecimal();

}
