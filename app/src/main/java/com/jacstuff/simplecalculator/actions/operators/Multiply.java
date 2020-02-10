package com.jacstuff.simplecalculator.actions.operators;


import java.math.BigDecimal;
import java.math.MathContext;

public class Multiply extends AbstractOperatorAction {

    public Multiply(MathContext mathContext){
        this.mathContext = mathContext;
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        return num1.multiply(num2, mathContext);
    }
}
