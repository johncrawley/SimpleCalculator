package com.jacstuff.simplecalculator.calculator.operators;


import java.math.BigDecimal;

public class Multiply extends PercentagePreOperatorAction {

    public Multiply(){
        super("×");
    }


    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        return isCalculatingPercentage ? percentOf.execute(num2, num1)
                : num1.multiply(num2, mathContext);
    }
}
