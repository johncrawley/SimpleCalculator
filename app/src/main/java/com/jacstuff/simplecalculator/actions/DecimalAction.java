package com.jacstuff.simplecalculator.actions;


public class DecimalAction extends AbstractAction implements ButtonAction {

    @Override
    public void process() {
        calculator.addDecimal();
    }

}
