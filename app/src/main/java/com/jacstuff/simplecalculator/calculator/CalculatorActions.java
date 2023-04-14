package com.jacstuff.simplecalculator.calculator;


import android.widget.TextView;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;
import com.jacstuff.simplecalculator.state.State;

import java.math.BigDecimal;

public class CalculatorActions {


    private Operator operator;
    private final TextView textView;
    private final OperandString numberStr1;
    private final OperandString numberStr2;
    private final OperandString resultStr;
    private final Calculator calculator;
    private final Memory memory;


    CalculatorActions(Calculator calculator, Memory memory, TextView textView){
        this.calculator = calculator;
        this.numberStr1 = calculator.getNumberStr1();
        this.numberStr2 = calculator.getNumberStr2();
        this.resultStr = calculator.getResultStr();
        this.memory = memory;
        this.textView = textView;
    }


    public void setAndDisplay(Operator operator){
        this.operator = operator;
        operator.onLoad();
        setDisplay(operator.getSymbol());
    }


    public void copyResultToFirstNumber(){
        numberStr1.getValueFrom(resultStr);
    }


    public boolean evaluateAndDisplay() {
        return evalAndDisplay(false);
    }


    public boolean evaluatePercentageAndDisplay(){
        return evalAndDisplay(true);
    }


    private boolean evalAndDisplay(boolean isCalculatingPercentage){
        try {
            resultStr.set(execute(isCalculatingPercentage));
        }
        catch(ArithmeticException e){
            resultStr.setAndDisplayError();
            calculator.setState(State.ERROR);
            return false;
        }
        return true;
    }


    private BigDecimal execute(boolean isCalculatingPercentage){
        BigDecimal number1 = createBigDecimalFrom(numberStr1);
        BigDecimal number2 = createBigDecimalFrom(numberStr2);
        operator.setCalculatingPercentage(isCalculatingPercentage);
        return operator.execute(number1, number2).stripTrailingZeros();
    }


    public void displayResult(){
        setDisplay(resultStr.get());
    }


    private BigDecimal createBigDecimalFrom(OperandString operandString){
        return new BigDecimal(operandString.getLegalStr());
    }


    public void clearNumbersAndDisplayText(){
        numberStr1.init();
        // numberStr2.init();
        resultStr.init();
        setDisplay("0");
    }


    public void clearSecondNumberString(){
        numberStr2.init();
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
