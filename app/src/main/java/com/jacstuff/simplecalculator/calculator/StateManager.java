package com.jacstuff.simplecalculator.calculator;

import com.jacstuff.simplecalculator.calculator.operators.Operator;
import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.calculator.state.CalcState;
import com.jacstuff.simplecalculator.calculator.state.ErrorState;
import com.jacstuff.simplecalculator.calculator.state.FirstNumberState;
import com.jacstuff.simplecalculator.calculator.state.OperatorState;
import com.jacstuff.simplecalculator.calculator.state.ResultState;
import com.jacstuff.simplecalculator.calculator.state.SecondNumberState;
import com.jacstuff.simplecalculator.calculator.state.State;

import java.util.HashMap;

public class StateManager {

    private final OperandString operandStr1, operandStr2, resultOperand;
    private HashMap<State, CalcState> calculatorStates;
    public CalcState currentState;
    private final Calculator calculator;


    public StateManager(Calculator calculator, OperandString operandStr1, OperandString operandStr2, OperandString resultOperand){
        this.calculator = calculator;
        this.operandStr1 = operandStr1;
        this.operandStr2 = operandStr2;
        this.resultOperand = resultOperand;
        setupStateMap();
        setState(State.FIRST_NUMBER);
    }


    private void setupStateMap(){
        if(calculatorStates != null) {
            return;
        }
        calculatorStates = new HashMap<>();
        registerState(State.FIRST_NUMBER, new FirstNumberState(operandStr1, operandStr2));
        registerState(State.OPERATOR, new OperatorState(operandStr2));
        registerState(State.SECOND_NUMBER, new SecondNumberState(operandStr2));
        registerState(State.ERROR, new ErrorState());
        registerState(State.RESULT, new ResultState(operandStr1, operandStr2, resultOperand));
    }


    private void registerState(State key, CalcState calcState){
        calculatorStates.put(key, calcState);
        calcState.setStateManager(this);
        calcState.setCalculatorActions(calculator.getCalculatorActions());
    }


    public void setState(State stateName){
        currentState = calculatorStates.get(stateName);
        CalcState calcState = calculatorStates.get(stateName);
        if(calcState != null){
            currentState = calcState;
            currentState.init();
        }
    }


    public void updateDisplay(String str){
        calculator.updateDisplay(str);
    }


    public void setOperatorState(Operator operator){
        currentState.setOperator(operator);
    }


    public void assignOperator(Operator operator){
        calculator.assignOperator(operator);
    }


    public Operator getPreviousOperator(){
        return calculator.getPreviousOperator();
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
