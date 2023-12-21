package com.jacstuff.simplecalculator.calculator.operators;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SquareRoot extends AbstractOperatorAction {

    public SquareRoot(){
        super("√");
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        if(num1.signum() == -1){
            throw new ArithmeticException();
        }
        final int SCALE = 30;
        BigDecimal x0 = BigDecimal.ZERO;
        final BigDecimal TWO = new BigDecimal(2);
        BigDecimal x1 = BigDecimal.valueOf(Math.sqrt(num1.doubleValue()));
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = num1.divide(x0, SCALE, RoundingMode.HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(TWO, SCALE, RoundingMode.HALF_UP);
        }
        return x1.setScale(10, RoundingMode.HALF_UP);
    }


    @Override
    public boolean hasSingleInput(){
        return true;
    }
}
