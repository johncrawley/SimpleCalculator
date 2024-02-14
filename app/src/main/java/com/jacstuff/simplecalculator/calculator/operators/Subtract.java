package com.jacstuff.simplecalculator.calculator.operators;

import java.math.BigDecimal;

public class Subtract extends PercentagePreOperatorAction {

    public Subtract(){
        super("−");
    }


    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        if(isCalculatingPercentage){
            BigDecimal percentResult =  percentOf.execute(num2, num1);
            return num1.subtract(percentResult, mathContext);
        }
        return num1.subtract(num2, mathContext);
    }

}