package com.jacstuff.simplecalculator.calculator;



import com.jacstuff.simplecalculator.calculator.display.OperandString;
import com.jacstuff.simplecalculator.actions.operators.Operator;
import com.jacstuff.simplecalculator.state.State;
import com.jacstuff.simplecalculator.view.MainViewModel;

import java.math.BigDecimal;

public class CalculatorActions {


    private final OperandString resultOperand;
    private final Calculator calculator;
    private final MainViewModel viewModel;
    private final Memory memory;


    CalculatorActions(Calculator calculator, Memory memory, MainViewModel viewModel){
        this.calculator = calculator;
        this.viewModel = viewModel;
        this.resultOperand = calculator.getResultStr();
        this.memory = memory;
    }


    public void loadAndDisplayOperator(){
        Operator operator = calculator.getOperator();
        if(operator == null){
            return;
        }
        operator.onLoad();
        calculator.updateDisplay(operator.getSymbol());
    }


    public void copyResultToFirstNumber(){
        viewModel.operandStr1.setValueFrom(resultOperand);
    }


    public boolean evaluateAndDisplay() {
        return evalAndDisplay(false, calculator.getOperator());
    }


    public boolean evaluateUsingPreviousOperatorAndDisplayResult() {
        return evalAndDisplay(false, calculator.getPreviousOperator());
    }


    public boolean evaluatePercentageAndDisplay(){
        return evalAndDisplay(true, calculator.getPreviousOperator());
    }


    private boolean evalAndDisplay(boolean isCalculatingPercentage, Operator operator){
        try {
            resultOperand.set(execute(isCalculatingPercentage, operator));
            calculator.updateDisplay(resultOperand.get());
        }
        catch(ArithmeticException e){
            e.printStackTrace();
            resultOperand.setAndDisplayError();
            calculator.updateDisplay(resultOperand.get());
            calculator.setState(State.ERROR);
            return false;
        }
        return true;
    }


    private BigDecimal execute(boolean isCalculatingPercentage, Operator operator){
        BigDecimal number1 = createBigDecimalFrom(viewModel.operandStr1);
        BigDecimal number2 = createBigDecimalFrom(viewModel.operandStr2);
        operator.setCalculatingPercentage(isCalculatingPercentage);
        return operator.execute(number1, number2).stripTrailingZeros();
    }


    public void displayResult(){
        calculator.updateDisplay(resultOperand.get());
    }


    private BigDecimal createBigDecimalFrom(OperandString operandString){
        return new BigDecimal(operandString.getLegalStr());
    }


    public void clearNumbersAndDisplayText(){
        viewModel.operandStr1.init();
        resultOperand.init();
        calculator.updateDisplay("0");
    }


    public void clearSecondNumberString(){
        viewModel.operandStr2.init();
    }



    public void saveNumberToMemory(OperandString operandString){
        memory.saveNumber(operandString.get());
    }


    public void recallNumberFromMemory(OperandString operandString){
        String recalledStr = memory.recallNumber();
        operandString.set(recalledStr);
    }

}
