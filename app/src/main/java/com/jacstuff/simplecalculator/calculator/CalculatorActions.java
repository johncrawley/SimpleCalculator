package com.jacstuff.simplecalculator.calculator;


import android.widget.TextView;

import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;
import com.jacstuff.simplecalculator.state.State;

import java.math.BigDecimal;

public class CalculatorActions {


    private Operator operator;
    private TextView textView;
    private OperandString numberStr1;
    private OperandString numberStr2;
    private OperandString resultStr;
    private Calculator calculator;
    private Memory memory;


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


    public boolean evaluateAndDisplay(){

        try {
            resultStr.set(execute());
        }
        catch(ArithmeticException e){
            resultStr.setAndDisplayError();
            calculator.setState(State.ERROR);
            return false;
        }
        return true;
    }


    private BigDecimal execute(){
        BigDecimal number1 = createBigDecimalFrom(numberStr1);
        BigDecimal number2 = createBigDecimalFrom(numberStr2);
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
        numberStr2.init();
        resultStr.init();
        setDisplay("0");
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
