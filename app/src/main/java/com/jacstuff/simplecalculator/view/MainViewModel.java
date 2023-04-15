package com.jacstuff.simplecalculator.view;

import androidx.lifecycle.ViewModel;

import com.jacstuff.simplecalculator.calculator.display.OperandString;

public class MainViewModel extends ViewModel {

    public String display;
    public OperandString operandStr1;
    public OperandString operandStr2;
    public OperandString resultOperand;
}
