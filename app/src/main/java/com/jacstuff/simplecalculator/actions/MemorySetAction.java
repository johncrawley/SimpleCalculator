package com.jacstuff.simplecalculator.actions;

import com.jacstuff.simplecalculator.calculator.Memory;

public class MemorySetAction extends AbstractAction implements ButtonAction {

    @Override
    public void process() {
        calculator.saveNumberToMemory();
    }
}
