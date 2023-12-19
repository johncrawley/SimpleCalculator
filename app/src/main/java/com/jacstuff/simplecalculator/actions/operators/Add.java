package com.jacstuff.simplecalculator.actions.operators;

import java.math.BigDecimal;
import java.math.MathContext;

public class Add extends AbstractOperatorAction {

    public Add(){
        super("+");
        isPercentagePreOperator = true;
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        if(isCalculatingPercentage){
            Operator percentOf = new PercentOf();
            BigDecimal percentResult =  percentOf.execute(num2, num1);
            return num1.add(percentResult, mathContext);
        }
        return num1.add(num2, mathContext);
    }

}
