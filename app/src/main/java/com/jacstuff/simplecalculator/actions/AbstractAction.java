package com.jacstuff.simplecalculator.actions;

import com.jacstuff.simplecalculator.Calculator;

public abstract class AbstractAction {


    protected Calculator calculator;
    private String symbol;

    public void setCalculator(Calculator calculator){
        this.calculator = calculator;
    }
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    public String getSymbol(){
        return symbol;
    }
}
