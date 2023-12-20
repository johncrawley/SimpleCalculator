package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.calculator.Calculator;
import com.jacstuff.simplecalculator.calculator.CalculatorActions;
import com.jacstuff.simplecalculator.actions.operators.Operator;
import com.jacstuff.simplecalculator.calculator.StateManager;

public interface CalcState {

    void setOperator(Operator operator);
    void addDigit(int digit);
    void setNumber(double number);
    void setNumber(double number, String displayValue);
    void clear();
    void evaluate();
    void setStateManager(StateManager stateManager);
    void setCalculatorActions(CalculatorActions calculatorActions);
    void init();
    void changeSign();
    void addDecimal();
    void deleteDigit();
    void saveNumberToMemory();
    void recallNumberFromMemory();


}
