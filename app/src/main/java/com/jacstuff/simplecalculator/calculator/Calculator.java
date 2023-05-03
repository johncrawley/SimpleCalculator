package com.jacstuff.simplecalculator.calculator;

import android.content.Context;
import android.widget.TextView;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;
import com.jacstuff.simplecalculator.state.CalcState;
import com.jacstuff.simplecalculator.state.ErrorState;
import com.jacstuff.simplecalculator.state.FirstNumberState;
import com.jacstuff.simplecalculator.state.OperatorState;
import com.jacstuff.simplecalculator.state.ResultState;
import com.jacstuff.simplecalculator.state.SecondNumberState;
import com.jacstuff.simplecalculator.state.State;
import com.jacstuff.simplecalculator.view.MainViewModel;

import java.util.HashMap;

public class Calculator {

    private CalculatorActions calculatorActions;
    private final MainViewModel viewModel;
    private final TextView display;


    public Calculator(Context context, TextView textView, MainViewModel viewModel){
        this.viewModel = viewModel;
        this.display = textView;
        initFields(context);
        setupStates();
        assignState();
        updateDisplay();
    }


    OperandString getResultStr(){ return viewModel.resultOperand;}


    private void initFields(Context context){
        initOperands();
        Memory memory = new Memory(context);
        calculatorActions = new CalculatorActions(this, memory, viewModel);
    }


    public CalculatorActions getCalculatorActions(){
        return calculatorActions;
    }


    private void initOperands(){
        if(viewModel.operandStr1 == null){
            viewModel.operandStr1 = new OperandString();
        }
        if(viewModel.operandStr2 == null){
            viewModel.operandStr2 = new OperandString();
        }
        if(viewModel.resultOperand == null){
            viewModel.resultOperand = new OperandString();
        }
    }


    private void setupStates(){
        if(viewModel.calculatorStates == null){
            viewModel.calculatorStates = new HashMap<>();
            addState(State.FIRST_NUMBER, new FirstNumberState(viewModel.operandStr1, viewModel.operandStr2));
            addState(State.OPERATOR, new OperatorState(viewModel.operandStr2));
            addState(State.SECOND_NUMBER, new SecondNumberState(viewModel.operandStr2));
            addState(State.ERROR, new ErrorState());
            addState(State.RESULT, new ResultState(viewModel.operandStr1, viewModel.operandStr2, viewModel.resultOperand));

        }
        for(CalcState calcState : viewModel.calculatorStates.values()){
            calcState.setCalculator(this);
        }
    }


    public void updateDisplay(String str){
        viewModel.displayStr = str;
        updateDisplay();
    }


    private void updateDisplay(){
        display.setText(viewModel.displayStr);
    }


    private void addState(State key, CalcState calcState){
        viewModel.calculatorStates.put(key, calcState);
    }


    public Operator getOperator(){
        return viewModel.operator;
    }


    public void setState(State stateName){
        viewModel.currentCalculatorStateName = stateName;
        CalcState calcState = viewModel.calculatorStates.get(stateName);
        if(calcState != null){
            viewModel.currentState = calcState;
            viewModel.currentState.init();
        }
    }


    private void assignState(){
        viewModel.currentState = viewModel.calculatorStates.get(viewModel.currentCalculatorStateName);
    }


    public void setOperator(Operator operator){
        viewModel.operator = operator;
        viewModel.currentState.setOperator(operator);
    }


    public Operator getExistingOperator(){
        return viewModel.operator;
    }


    public void evaluate(){
        viewModel.currentState.evaluate();
    }


    public void addDigit(int digit){
        viewModel.currentState.addDigit(digit);
    }


    public void changeSign() { viewModel.currentState.changeSign(); }


    public void addDecimal() { viewModel.currentState.addDecimal(); }


    public void backSpace() { viewModel.currentState.deleteDigit();}


    public void clear(){
        viewModel.currentState.clear();
    }


    public void saveNumberToMemory(){ viewModel.currentState.saveNumberToMemory();}


    public void recallNumberFromMemory(){ viewModel.currentState.recallNumberFromMemory();}


}
