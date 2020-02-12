package com.jacstuff.simplecalculator.actions;

import android.content.Context;

import com.jacstuff.simplecalculator.calculator.Calculator;
import com.jacstuff.simplecalculator.actions.operators.Divide;
import com.jacstuff.simplecalculator.actions.operators.Multiply;
import com.jacstuff.simplecalculator.actions.operators.PercentOf;
import com.jacstuff.simplecalculator.actions.operators.Plus;
import com.jacstuff.simplecalculator.actions.operators.PowerOf;
import com.jacstuff.simplecalculator.actions.operators.SquareRoot;
import com.jacstuff.simplecalculator.actions.operators.Subtract;

import java.math.MathContext;

public class ActionsFactory {


    public enum Action { DIVIDE, MULTIPLY, PERCENT, PLUS, POWER, ROOT, SUBTRACT, EQUALS, CHANGE_SIGN, CLEAR, BACKSPACE, DECIMAL, MEMORY_SET, MEMORY_RECALL}
    private Context context;
    private Calculator calculator;
    private MathContext mc;

    public ActionsFactory(Context context, Calculator calculator){
        this.context = context;
        this.calculator = calculator;
        mc = new MathContext(9);
    }


    public ButtonAction create(Action actionType, int symbolId){

        ButtonAction action = createAction(actionType);
            return assignSymbolAndCalculator(action, symbolId);

    }


    public ButtonAction create(int symbolId, int digit){

        ButtonAction action = new Number(digit);
        return assignSymbolAndCalculator(action, symbolId);

    }



    private ButtonAction createAction(Action actionType){

        switch(actionType){

            case PLUS:      return new Plus(mc);
            case SUBTRACT:  return new Subtract(mc);
            case DIVIDE:    return new Divide(mc);
            case MULTIPLY:  return new Multiply(mc);
            case PERCENT:   return new PercentOf(mc);
            case POWER:     return new PowerOf(mc);
            case ROOT:      return new SquareRoot(mc);

            case EQUALS:    return new EqualsAction();
            case CLEAR:     return new ClearAction();
            case CHANGE_SIGN: return new ChangeSignAction();

            case DECIMAL: return new DecimalAction();
            case BACKSPACE: return new BackspaceAction();

            case MEMORY_SET: return new MemorySetAction();
            case MEMORY_RECALL: return new MemoryRecallAction();

            default : return null;

        }
    }

    private ButtonAction assignSymbolAndCalculator(ButtonAction action, int symbolId){
        action.setCalculator(calculator);
        action.setSymbol(context.getString(symbolId));
        return action;
    }

}
