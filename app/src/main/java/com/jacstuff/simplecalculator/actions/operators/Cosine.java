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
        double sine = Math.cos(num1.doubleValue());
        BigDecimal bd = BigDecimal.valueOf(Math.sin(sine));
        System.out.println("^^^ Cosine.execute() result : " + bd.toPlainString());
        return bd;
    }

}