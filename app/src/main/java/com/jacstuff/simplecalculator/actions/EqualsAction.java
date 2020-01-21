package com.jacstuff.simplecalculator.actions;

import com.jacstuff.simplecalculator.Calculator;
import com.jacstuff.simplecalculator.actions.ButtonAction;

public class EqualsAction extends AbstractAction implements ButtonAction {

    public void process(){
        calculator.evaluate();
    }
}
