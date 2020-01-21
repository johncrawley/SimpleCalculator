package com.jacstuff.simplecalculator;

import android.util.Log;
import android.widget.TextView;

import com.jacstuff.simplecalculator.actions.operators.Operator;

public class CalculatorActions {

    private int firstNumber;
    private int secondNumber;
    private Operator operator;
    private int result;
    private TextView textView;
    private String displayText;


    public CalculatorActions(TextView textView){

        this.textView = textView;
    }


    public void setAndDisplayOperatorAction(Operator operator){
        this.operator = operator;
        operator.onLoad();
        setDisplay(operator.getSymbol());

    }


    public void addDigitToFirstNumber(int digit){
        firstNumber = addDigitAndUpdateDisplay(firstNumber, digit);
    }

    public void addDigitToSecondNumber(int digit){
        secondNumber = addDigitAndUpdateDisplay(secondNumber, digit);
    }

    public void displayError(){
        displayText = "ERR";
        updateDisplay();
    }


    public void clearSecondNumber(){
        secondNumber = 0;
    }

    private void updateDisplay(){
        textView.setText(displayText);
    }
    public void appendOperatorToDisplay(){
        displayText += " " + operator.getSymbol();
    }

    public void appendSecondNumberToDisplay(){

        displayText += " " + secondNumber;

    }


    private int addDigitAndUpdateDisplay(int number, int newDigit){
        log("Entered addDigitAndUpdateDisplay()");
        number = addDigit(number, newDigit);
        setDisplay(number);
        return number;
    }



    public void assignResultToFirstNumber(){
        firstNumber = result;
    }

    private int addDigit(int number, int digit){
        return (number * 10) + digit;
    }

    private void log(String msg){

        Log.i("CalculatorActions", msg);

    }

    public void evaluateAndDisplay(){
        Log.i("CalculatorActions", "Entered evaluateAndDisplay()");
        result = operator.execute(firstNumber, secondNumber);
        setDisplay(result);
        firstNumber = result;
    }

    private void setDisplay(int value){
        Log.i("CalculatorActions", "Entered setDisplay(), value: " + value + " state: ");
        displayText = "" + value;
        textView.setText(displayText);
    }

    public void clearNumbers(){
        firstNumber = 0;
        secondNumber = 0;
        result = 0;
        setDisplay(0);
    }


    private void setDisplay(String text){
        displayText = text;
        textView.setText(displayText);
    }

    public void changeSignOfFirstNumber(){
        firstNumber = changeSign(firstNumber);
        setDisplay(firstNumber);
    }
    public void changeSignOfSecondNumber(){
        secondNumber = changeSign(secondNumber);
        setDisplay(secondNumber);
    }

    public void changeSignOfResult(){
        result = result *-1 ;
        setDisplay(result);
    }


    private int changeSign(int number){
        return number * -1;
    }

}
