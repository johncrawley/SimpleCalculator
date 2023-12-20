package com.jacstuff.simplecalculator.actions.operators;

import java.math.BigDecimal;
import java.math.MathContext;

public class Sine extends AbstractOperatorAction {

    public Sine(){
        super("Sine");
        hasSingleInput = true;
    }


    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        return MathUtils.performFunction(Math::sin, num1);
    }

}
