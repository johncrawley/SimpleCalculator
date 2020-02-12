package com.jacstuff.simplecalculator.actions;

public class ChangeSignAction extends AbstractAction implements ButtonAction {

    public void process(){
        calculator.changeSign();
    }
}
