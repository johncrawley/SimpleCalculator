package com.jacstuff.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.jacstuff.simplecalculator.actions.BackspaceAction;
import com.jacstuff.simplecalculator.actions.ButtonAction;
import com.jacstuff.simplecalculator.actions.ChangeSignAction;
import com.jacstuff.simplecalculator.actions.ClearAction;
import com.jacstuff.simplecalculator.actions.DecimalAction;
import com.jacstuff.simplecalculator.actions.EqualsAction;
import com.jacstuff.simplecalculator.actions.MemoryRecallAction;
import com.jacstuff.simplecalculator.actions.MemorySetAction;
import com.jacstuff.simplecalculator.actions.Number;
import com.jacstuff.simplecalculator.actions.operators.Add;
import com.jacstuff.simplecalculator.actions.operators.Divide;
import com.jacstuff.simplecalculator.actions.operators.Multiply;
import com.jacstuff.simplecalculator.actions.operators.PercentOf;
import com.jacstuff.simplecalculator.actions.operators.PowerOf;
import com.jacstuff.simplecalculator.actions.operators.SquareRoot;
import com.jacstuff.simplecalculator.actions.operators.Subtract;
import com.jacstuff.simplecalculator.calculator.Calculator;


import java.math.MathContext;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    MathContext mc = new MathContext(9);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCalculator();
        setupViews();
    }


    private void initCalculator(){
        TextView display = findViewById(R.id.textView);
        calculator = new Calculator(getApplicationContext(), display);
    }


    private void setupViews(){
        setupButtons();
   }


    private void setupButtons(){
        setupButton(R.id.buttonPlus,    new Add(mc),        R.string.symbol_add);
        setupButton(R.id.buttonMinus,   new Subtract(mc),   R.string.symbol_subtract);
        setupButton(R.id.buttonMultiply,new Multiply(mc),   R.string.symbol_multiply);
        setupButton(R.id.buttonDivide,  new Divide(mc),     R.string.symbol_divide);
        setupButton(R.id.buttonPow,     new PowerOf(mc),    R.string.symbol_power);
        setupButton(R.id.buttonRoot,    new SquareRoot(mc), R.string.symbol_root);
        setupButton(R.id.buttonPercent, new PercentOf(mc),  R.string.symbol_percentage);

        setupButton(R.id.buttonClear,       new ClearAction(),      R.string.symbol_cancel);
        setupButton(R.id.buttonBackspace,   new BackspaceAction(),  R.string.symbol_backspace);
        setupButton(R.id.buttonDecimal,     new DecimalAction(),    R.string.symbol_decimal);
        setupButton(R.id.buttonEquals,      new EqualsAction(),     R.string.symbol_equals);
        setupButton(R.id.buttonChangeSign,  new ChangeSignAction(), R.string.symbol_change_sign);

        setupButton(R.id.buttonMemorySet,   new MemorySetAction(),      R.string.symbol_memory_set);
        setupButton(R.id.buttonMemoryRecall,new MemoryRecallAction(),   R.string.symbol_memory_recall);

        setupButton(R.id.button0, new Number(0), R.string.symbol_0);
        setupButton(R.id.button1, new Number(1), R.string.symbol_1);
        setupButton(R.id.button2, new Number(2),R.string.symbol_2);
        setupButton(R.id.button3, new Number(3), R.string.symbol_3);
        setupButton(R.id.button4, new Number(4), R.string.symbol_4);
        setupButton(R.id.button5, new Number(5), R.string.symbol_5);
        setupButton(R.id.button6, new Number(6), R.string.symbol_6 );
        setupButton(R.id.button7, new Number(7), R.string.symbol_7);
        setupButton(R.id.button8, new Number(8), R.string.symbol_8);
        setupButton(R.id.button9, new Number(9), R.string.symbol_9);
    }


    private void setupButton(int buttonId, ButtonAction buttonAction, int symbolId){
        buttonAction.setSymbol(getString(symbolId));
        buttonAction.setCalculator(calculator);
        findViewById(buttonId).setOnClickListener(v -> buttonAction.process());
    }
}
