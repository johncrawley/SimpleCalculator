package com.jacstuff.simplecalculator.calculator;

import android.content.Context;
import android.widget.TextView;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.calculator.display.UpdatableDisplay;
import com.jacstuff.simplecalculator.calculator.display.UpdatableDisplayImpl;
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

    private CalcState currentState;
    private CalculatorActions calculatorActions;
    private Operator operator;
    UpdatableDisplay updatableDisplay;
    private final MainViewModel viewModel;
    private final TextView display;


    public Calculator(Context context, TextView textView, MainViewModel viewModel){
        this.viewModel = viewModel;
        this.display = textView;
        initFields(context, textView, viewModel);
        setupStates();
        setState(viewModel.calculatorStateName);
    }


    OperandString getNumberStr1(){return viewModel.operandStr1;}


    OperandString getNumberStr2(){return viewModel.operandStr2;}


    OperandString getResultStr(){ return viewModel.resultOperand;}


    private void initFields(Context context, TextView textView, MainViewModel viewModel){
        updatableDisplay = new UpdatableDisplayImpl(textView, viewModel);
        initOperands();
        Memory memory = new Memory(context);
        calculatorActions = new CalculatorActions(this, memory, textView);
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
            addState(State.RESULT, new ResultState(viewModel.operandStr1, viewModel.operandStr2,viewModel.resultOperand));
        }
        for(CalcState calcState : viewModel.calculatorStates.values()){
            calcState.setCalculator(this);
        }
    }


    public void updateDisplay(String str){
        display.setText(str);
    }


    private void addState(State key, CalcState calcState){
        calcState.setCalculator(this);
        calcState.setCalculatorActions(calculatorActions);
        viewModel.calculatorStates.put(key, calcState);
    }


    public void setState(State stateName){
        viewModel.calculatorStateName = stateName;
        CalcState calcState = viewModel.calculatorStates.get(stateName);
        if(calcState != null){
            this.currentState = calcState;
            this.currentState.init();
        }
    }


    public void setOperator(Operator operator){
        currentState.setOperator(operator);
        this.operator = operator;
    }


    public Operator getExistingOperator(){
        return operator;
    }


    public void evaluate(){
        currentState.evaluate();
    }


    public void addDigit(int digit){
        currentState.addDigit(digit);
    }


    public void changeSign() { currentState.changeSign(); }


    public void addDecimal() { currentState.addDecimal(); }


    public void backSpace() { currentState.deleteDigit();}


    public void clear(){
        currentState.clear();
    }


    public void saveNumberToMemory(){ currentState.saveNumberToMemory();}


    public void recallNumberFromMemory(){ currentState.recallNumberFromMemory();}


}
