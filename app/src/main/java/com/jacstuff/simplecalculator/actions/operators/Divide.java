package com.jacstuff.simplecalculator.actions.operators;

import java.math.BigDecimal;
import java.math.MathContext;

public class Divide  extends AbstractOperatorAction {

    public Divide(MathContext mathContext){
        this.mathContext = mathContext;
    }

    @Override
    public BigDecimal execute(BigDecimal number1, BigDecimal number2){

        return number1.divide(number2, mathContext);
    }


}