package com.jacstuff.simplecalculator.actions.operators;


import java.math.BigDecimal;
import java.math.MathContext;

public class Multiply extends AbstractOperatorAction {

    public Multiply(MathContext mathContext){
        this.mathContext = mathContext;
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){

        if(isCalculatingPercentage){
            Operator percentOf = new PercentOf(mathContext);
            return percentOf.execute(num2, num1);
        }

        return num1.multiply(num2, mathContext);
    }


    @Override
    public boolean isPercentagePreOperator(){return true;}
}
