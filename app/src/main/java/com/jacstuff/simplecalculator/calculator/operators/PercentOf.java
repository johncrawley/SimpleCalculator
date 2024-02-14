package com.jacstuff.simplecalculator.calculator.operators;

import java.math.BigDecimal;

public class PercentOf extends AbstractOperatorAction {

    public PercentOf(){
        super("%");
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        BigDecimal intermediateResult = num2.multiply(num1, mathContext);
        return intermediateResult.divide(new BigDecimal(100), mathContext);
    }
}
