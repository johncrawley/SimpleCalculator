package com.jacstuff.simplecalculator.actions;

import com.jacstuff.simplecalculator.Calculator;

public interface ButtonAction {
    void process();
    void setCalculator(Calculator calculator);
    void setSymbol(String symbol);
}
