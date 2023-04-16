package com.jacstuff.simplecalculator.view;

import androidx.lifecycle.ViewModel;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.state.CalcState;
import com.jacstuff.simplecalculator.state.State;

import java.util.HashMap;

public class MainViewModel extends ViewModel {

    public String display;
    public OperandString operandStr1;
    public OperandString operandStr2;
    public OperandString resultOperand;
    public State calculatorStateName = State.FIRST_NUMBER;
    public HashMap<State, CalcState> calculatorStates;
}
