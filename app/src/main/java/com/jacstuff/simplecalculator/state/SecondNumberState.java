package com.jacstuff.simplecalculator.state;

import android.util.Log;

import com.jacstuff.simplecalculator.actions.operators.Operator;

public class SecondNumberState extends AbstractState implements CalcState {


    @Override
    public void init(){

    }

    @Override
    public void setOperator(Operator operator) {
        //calculator.setState(new ErrorState());
        calculator.evaluate();
        calculatorActions.assignResultToFirstNumber();
        calculator.setState(State.OPERATOR);
    }


    @Override
    public void changeSign() {

        calculatorActions.changeSignOfSecondNumber();
    }

    @Override
    public void addDecimal() {

    }

    @Override
    public void addDigit(int digit) {
        calculatorActions.addDigitToSecondNumber(digit);
    }

    @Override
    public void clear() {
        calculatorActions.clearNumbers();
        calculator.setState(State.FIRST_NUMBER);
    }

    @Override
    public void evaluate() {
        Log.i("SecondNumberState", "Entered evaluate()");
        calculatorActions.evaluateAndDisplay();
        calculator.setState(State.RESULT);
    }
}
