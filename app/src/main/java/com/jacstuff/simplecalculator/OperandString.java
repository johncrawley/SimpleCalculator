package com.jacstuff.simplecalculator;

public class OperandString {

    private String value;
    private final String INITIAL_VALUE = "0";
    private final String ZERO = "0";
    private final String DECIMAL = ".";
    private boolean isPositive = true;
    private final String MINUS = "-";
    private UpdatableDisplay updatableDisplay;

    public OperandString(UpdatableDisplay updatableDisplay){
        this.updatableDisplay = updatableDisplay;
        this.init();
    }


    public void init(){
        this.value = INITIAL_VALUE;
        this.isPositive = true;
    }


    public String get(){

        if(isPositive || value.equals(ZERO)){
            return value;
        }
        return MINUS + value;
    }

    public String getLegalStr(){

        String str = get();
        if(str.endsWith(DECIMAL)){
            str += "0";
        }
        return str;
    }


    public void set(String str){
        this.isPositive = !str.startsWith(MINUS);
        updateValueAndDisplay(stripLeadingMinus(str));
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
        set(operandString.get());
    }

}
