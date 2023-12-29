package com.jacstuff.simplecalculator.calculator.operators;

import java.math.BigDecimal;

public class Sine extends AbstractOperatorAction {

    public Sine(){
        super("Sine");
        hasSingleInput = true;
    }


    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        return MathUtils.performTrigFunction(Math::sin, num1).round(mathContext);
    }

}
