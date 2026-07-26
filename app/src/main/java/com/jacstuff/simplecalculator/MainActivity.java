package com.jacstuff.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupInsets();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initCalculator();
        setupButtons();
    }


    private void setupInsets() {
        View mainLayout = findViewById(R.id.mainLayout);
        if(mainLayout == null){
            System.out.println("MainActivity.setupInsets() main layout could not be found!");
            return;
        }
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
            var systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupCalculatorButtons() {
        GridLayout buttonGrid = findViewById(R.id.button_grid);
        String[][] buttons = {
                {"C", "±", "%", "÷"},
                {"7", "8", "9", "×"},
                {"4", "5", "6", "-"},
                {"1", "2", "3", "+"},
                {"0", ".", "="}          // 0 will span 2 columns
        };

        int row = 0, col = 0;

        for (var buttonRow : buttons) {
            for (var text : buttonRow) {
                var button = (MaterialButton) getLayoutInflater()
                        .inflate(R.layout.input_button, buttonGrid, false);

                button.setText(text);

                var params = (GridLayout.LayoutParams) button.getLayoutParams();
                params.width = 0;
                params.height = 0;
                params.columnSpec = GridLayout.spec(col, 1f);   // 1f = weight
                params.rowSpec = GridLayout.spec(row, 1f);

                button.setLayoutParams(params);
                //button.setOnClickListener(v -> calculator.process(text));

                buttonGrid.addView(button);

                col++;
                if (col >= 4) {   // move to next row
                    col = 0;
                    row++;
                }
            }
        }
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
