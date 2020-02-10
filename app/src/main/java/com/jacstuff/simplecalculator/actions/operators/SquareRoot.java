package com.jacstuff.simplecalculator.actions.operators;

import android.util.Log;

import com.jacstuff.simplecalculator.state.State;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class SquareRoot extends AbstractOperatorAction {

    public SquareRoot(MathContext mathContext){
        this.mathContext = mathContext;
    }

    public int execute(int num1, int num2){
        Log.i("SquareRoot", "Entered execute()");
        return (int)Math.sqrt(num1);
    }

    public BigDecimal execute(BigDecimal num1, BigDecimal num2){
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
    public void onLoad(){
        Log.i("SquareRoot", "Entered onLoad()");
       // calculator.setState(State.SECOND_NUMBER);
       // calculator.evaluate();
    }

    @Override
    public boolean hasSingleInput(){
        return true;
    }
}
