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


import java.math.MathContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    private final CalculatorActions calculatorActions;
    private final UpdatableDisplay updatableDisplay;
    private OperandString operandStr1, operandStr2, resultOperand;
    private Operator operator, previousOperator;
    private final StateManager stateManager;
    private final MathContext mc = new MathContext(13);
    private Map<Class<? extends Operator>, Operator> operatorMap;


    public Calculator(Memory memory, UpdatableDisplay updatableDisplay){
        this.updatableDisplay = updatableDisplay;
        initOperands();
        registerOperatorActions();
        calculatorActions = new CalculatorActions(this, memory);
        stateManager = new StateManager(this, operandStr1, operandStr2, resultOperand);
        stateManager.assignState();
    }


    public void process(InputSymbol inputSymbol){
        inputSymbol.process(this);
    }


    private void registerOperatorActions(){
        operatorMap = new HashMap<>();
        Arrays.asList(new Add(),
                new Subtract(),
                new Multiply(),
                new Divide(),
                new PowerOf(),
                new SquareRoot(),
                new PercentOf(),
                new Sine(),
                new Cosine(),
                new Tan()
                ).forEach(this::register);
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


    CalculatorActions getCalculatorActions(){
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


    void updateDisplay(String str){
        updatableDisplay.set(str);
    }


    Operator getOperator(){
        return operator;
    }


    Operator getPreviousOperator(){
        return previousOperator;
    }


    void setOperator(Class<? extends Operator> operatorClass){
        setOperatorFromButton(operatorMap.get(operatorClass));
    }


    void setOperatorFromButton(Operator operator){
        assignOperator(operator);
        stateManager.setOperatorState(operator);
    }


    void assignOperator(Operator operator){
        previousOperator = this.operator;
        this.operator = operator;
    }

    StateManager getStateManager(){
        return stateManager;
    }


}
