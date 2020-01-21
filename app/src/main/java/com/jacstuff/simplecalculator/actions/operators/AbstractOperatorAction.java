package com.jacstuff.simplecalculator.actions.operators;

import com.jacstuff.simplecalculator.Calculator;
import com.jacstuff.simplecalculator.actions.AbstractAction;
import com.jacstuff.simplecalculator.actions.ButtonAction;

public abstract class AbstractOperatorAction extends AbstractAction implements ButtonAction, Operator {

    public void process(){
        calculator.setOperator(this);
    }

    public int execute(){
        return 0;
    }
    public void onLoad(){}
    public boolean hasSingleInput(){return false;}


}
