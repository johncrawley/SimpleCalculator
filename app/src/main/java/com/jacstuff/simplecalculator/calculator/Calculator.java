package com.jacstuff.simplecalculator.calculator;


import com.jacstuff.simplecalculator.actions.operators.Add;
import com.jacstuff.simplecalculator.actions.operators.Cosine;
import com.jacstuff.simplecalculator.actions.operators.Divide;
import com.jacstuff.simplecalculator.actions.operators.Multiply;
import com.jacstuff.simplecalculator.actions.operators.PercentOf;
import com.jacstuff.simplecalculator.actions.operators.PowerOf;
import com.jacstuff.simplecalculator.actions.operators.Sine;
import com.jacstuff.simplecalculator.actions.operators.SquareRoot;
import com.jacstuff.simplecalculator.actions.operators.Subtract;
import com.jacstuff.simplecalculator.actions.operators.Tan;
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

import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    private final CalculatorActions calculatorActions;
    private final UpdatableDisplay updatableDisplay;
    private OperandString operandStr1, operandStr2, resultOperand;
    private Operator operator, previousOperator;
    public State currentCalculatorStateName = State.FIRST_NUMBER;
    public HashMap<State, CalcState> calculatorStates;
    public CalcState currentState;
    private final MathContext mc = new MathContext(13);
    private Map<Class<? extends Operator>, Operator> operatorMap;


    public Calculator(Memory memory, UpdatableDisplay updatableDisplay){
        this.updatableDisplay = updatableDisplay;
        initOperands();
        registerOperatorActions();
        calculatorActions = new CalculatorActions(this, memory);
        setupStates();
        assignState();

    }

    private void registerOperatorActions(){
        operatorMap = new HashMap<>();
        register(new Add());
        register(new Subtract());
        register(new Multiply());
        register(new Divide());
        register(new PowerOf());
        register(new SquareRoot());
        register(new PercentOf());
        register(new Sine());
        register(new Cosine());
        register(new Tan());
    }


    private void register(Operator operator){
        operatorMap.put(operator.getClass(), operator);
        operator.setMathContext(mc);
    }


    OperandString getResultStr(){ return resultOperand;}


    OperandString getOperandStr1(){
        return operandStr1;
    }

    OperandString getOperandStr2(){
        return operandStr2;
    }


    public void process(InputSymbol inputSymbol){
        inputSymbol.process(this);
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


    Operator getOperator(){
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


    void setOperator(Class<? extends Operator> operatorClass){
        setOperatorFromButton(operatorMap.get(operatorClass));
    }


    public void setOperatorFromButton(Operator operator){
        assignOperator(operator);
        setOperatorState(operator);
    }


    public void assignOperator(Operator operator){
        previousOperator = this.operator;
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
