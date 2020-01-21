package com.jacstuff.simplecalculator.actions;

import com.jacstuff.simplecalculator.Calculator;

public class ClearAction extends  AbstractAction implements ButtonAction {
    public void process(){
        calculator.clear();
    }
}
