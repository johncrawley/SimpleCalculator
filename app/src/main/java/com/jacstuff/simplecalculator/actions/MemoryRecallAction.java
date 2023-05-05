package com.jacstuff.simplecalculator.actions;


public class MemoryRecallAction extends AbstractAction implements ButtonAction {

    @Override
    public void process() {
        calculator.recallNumberFromMemory();
    }
}
