package com.jacstuff.simplecalculator.actions.operators;

import java.math.BigDecimal;
import java.math.MathContext;

public class Subtract extends AbstractOperatorAction {

    public Subtract(MathContext mathContext){
        this.mathContext = mathContext;
    }


    public int execute(int num1, int num2) {
        return num1 - num2;
    }

    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        return num1.subtract(num2, mathContext);
    }
}