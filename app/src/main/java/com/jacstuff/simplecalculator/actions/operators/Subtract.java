package com.jacstuff.simplecalculator.actions.operators;

import android.util.Log;

import java.math.BigDecimal;
import java.math.MathContext;

public class Subtract extends AbstractOperatorAction {

    public Subtract(MathContext mathContext){
        this.mathContext = mathContext;
    }


    public int execute(int num1, int num2) {
        return num1 - num2;
    }

    @Override
    public BigDecimal execute(BigDecimal num1, BigDecimal num2){

        if(isCalculatingPercentage){
            Log.i("Subtract operator", "calculating percentage to subtract...");
            Operator percentOf = new PercentOf(mathContext);
            BigDecimal percentResult =  percentOf.execute(num2, num1);
            return num1.subtract(percentResult, mathContext);
        }


        return num1.subtract(num2, mathContext);
    }

    @Override
    public boolean isPercentagePreOperator(){return true;}
}