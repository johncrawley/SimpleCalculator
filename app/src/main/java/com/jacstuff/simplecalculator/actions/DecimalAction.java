package com.jacstuff.simplecalculator.actions;


import com.jacstuff.simplecalculator.Calculator;
import com.jacstuff.simplecalculator.actions.ButtonAction;

public class DecimalAction extends AbstractAction implements ButtonAction {

    @Override
    public void process() {
        calculator.addDecimal();
    }

}
