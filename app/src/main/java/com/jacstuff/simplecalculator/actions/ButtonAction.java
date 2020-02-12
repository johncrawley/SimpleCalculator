package com.jacstuff.simplecalculator.actions;

import com.jacstuff.simplecalculator.calculator.Calculator;

public interface ButtonAction {
    void process();
    void setCalculator(Calculator calculator);
    void setSymbol(String symbol);
}
