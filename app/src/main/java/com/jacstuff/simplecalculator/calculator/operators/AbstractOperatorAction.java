package com.jacstuff.simplecalculator.calculator.operators;


import java.math.MathContext;

public abstract class AbstractOperatorAction implements Operator {

    protected MathContext mathContext;
    protected boolean isCalculatingPercentage = false;
    protected boolean isPercentagePreOperator = false;
    protected boolean hasSingleInput = false;
    private final String symbol;

    public AbstractOperatorAction(String displayStr){
        this.symbol = displayStr;
    }


    public void onLoad(){}


    @Override
    public String getSymbol(){
        return symbol;
    }

    @Override
    public boolean hasSingleInput(){ return hasSingleInput;}


    @Override public boolean isPercentagePreOperator(){
        return isPercentagePreOperator;}


    @Override public void setCalculatingPercentage(boolean isCalculatingPercentage){
        this.isCalculatingPercentage = isCalculatingPercentage;
    }


    @Override public void setMathContext(MathContext mc){
        this.mathContext = mc;
    }
}
