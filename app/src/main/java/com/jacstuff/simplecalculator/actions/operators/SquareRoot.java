package com.jacstuff.simplecalculator.actions.operators;

import android.util.Log;

import com.jacstuff.simplecalculator.state.State;

public class SquareRoot extends AbstractOperatorAction {

    public int execute(int num1, int num2){
        Log.i("SquareRoot", "Entered execute()");
        return (int)Math.sqrt(num1);
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
