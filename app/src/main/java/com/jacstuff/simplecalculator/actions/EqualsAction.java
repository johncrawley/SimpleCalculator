package com.jacstuff.simplecalculator.actions;

public class EqualsAction extends AbstractAction implements ButtonAction {

    public void process(){
        calculator.evaluate();
    }
}
