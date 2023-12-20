package com.jacstuff.simplecalculator.actions;

public abstract class AbstractAction {

    private String symbol;

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }


    public String getSymbol(){
        return symbol;
    }
}
