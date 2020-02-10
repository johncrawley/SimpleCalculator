package com.jacstuff.simplecalculator.actions.operators;

import java.math.BigDecimal;

public interface Operator {

    BigDecimal execute(BigDecimal number1, BigDecimal number2);
    String getSymbol();
    void onLoad();
    boolean hasSingleInput();
}
