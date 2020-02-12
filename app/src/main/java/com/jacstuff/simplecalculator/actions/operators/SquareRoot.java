package com.jacstuff.simplecalculator.actions.operators;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class SquareRoot extends AbstractOperatorAction {

    public SquareRoot(MathContext mathContext){
        this.mathContext = mathContext;
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        if(num1.signum() == -1){
            throw new ArithmeticException();
        }
        final int SCALE = 30;
        BigDecimal x0 = BigDecimal.ZERO;
        final BigDecimal TWO = new BigDecimal(2);
        BigDecimal x1 = new BigDecimal(Math.sqrt(num1.doubleValue()));
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = num1.divide(x0, SCALE, ROUND_HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(TWO, SCALE, ROUND_HALF_UP);
        }
        return x1.setScale(10, ROUND_HALF_UP);
    }


    @Override
    public boolean hasSingleInput(){
        return true;
    }
}
