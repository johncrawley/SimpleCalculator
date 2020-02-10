package com.jacstuff.simplecalculator.state;

import com.jacstuff.simplecalculator.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;

public class FirstNumberState extends AbstractState implements CalcState {


    OperandString firstOperandString;

    public FirstNumberState(OperandString firstOperandString){
        this.firstOperandString = firstOperandString;
    }

    @Override
    public void init(){

    }
    @Override
    public void setOperator(Operator operator){

        calculatorActions.setAndDisplayOperatorAction(operator);
        if(operator.hasSingleInput()){
            calculator.setState(State.SECOND_NUMBER);
            calculator.evaluate();
            return;
        }
        calculator.setState(State.OPERATOR);
    }

    @Override
    public void changeSign() {
        firstOperandString.negate();
    }

    @Override
    public void addDecimal() {
        firstOperandString.addDecimal();
    }

    @Override
    public void addDigit(int digit) {
        firstOperandString.addDigit(digit);
    }

    @Override
    public void clear() {
        calculatorActions.clearNumbersAndDisplayText();
    }

    @Override
    public void evaluate() {
    }


    @Override
    public void deleteDigit() {
        firstOperandString.deleteDigit();
    }
}
