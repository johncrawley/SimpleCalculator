package com.jacstuff.simplecalculator;

import android.util.Log;
import android.widget.TextView;

import com.jacstuff.simplecalculator.actions.operators.Operator;
import com.jacstuff.simplecalculator.state.CalcState;
import com.jacstuff.simplecalculator.state.ErrorState;
import com.jacstuff.simplecalculator.state.FirstNumberState;
import com.jacstuff.simplecalculator.state.OperatorState;
import com.jacstuff.simplecalculator.state.ResultState;
import com.jacstuff.simplecalculator.state.SecondNumberState;
import com.jacstuff.simplecalculator.state.State;

import java.util.HashMap;
import java.util.Map;

public class Calculator {


    private Map<State, CalcState> states;

    private CalcState currentState;
    private CalculatorActions calculatorActions;

    public Calculator(TextView textView){
        calculatorActions = new CalculatorActions(textView);
        setupStates();
        setState(State.FIRST_NUMBER);


    }

    private void setupStates(){

        states = new HashMap<>();
        addState(State.FIRST_NUMBER, new FirstNumberState());
        addState(State.OPERATOR, new OperatorState());
        addState(State.SECOND_NUMBER, new SecondNumberState());
        addState(State.ERROR, new ErrorState());
        addState(State.RESULT, new ResultState());

    }

    private void addState(State key, CalcState calcState){
        calcState.setCalculator(this);
        calcState.setCalculatorActions(calculatorActions);
        states.put(key, calcState);

    }



    public void setState(State key){
        Log.i("Calculator", "Entered setState, currentState = " + this.currentState + " new state: " + states.get(key));
        this.currentState = states.get(key);
        this.currentState.init();
    }


    public void setOperator(Operator operator){
        currentState.setOperator(operator);
    }
    public void evaluate(){
        currentState.evaluate();
    }
    public void addDigit(int digit){
        currentState.addDigit(digit);
    }
    public void changeSign() { currentState.changeSign(); }
    public void addDecimal() { currentState.addDecimal(); }

    private void log(String msg){
        Log.i("Calculator", msg);
    }

    public void clear(){
        currentState.clear();
    }




}
