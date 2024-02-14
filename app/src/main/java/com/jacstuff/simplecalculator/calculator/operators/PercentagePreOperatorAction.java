package com.jacstuff.simplecalculator.calculator.operators;

import java.math.MathContext;

public abstract class PercentagePreOperatorAction extends AbstractOperatorAction{

    protected PercentOf percentOf;

    public PercentagePreOperatorAction(String displayStr){
        super(displayStr);
        this.percentOf = new PercentOf();
        isPercentagePreOperator = true;
    }


    @Override
    public void setMathContext(MathContext mc){
        super.setMathContext(mc);
        percentOf.setMathContext(mc);
    }
}
