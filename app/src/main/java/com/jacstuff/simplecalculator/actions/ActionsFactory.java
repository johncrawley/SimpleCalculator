package com.jacstuff.simplecalculator.actions;

import android.content.Context;
import android.util.Log;

import com.jacstuff.simplecalculator.Calculator;
import com.jacstuff.simplecalculator.actions.operators.Divide;
import com.jacstuff.simplecalculator.actions.operators.Multiply;
import com.jacstuff.simplecalculator.actions.operators.PercentOf;
import com.jacstuff.simplecalculator.actions.operators.Plus;
import com.jacstuff.simplecalculator.actions.operators.PowerOf;
import com.jacstuff.simplecalculator.actions.operators.SquareRoot;
import com.jacstuff.simplecalculator.actions.operators.Subtract;

public class ActionsFactory {


    public enum Action { DIVIDE, MULTIPLY, PERCENT, PLUS, POWER, ROOT, SUBTRACT, EQUALS, CHANGE_SIGN, CLEAR, NUMBER};
    private Context context;
    private Calculator calculator;

    public ActionsFactory(Context context, Calculator calculator){
        this.context = context;
        if(calculator == null){
            Log.i("ActionsFactory", "constructor: calculator param is null!");
        }
        this.calculator = calculator;
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

            case PLUS:      return new Plus();
            case SUBTRACT:  return new Subtract();
            case DIVIDE:    return new Divide();
            case MULTIPLY:  return new Multiply();
            case PERCENT:   return new PercentOf();
            case POWER:     return new PowerOf();
            case ROOT:      return new SquareRoot();
            case EQUALS:    return new EqualsAction();
            case CLEAR:     return new ClearAction();
            case CHANGE_SIGN: return new ChangeSignAction();

            default : return new ClearAction();

        }
    }

    private ButtonAction assignSymbolAndCalculator(ButtonAction action, int symbolId){
        if(this.calculator == null){
            Log.i("ActionsFactory", "assignSymbolAndCalculator() : calculator is null!");
        }else{
            Log.i("ActionsFactory", "assignSymbolAndCalculator() : calculator appears to be ok!");
        }
        action.setCalculator(calculator);
        action.setSymbol(context.getString(symbolId));
        return action;
    }

}
