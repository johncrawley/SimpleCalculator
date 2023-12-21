package com.jacstuff.simplecalculator.calculator.operators;

import java.math.BigDecimal;

public class Divide  extends AbstractOperatorAction {

    public Divide(){
        super("/");
    }

    @Override
    public BigDecimal execute(BigDecimal number1, BigDecimal number2){
        return number1.divide(number2, mathContext);
    }


}