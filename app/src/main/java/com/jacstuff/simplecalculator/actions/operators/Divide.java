package com.jacstuff.simplecalculator.actions.operators;

import com.jacstuff.simplecalculator.Calculator;
import com.jacstuff.simplecalculator.OperandString;
import com.jacstuff.simplecalculator.state.State;

import java.math.BigDecimal;
import java.math.MathContext;

public class Divide  extends AbstractOperatorAction {

    public Divide(MathContext mathContext){
        this.mathContext = mathContext;
    }

    @Override
    public BigDecimal execute(BigDecimal number1, BigDecimal number2){

        if(number2.signum() == 0){
            calculator.setState(State.ERROR);
            return BigDecimal.ZERO;
        }

        return number1.divide(number2, mathContext);

    }


}