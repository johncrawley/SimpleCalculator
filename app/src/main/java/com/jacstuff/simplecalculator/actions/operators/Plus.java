package com.jacstuff.simplecalculator.actions.operators;

import android.util.Log;

import java.math.BigDecimal;
import java.math.MathContext;

public class Plus extends AbstractOperatorAction {

    public Plus(MathContext mathContext){
        this.mathContext = mathContext;
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
        log("num1 : " + num1.toPlainString() + " num2 : " + num2.toPlainString());
        return num1.add(num2, mathContext);
    }


    private void log(String msg){
        Log.i("Plus", msg);
    }
}
