package com.jacstuff.simplecalculator.actions.operators;

import java.math.BigDecimal;
import java.math.MathContext;

public class Cosine extends AbstractOperatorAction {

    public Cosine(){
        super("Cosine");
        hasSingleInput = true;
    }


    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        return MathUtils.performFunction(Math::cos, num1);
    }

}