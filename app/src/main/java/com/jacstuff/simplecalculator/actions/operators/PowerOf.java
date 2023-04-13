package com.jacstuff.simplecalculator.actions.operators;

import java.math.BigDecimal;
import java.math.MathContext;

public class PowerOf extends AbstractOperatorAction {

    public PowerOf(MathContext mathContext){
        this.mathContext = mathContext;
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        double doubleExponent = num2.doubleValue();
        if(isFractionalNumber(doubleExponent)){
            double result = Math.pow(num1.doubleValue(), doubleExponent);
            return new BigDecimal(result, mathContext);
        }
        int exponent = num2.toBigInteger().intValue();
        return num1.pow(exponent, mathContext);
    }

    private boolean isFractionalNumber(double number){
        return Math.floor(number) - number != 0;
    }

}
