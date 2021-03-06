package com.jacstuff.simplecalculator.actions.operators;

import com.jacstuff.simplecalculator.actions.AbstractAction;
import com.jacstuff.simplecalculator.actions.ButtonAction;

import java.math.MathContext;

public abstract class AbstractOperatorAction extends AbstractAction implements ButtonAction, Operator {

    protected MathContext mathContext;
    protected boolean isCalculatingPercentage = false;

    public void process(){
        calculator.setOperator(this);
    }


    public int execute(){
        return 0;
    }
    public void onLoad(){}
    public boolean hasSingleInput(){return false;}
    @Override public boolean isPercentagePreOperator(){return false;}

    @Override public void setCalculatingPercentage(boolean isCalculatingPercentage){
        this.isCalculatingPercentage = isCalculatingPercentage;
    }
}
