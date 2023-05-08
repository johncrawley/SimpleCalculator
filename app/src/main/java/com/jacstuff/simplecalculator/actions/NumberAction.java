package com.jacstuff.simplecalculator.actions;


public class NumberAction extends AbstractAction implements ButtonAction {

    private final double number;


    public NumberAction(double number){
        this.number = number;
    }

    public void process(){
        calculator.setNumber(number);
    }
}