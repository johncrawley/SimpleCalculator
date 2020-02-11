package com.jacstuff.simplecalculator;

import android.util.Log;
import android.widget.TextView;

import com.jacstuff.simplecalculator.actions.operators.Operator;

import java.math.BigDecimal;

public class CalculatorActions {


    private Operator operator;
    private TextView textView;
    private OperandString operandString1;
    private OperandString operandString2;
    private OperandString resultStr;


    CalculatorActions(OperandString operandString1, OperandString operandString2, OperandString resultStr, TextView textView){

        this.operandString1 = operandString1;
        this.operandString2 = operandString2;
        this.resultStr = resultStr;
        this.textView = textView;
    }


    public void setAndDisplayOperatorAction(Operator operator){
        this.operator = operator;
        operator.onLoad();
        setDisplay(operator.getSymbol());
    }


    public void displayError(){
        setDisplay("ERR");
    }



    public void copyResultToFirstNumber(){
        operandString1.getValueFrom(resultStr);
    }

    private void log(String msg){
        Log.i("CalculatorActions", msg);
    }

    public void evaluateAndDisplay(){

        BigDecimal number1 = createBigDecimalFrom(operandString1);
        BigDecimal number2 = createBigDecimalFrom(operandString2);

        BigDecimal resultBig = operator.execute(number1, number2).stripTrailingZeros();
        resultStr.set(resultBig.toPlainString());
        operandString1.getValueFrom(resultStr);
    }

    public void displayResult(){
        setDisplay(resultStr.get());
    }

    private BigDecimal createBigDecimalFrom(OperandString operandString){
        return new BigDecimal(operandString.getLegalStr());
    }


    public void clearNumbersAndDisplayText(){
        operandString1.init();
        operandString2.init();

        setDisplay("0");
    }

    private void setDisplay(String text){
        textView.setText(text);
    }


}
