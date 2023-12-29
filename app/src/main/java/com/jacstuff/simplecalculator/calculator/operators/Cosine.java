package com.jacstuff.simplecalculator.calculator.operators;

import java.math.BigDecimal;

public class Cosine extends AbstractOperatorAction {

    public Cosine(){
        super("Cosine");
        hasSingleInput = true;
    }


    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        return MathUtils.performTrigFunction(Math::cos, num1).round(mathContext);
    }

}