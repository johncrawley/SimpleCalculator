package com.jacstuff.simplecalculator.calculator.operators;

import java.math.BigDecimal;
import java.util.function.Function;

public class MathUtils {

    public static BigDecimal performFunction(Function<Double, Double> function, BigDecimal num1){
        double rads = Math.toRadians(num1.doubleValue());
        double sineInRads = function.apply(rads);
        return BigDecimal.valueOf(sineInRads);
    }
}
