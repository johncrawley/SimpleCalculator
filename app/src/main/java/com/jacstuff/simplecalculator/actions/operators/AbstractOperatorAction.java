package com.jacstuff.simplecalculator.actions.operators;

import com.jacstuff.simplecalculator.actions.AbstractAction;
import com.jacstuff.simplecalculator.actions.ButtonAction;

import java.math.MathContext;

public abstract class AbstractOperatorAction extends AbstractAction implements ButtonAction, Operator {

    protected MathContext mathContext;
    protected boolean isCalculatingPercentage = false;
    protected boolean isPercentagePreOperator = false;
    protected boolean hasSingleInput = false;

    public void process(){
        calculator.setOperatorFromButton(this);
    }


    public void onLoad(){}


    public boolean hasSingleInput(){ return hasSingleInput;}


    @Override public boolean isPercentagePreOperator(){return isPercentagePreOperator;}


    @Override public void setCalculatingPercentage(boolean isCalculatingPercentage){
        this.isCalculatingPercentage = isCalculatingPercentage;
    }
}
