package com.jacstuff.simplecalculator.actions;

public class ClearAction extends  AbstractAction implements ButtonAction {
    public void process(){
        calculator.clear();
    }
}
