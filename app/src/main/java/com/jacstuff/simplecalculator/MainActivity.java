package com.jacstuff.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jacstuff.simplecalculator.actions.ActionsFactory;
import com.jacstuff.simplecalculator.actions.ButtonAction;

import static com.jacstuff.simplecalculator.actions.ActionsFactory.Action.BACKSPACE;
import static com.jacstuff.simplecalculator.actions.ActionsFactory.Action.CHANGE_SIGN;
import static com.jacstuff.simplecalculator.actions.ActionsFactory.Action.CLEAR;
import static com.jacstuff.simplecalculator.actions.ActionsFactory.Action.DECIMAL;
import static com.jacstuff.simplecalculator.actions.ActionsFactory.Action.DIVIDE;
import static com.jacstuff.simplecalculator.actions.ActionsFactory.Action.EQUALS;
import static com.jacstuff.simplecalculator.actions.ActionsFactory.Action.MULTIPLY;
import static com.jacstuff.simplecalculator.actions.ActionsFactory.Action.PERCENT;
import static com.jacstuff.simplecalculator.actions.ActionsFactory.Action.PLUS;
import static com.jacstuff.simplecalculator.actions.ActionsFactory.Action.POWER;
import static com.jacstuff.simplecalculator.actions.ActionsFactory.Action.ROOT;
import static com.jacstuff.simplecalculator.actions.ActionsFactory.Action.SUBTRACT;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Map<Integer, ButtonAction> actions;
    private Calculator calculator;

    private ActionsFactory actionsFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCalculator();

        actionsFactory = new ActionsFactory(getApplicationContext(), calculator);
        setupViews();

    }


    private void initCalculator(){
        TextView display = findViewById(R.id.textView);
        calculator = new Calculator(display);
    }

    private void setupViews(){
        actions = new HashMap<>();
        mapNumberButtons();
        mapOperatorButtons();
        mapOtherButtons();
   }

   private void mapNumberButtons(){
       mapButtonToAction(R.id.button0, R.string.symbol_0, 0);
       mapButtonToAction(R.id.button1, R.string.symbol_1, 1);
       mapButtonToAction(R.id.button2, R.string.symbol_2, 2);
       mapButtonToAction(R.id.button3, R.string.symbol_3, 3);
       mapButtonToAction(R.id.button4, R.string.symbol_4,4);
       mapButtonToAction(R.id.button5, R.string.symbol_5, 5);
       mapButtonToAction(R.id.button6, R.string.symbol_6, 6);
       mapButtonToAction(R.id.button7, R.string.symbol_7, 7);
       mapButtonToAction(R.id.button8, R.string.symbol_8, 8);
       mapButtonToAction(R.id.button9, R.string.symbol_9, 9);
   }

    private void mapOperatorButtons(){

        mapButtonToAction(R.id.buttonPlus,       PLUS, R.string.symbol_plus);
        mapButtonToAction(R.id.buttonMinus,      SUBTRACT, R.string.symbol_minus);
        mapButtonToAction(R.id.buttonMultiply,   MULTIPLY, R.string.symbol_multiply);
        mapButtonToAction(R.id.buttonDivide,     DIVIDE, R.string.symbol_divide);
        mapButtonToAction(R.id.buttonPow,        POWER, R.string.symbol_power);
        mapButtonToAction(R.id.buttonRoot,       ROOT, R.string.symbol_root);
        mapButtonToAction(R.id.buttonPercent,    PERCENT, R.string.symbol_percentage);
    }

    private void mapOtherButtons(){
        mapButtonToAction(R.id.buttonClear, CLEAR, R.string.symbol_cancel);
        mapButtonToAction(R.id.buttonBackspace, BACKSPACE, R.string.symbol_backspace);
        mapButtonToAction(R.id.buttonDecimal, DECIMAL, R.string.symbol_decimal);
        mapButtonToAction(R.id.buttonEquals, EQUALS, R.string.symbol_equals);
        mapButtonToAction(R.id.buttonChangeSign, CHANGE_SIGN, R.string.symbol_change_sign);
    }

    private void mapButtonToAction(int viewId, ActionsFactory.Action actionType, int symbolId){
        ButtonAction buttonAction = actionsFactory.create(actionType, symbolId);
        mapActionAndAssignListener(viewId, buttonAction);
    }
    private void mapButtonToAction(int viewId, int symbolId, int digit){
        ButtonAction buttonAction = actionsFactory.create(symbolId, digit);
        mapActionAndAssignListener(viewId, buttonAction);
    }

    private void mapActionAndAssignListener(int viewId, ButtonAction buttonAction){
        actions.put(viewId, buttonAction);
        findViewById(viewId).setOnClickListener(this);
    }

    public void onClick(View view){
        int id = view.getId();
        if(actions.containsKey(id)) {
            actions.get(id).process();
        }
    }


}


//TODO: use double instead of int
// TODO: if operator on 2nd number state, eval sum, store in 1st num and set operator

// TODO: create CalculatorNumber class, will represent the 1st and 2nd numbers in the calculator
//      The calculator will have access to these 2 instances, as well as the various states
//      This should mean less duplication for methods clearing numbers, inverting signs,
//      as well as somewhere to put state for adding the decimal place
