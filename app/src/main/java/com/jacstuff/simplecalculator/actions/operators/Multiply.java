package com.jacstuff.simplecalculator.actions.operators;


import java.math.BigDecimal;
import java.math.MathContext;

public class Multiply extends AbstractOperatorAction {

    public Multiply(){
        super("×");
        isPercentagePreOperator = true;
    }


    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        return isCalculatingPercentage ? new PercentOf().execute(num2, num1)
                : num1.multiply(num2, mathContext);
    }
}
