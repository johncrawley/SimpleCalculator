package com.jacstuff.simplecalculator.actions;


public class DigitAction extends AbstractAction implements ButtonAction {

    private final int digit;


    public DigitAction(int digit){
        this.digit = digit;
    }


    public void process(){
        calculator.addDigit(digit);
    }
}
