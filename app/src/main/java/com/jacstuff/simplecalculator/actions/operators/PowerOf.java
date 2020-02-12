package com.jacstuff.simplecalculator.actions.operators;

import java.math.BigDecimal;
import java.math.MathContext;

public class PowerOf extends AbstractOperatorAction {

    public PowerOf(MathContext mathContext){
        this.mathContext = mathContext;
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        int exponent = num2.toBigInteger().intValue();
        return num1.pow(exponent, mathContext);
    }
}
