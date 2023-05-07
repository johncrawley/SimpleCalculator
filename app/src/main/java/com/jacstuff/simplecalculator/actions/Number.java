package com.jacstuff.simplecalculator.actions;


public class Number extends AbstractAction implements ButtonAction {
    private final int digit;

    public Number(int digit){
        this.digit = digit;
    }

    public void process(){
        calculator.addDigit(digit);
    }


}
