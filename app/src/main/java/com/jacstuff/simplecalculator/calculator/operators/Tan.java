package com.jacstuff.simplecalculator.calculator.operators;

import java.math.BigDecimal;

public class Tan extends AbstractOperatorAction {

    public Tan(){
        super("Tangent");
        hasSingleInput = true;
    }


    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        return MathUtils.performFunction(Math::tan, num1);
    }

}