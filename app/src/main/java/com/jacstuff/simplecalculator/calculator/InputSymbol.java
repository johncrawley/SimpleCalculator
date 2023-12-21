package com.jacstuff.simplecalculator.calculator;

import com.jacstuff.simplecalculator.calculator.operators.Add;
import com.jacstuff.simplecalculator.calculator.operators.Cosine;
import com.jacstuff.simplecalculator.calculator.operators.Divide;
import com.jacstuff.simplecalculator.calculator.operators.Multiply;
import com.jacstuff.simplecalculator.calculator.operators.Operator;
import com.jacstuff.simplecalculator.calculator.operators.PercentOf;
import com.jacstuff.simplecalculator.calculator.operators.PowerOf;
import com.jacstuff.simplecalculator.calculator.operators.Sine;
import com.jacstuff.simplecalculator.calculator.operators.SquareRoot;
import com.jacstuff.simplecalculator.calculator.operators.Subtract;
import com.jacstuff.simplecalculator.calculator.operators.Tan;

import java.util.function.Consumer;

public enum InputSymbol {

    PLUS(Add.class),
    MINUS(Subtract.class),
    MULTIPLY(Multiply.class),
    DIVIDE(Divide.class),
    POWER_OF(PowerOf.class),
    SQUARE_ROOT(SquareRoot.class),
    PERCENT_OF(PercentOf.class),
    SINE(Sine.class),
    COSINE(Cosine.class),
    TAN(Tan.class),

    CLEAR(StateManager::clear),
    BACKSPACE(StateManager::backSpace),
    DECIMAL(StateManager::addDecimal),
    EQUALS(StateManager::evaluate),
    CHANGE_SIGN(StateManager::changeSign),
    SET_MEMORY(StateManager::saveNumberToMemory),
    RECALL_MEMORY(StateManager::recallNumberFromMemory),
    _1(1),
    _2(2),
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _7(7),
    _8(8),
    _9(9),
    _0(0),
    _PI(c -> c.setNumber(Math.PI));


    private final Consumer<Calculator> consumer;


    InputSymbol(Consumer<StateManager> consumer){
        this.consumer = c -> {
            consumer.accept(c.getStateManager());
        };
    }


    InputSymbol(Class <? extends Operator> operatorClass) {
        this.consumer = calculator -> calculator.setOperator(operatorClass);
    }


    InputSymbol(int digit) {
        this.consumer = calculator -> calculator.getStateManager().addDigit(digit);
    }
    

    public void process(Calculator calculator){
        consumer.accept(calculator);
    }
}
