package com.jacstuff.simplecalculator.actions;

import com.jacstuff.simplecalculator.Calculator;

public class ChangeSignAction extends AbstractAction implements ButtonAction {

    public void process(){
        calculator.changeSign();
    }
}
