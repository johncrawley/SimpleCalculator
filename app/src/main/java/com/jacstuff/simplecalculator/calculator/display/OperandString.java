package com.jacstuff.simplecalculator.calculator.display;

import java.math.BigDecimal;

public class OperandString {

    private String value;
    private final String INITIAL_VALUE = "0";
    private final String ZERO = "0";
    private final String DECIMAL = ".";
    private boolean isPositive = true;
    private final String MINUS = "-";
    private final String ERROR = "ERROR";
    private UpdatableDisplay updatableDisplay;
    private boolean showError;

    public OperandString(UpdatableDisplay updatableDisplay){
        this.updatableDisplay = updatableDisplay;
        this.init();
    }


    public void init(){
        this.value = INITIAL_VALUE;
        this.isPositive = true;
        this.showError = false;
    }


    public String get(){
        if(showError){
            return ERROR;
        }

        if(isPositive || value.equals(ZERO)){
            return value;
        }
        return MINUS + value;
    }


    public String getLegalStr(){
        if(showError){
            return ZERO;
        }
        String str = get();
        if(str.endsWith(DECIMAL)){
            str += ZERO;
        }
        return str;
    }


    public void set(String str){
        this.isPositive = !str.startsWith(MINUS);
        updateValueAndDisplay(stripLeadingMinus(str));
    }

    public void set(BigDecimal bigDecimal){
        String str = bigDecimal.toPlainString();
        set(str);
    }

    private String stripLeadingMinus(String str){
        return str.startsWith(MINUS) ? str.substring(1) : str;
    }


    private void updateValueAndDisplay(String str){
        this.value = str;
        updatableDisplay.update(get());
    }


    public void addDigit(int digit){
        if(value.equals(INITIAL_VALUE)){
            updateValueAndDisplay("" + digit);
            return;
        }
        updateValueAndDisplay(value + digit);
    }


    public void addDecimal(){
        if(value.contains(DECIMAL)){
            return;
        }
        updateValueAndDisplay( value + DECIMAL);

    }

    public void deleteDigit(){
        if(value.length() <= 1){
            value = INITIAL_VALUE;
            updateValueAndDisplay(INITIAL_VALUE);
            return;
        }
        updateValueAndDisplay(removeEndDigit(value));
    }

    private String removeEndDigit(String str){
        return str.substring(0, str.length()-1);
    }

    public void negate(){
        if(value.equals(ZERO)){
            return;
        }
        isPositive = !isPositive;
        updateValueAndDisplay(value);
    }


    public void getValueFrom(OperandString operandString){
        if(operandString.get().equals(ERROR)){
            set(ZERO);
            return;
        }
        set(operandString.get());
    }

    public void setAndDisplayError(){
        this.showError = true;
        updatableDisplay.update(ERROR);
    }

}
