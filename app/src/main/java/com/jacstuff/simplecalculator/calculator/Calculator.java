package com.jacstuff.simplecalculator.calculator;

import android.content.Context;
import android.widget.TextView;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;
import com.jacstuff.simplecalculator.calculator.display.UpdatableDisplay;
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

    private final CalculatorActions calculatorActions;
    private final UpdatableDisplay updatableDisplay;
    private OperandString operandStr1, operandStr2, resultOperand;
    private Operator operator, previousOperator;
    public State currentCalculatorStateName = State.FIRST_NUMBER;
    public HashMap<State, CalcState> calculatorStates;
    public CalcState currentState;


    public Calculator(Memory memory, UpdatableDisplay updatableDisplay){
        this.updatableDisplay = updatableDisplay;
        initOperands();
        calculatorActions = new CalculatorActions(this, memory);
        setupStates();
        assignState();

    }


    OperandString getResultStr(){ return resultOperand;}

    public OperandString getOperandStr1(){
        return operandStr1;
    }


    public OperandString getOperandStr2(){
        return operandStr2;
    }

    public void process(InputSymbol inputSymbol){

    }



    public CalculatorActions getCalculatorActions(){
        return calculatorActions;
    }


    private void initOperands(){
        int maxLength = 14;
        
        if(operandStr1 == null){
            operandStr1 = new OperandString(maxLength);
        }
        if(operandStr2 == null){
            operandStr2 = new OperandString(maxLength);
        }
        if(resultOperand == null){
            resultOperand = new OperandString(maxLength);
        }
    }
        

    private void setupStates(){
        if(calculatorStates == null){
            calculatorStates = new HashMap<>();
            addState(State.FIRST_NUMBER, new FirstNumberState(operandStr1, operandStr2));
            addState(State.OPERATOR, new OperatorState(operandStr2));
            addState(State.SECOND_NUMBER, new SecondNumberState(operandStr2));
            addState(State.ERROR, new ErrorState());
            addState(State.RESULT, new ResultState(operandStr1, operandStr2, resultOperand));
        }
        for(CalcState calcState : calculatorStates.values()){
            calcState.setCalculator(this);
        }
    }


    public void updateDisplay(String str){
        updatableDisplay.update(str);
    }


    private void addState(State key, CalcState calcState){
        calculatorStates.put(key, calcState);
    }


    public Operator getOperator(){
        return operator;
    }


    public Operator getPreviousOperator(){
        return previousOperator;
    }


    public void setState(State stateName){
        currentCalculatorStateName = stateName;
        CalcState calcState = calculatorStates.get(stateName);
        if(calcState != null){
            currentState = calcState;
            currentState.init();
        }
    }


    private void assignState(){
        currentState = calculatorStates.get(currentCalculatorStateName);
    }


    public void setOperatorFromButton(Operator operator){
        assignOperator(operator);
        setOperatorState(operator);
    }


    public void assignOperator(Operator operator){
        previousOperator = operator;
        this.operator = operator;
    }


    public void setOperatorState(Operator operator){
        currentState.setOperator(operator);
    }


    public void evaluate(){
        currentState.evaluate();
    }


    public void addDigit(int digit){
        currentState.addDigit(digit);
    }


    public void setNumber(double number){
        currentState.setNumber(number);
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
