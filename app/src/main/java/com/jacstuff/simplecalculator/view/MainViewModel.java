package com.jacstuff.simplecalculator.view;

import androidx.lifecycle.ViewModel;

import com.jacstuff.simplecalculator.calculator.operators.Operator;
import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.calculator.state.CalcState;
import com.jacstuff.simplecalculator.calculator.state.State;

import java.util.HashMap;

public class MainViewModel extends ViewModel {

    public String displayStr = "0";
    public OperandString operandStr1;
    public OperandString operandStr2;
    public OperandString resultOperand;
    public State currentCalculatorStateName = State.FIRST_NUMBER;
    public HashMap<State, CalcState> calculatorStates;
    public Operator operator, previousOperator;
    public CalcState currentState;
}
