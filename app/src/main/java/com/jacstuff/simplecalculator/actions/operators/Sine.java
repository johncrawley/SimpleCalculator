package com.jacstuff.simplecalculator.actions.operators;

import java.math.BigDecimal;
import java.math.MathContext;

public class Sine extends AbstractOperatorAction {

    public Sine(MathContext mathContext){
        this.mathContext = mathContext;
        hasSingleInput = true;
    }


    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        double sine = Math.sin(num1.doubleValue());
        return BigDecimal.valueOf(Math.sin(sine));
    }

}
