package com.jacstuff.simplecalculator.calculator;


import android.widget.TextView;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;
import com.jacstuff.simplecalculator.state.State;

import java.math.BigDecimal;

public class CalculatorActions {


    private Operator operator;
    private final TextView textView;
    private final OperandString numberOperand1;
    private final OperandString numberOperand2;
    private final OperandString resultOperand;
    private final Calculator calculator;
    private final Memory memory;


    CalculatorActions(Calculator calculator, Memory memory, TextView textView){
        this.calculator = calculator;
        this.numberOperand1 = calculator.getNumberStr1();
        this.numberOperand2 = calculator.getNumberStr2();
        this.resultOperand = calculator.getResultStr();
        this.memory = memory;
        this.textView = textView;
    }


    public void setAndDisplay(Operator operator){
        this.operator = operator;
        operator.onLoad();
        setDisplay(operator.getSymbol());
    }


    public void copyResultToFirstNumber(){
        numberOperand1.getValueFrom(resultOperand);
    }


    public boolean evaluateAndDisplay() {
        return evalAndDisplay(false);
    }


    public boolean evaluatePercentageAndDisplay(){
        return evalAndDisplay(true);
    }


    private boolean evalAndDisplay(boolean isCalculatingPercentage){
        try {
            resultOperand.set(execute(isCalculatingPercentage));
            textView.setText(resultOperand.get());
        }
        catch(ArithmeticException e){
            resultOperand.setAndDisplayError();
            textView.setText(resultOperand.get());
            calculator.setState(State.ERROR);
            return false;
        }
        return true;
    }


    private BigDecimal execute(boolean isCalculatingPercentage){
        BigDecimal number1 = createBigDecimalFrom(numberOperand1);
        BigDecimal number2 = createBigDecimalFrom(numberOperand2);
        operator.setCalculatingPercentage(isCalculatingPercentage);
        return operator.execute(number1, number2).stripTrailingZeros();
    }


    public void displayResult(){
        setDisplay(resultOperand.get());
    }


    private BigDecimal createBigDecimalFrom(OperandString operandString){
        return new BigDecimal(operandString.getLegalStr());
    }


    public void clearNumbersAndDisplayText(){
        numberOperand1.init();
        // numberStr2.init();
        resultOperand.init();
        setDisplay("0");
    }


    public void clearSecondNumberString(){
        numberOperand2.init();
    }


    private void setDisplay(String text){
        textView.setText(text);
    }


    public void saveNumberToMemory(OperandString operandString){
        memory.saveNumber(operandString.get());
    }


    public void recallNumberFromMemory(OperandString operandString){
        String recalledStr = memory.recallNumber();
        operandString.set(recalledStr);
    }

}
