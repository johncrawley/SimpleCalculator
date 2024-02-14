package com.jacstuff.simplecalculator.calculator.operators;

import java.math.BigDecimal;

public class Add extends PercentagePreOperatorAction {

    public Add(){
        super("+");
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        if(isCalculatingPercentage){
            BigDecimal percentResult =  percentOf.execute(num2, num1);
            return num1.add(percentResult, mathContext);
        }
        return num1.add(num2, mathContext);
    }

}
