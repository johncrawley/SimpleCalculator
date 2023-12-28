package com.jacstuff.simplecalculator.calculator.operators;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SquareRoot extends AbstractOperatorAction {

    public SquareRoot(){
        super("√");
        hasSingleInput = true;
    }


    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        throwExceptionIfNegative(num1);
        BigDecimal x1 = BigDecimal.valueOf(Math.sqrt(num1.doubleValue()));
        x1 = getMoreAccurateRoot(num1, x1);

        BigDecimal result =  x1.setScale(10, RoundingMode.HALF_UP);
        log("execute() result: " + result.toPlainString());
        return result;
    }


    /*
         An accurate square root, x, divided into the original number should have a result of x
         While this is not the case, add the different result to the estimate and divide by two,
          setting this to be the new estimate. It will eventually converge on the correct answer.
     */
    private BigDecimal getMoreAccurateRoot(BigDecimal originalNumber, BigDecimal estimate){
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal x1 = estimate;

        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = divide(originalNumber, x0);
            x1 = x1.add(x0);
            x1 = halfOf(x1);
        }
        return x1;
    }


    private BigDecimal divide(BigDecimal num1, BigDecimal num2){
        int scale = 30;
        return num1.divide(num2, scale, RoundingMode.HALF_UP);
    }


    private BigDecimal halfOf(BigDecimal number){
       return divide(number, new BigDecimal(2));
    }


    private void throwExceptionIfNegative(BigDecimal num){
        if(num.signum() == -1){
            throw new ArithmeticException();
        }
    }

    private void log(String msg){
        System.out.println("^^^ SquareRoot: " + msg);
    }
}
