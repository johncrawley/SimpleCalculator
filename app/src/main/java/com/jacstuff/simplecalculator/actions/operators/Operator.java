package com.jacstuff.simplecalculator.actions.operators;

import java.math.BigDecimal;
import java.math.MathContext;

public interface Operator {

    BigDecimal execute(BigDecimal number1, BigDecimal number2);
    String getSymbol();
    void setMathContext(MathContext mc);
    void onLoad();
    boolean hasSingleInput();
    boolean isPercentagePreOperator();
    void setCalculatingPercentage(boolean isCalculatingPercentage);
}
