package com.jacstuff.simplecalculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.jacstuff.simplecalculator.calculator.Calculator;
import com.jacstuff.simplecalculator.calculator.InputSymbol;
import com.jacstuff.simplecalculator.calculator.memory.PrefsMemoryImpl;
import com.jacstuff.simplecalculator.calculator.display.UpdatableDisplay;
import com.jacstuff.simplecalculator.calculator.display.UpdatableDisplayImpl;
import com.jacstuff.simplecalculator.view.MainViewModel;


public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initCalculator();
        setupButtons();
    }


    private void initCalculator(){
        TextView displayTextView = findViewById(R.id.outputDisplayText);
        UpdatableDisplay display = new UpdatableDisplayImpl(displayTextView, viewModel);
        PrefsMemoryImpl prefsMemoryImpl = new PrefsMemoryImpl(getApplicationContext());
        calculator = new Calculator(prefsMemoryImpl, display);
    }

    private void setupButtons(){
        setup(R.id.buttonPlus,  InputSymbol.PLUS );
        setup(R.id.buttonMinus,   InputSymbol.MINUS);
        setup(R.id.buttonMultiply, InputSymbol.MULTIPLY);
        setup(R.id.buttonDivide,  InputSymbol.DIVIDE);
        setup(R.id.buttonPow,    InputSymbol.POWER_OF);
        setup(R.id.buttonRoot,    InputSymbol.SQUARE_ROOT);
        setup(R.id.buttonPercent, InputSymbol.PERCENT_OF);

        setup(R.id.buttonClear,       InputSymbol.CLEAR);
        setup(R.id.buttonBackspace,   InputSymbol.BACKSPACE);
        setup(R.id.buttonDecimal,     InputSymbol.DECIMAL);
        setup(R.id.buttonEquals,      InputSymbol.EQUALS);
        setup(R.id.buttonChangeSign,  InputSymbol.CHANGE_SIGN);

        setup(R.id.buttonMemorySet,   InputSymbol.SET_MEMORY);
        setup(R.id.buttonMemoryRecall,InputSymbol.RECALL_MEMORY);

        setup(R.id.buttonSine, InputSymbol.SINE);
        setup(R.id.buttonCosine, InputSymbol.COSINE);
        setup(R.id.buttonTan, InputSymbol.TAN);

        setup(R.id.button0, InputSymbol._0);
        setup(R.id.button1, InputSymbol._1);
        setup(R.id.button2, InputSymbol._2);
        setup(R.id.button3, InputSymbol._3);
        setup(R.id.button4, InputSymbol._4);
        setup(R.id.button5, InputSymbol._5);
        setup(R.id.button6, InputSymbol._6);
        setup(R.id.button7, InputSymbol._7);
        setup(R.id.button8, InputSymbol._8);
        setup(R.id.button9, InputSymbol._9);
        setup(R.id.buttonPi,InputSymbol._PI);
    }


    private void setup(int buttonId, InputSymbol inputSymbol){
        findViewById(buttonId).setOnClickListener(v -> calculator.process(inputSymbol));
    }
}
