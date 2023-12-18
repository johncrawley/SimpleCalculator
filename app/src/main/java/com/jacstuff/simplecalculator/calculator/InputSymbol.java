package com.jacstuff.simplecalculator.calculator;

import java.util.function.Consumer;

public enum InputSymbol {

    PLUS(c -> c.a),
    MINUS,
    MULTIPLY,
    DIVIDE,
    POWER_OF,
    SQUARE_ROOT,
    PERCENT_OF,
    SINE,
    COSINE,
    TAN,

    CLEAR(Calculator::clear),
    BACKSPACE(Calculator::backSpace),
    DECIMAL(Calculator::addDecimal),
    EQUALS(Calculator::evaluate),
    CHANGE_SIGN(Calculator::changeSign),
    SET_MEMORY(Calculator::saveNumberToMemory),
    RECALL_MEMORY(Calculator::recallNumberFromMemory),
    _1(c -> c.addDigit(1)),
    _2(c -> c.addDigit(2)),
    _3(c -> c.addDigit(3)),
    _4(c -> c.addDigit(4)),
    _5(c -> c.addDigit(5)),
    _6(c -> c.addDigit(6)),
    _7(c -> c.addDigit(7)),
    _8(c -> c.addDigit(8)),
    _9(c -> c.addDigit(9)),
    _0(c -> c.addDigit(0)),
    _PI(c -> c.addDigit(Math.PI)) ;


    private final Consumer<Calculator> consumer;

    InputSymbol(Consumer<Calculator> consumer){
        this.consumer = consumer;
    }

    public void process(Calculator calculator){
        consumer.accept(calculator);
    }
}
