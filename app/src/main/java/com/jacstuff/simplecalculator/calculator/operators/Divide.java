package com.jacstuff.simplecalculator.calculator.operators;

import java.math.BigDecimal;

public class Divide  extends PercentagePreOperatorAction {

    public Divide(){
        super("÷");
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        return isCalculatingPercentage ? divideByPercent(num1, num2)
                : num1.divide(num2, mathContext);
    }


    private BigDecimal divideByPercent(BigDecimal num1, BigDecimal num2){
        BigDecimal temp = num1.multiply(new BigDecimal(100));
        return temp.divide(num2, mathContext);
    }
}