package com.jacstuff.simplecalculator.actions.operators;

import com.jacstuff.simplecalculator.Calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public class PercentOf extends AbstractOperatorAction {

    public PercentOf(MathContext mathContext){
        this.mathContext = mathContext;
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){

        BigDecimal result = num2.multiply(num1, mathContext);
        return result.divide(new BigDecimal(100), mathContext);
    }
}
