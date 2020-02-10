package com.jacstuff.simplecalculator.actions;

public class BackspaceAction extends AbstractAction implements ButtonAction {

    public void process(){
        calculator.backSpace();
    }
}
