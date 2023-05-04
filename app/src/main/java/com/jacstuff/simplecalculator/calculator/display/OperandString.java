package com.jacstuff.simplecalculator.calculator.display;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class OperandString {

    private String value;
    private final String INITIAL_VALUE = "0";
    private final String ZERO = "0";
    private final String DECIMAL = ".";
    private boolean isPositive = true;
    private final String MINUS = "-";
    private final String ERROR = "ERROR";
    private boolean showError;
    private final int maxLength;


    public OperandString(int maxLength){
        this.maxLength = maxLength;
        this.init();
    }


    public void init(){
        this.value = INITIAL_VALUE;
        this.isPositive = true;
        this.showError = false;
    }


    public boolean isAtMaxLength(){
        return value.length() >= maxLength;
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


    public String get(int maxLength){
        if(showError){
            return ERROR;
        }
        if(isPositive || value.equals(ZERO)){
            return value;
        }
        String displayStr =  MINUS + value;
        if(displayStr.length() <= maxLength){
            return displayStr;
        }
        int decimalIndex = displayStr.indexOf(".");
        if(decimalIndex == -1 || decimalIndex >= maxLength - 1){
            return getScientificStrFrom(displayStr);
        }
        return cutOffSomeDecimalsFrom(displayStr, decimalIndex, maxLength);
    }


    private String cutOffSomeDecimalsFrom(String str, int decimalIndex, int maxLength){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);

        return df.format(str);
    }


    private String getScientificStrFrom(String str){
        BigInteger bigInteger = new BigInteger(str);
        NumberFormat formatter = new DecimalFormat("0.######E0", DecimalFormatSymbols.getInstance(Locale.ROOT));
        return formatter.format(bigInteger);
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
        value = stripLeadingMinus(str);
    }


    public void set(BigDecimal bigDecimal){
        String str = bigDecimal.toPlainString();
        set(str);
    }


    private String stripLeadingMinus(String str){
        return str.startsWith(MINUS) ? str.substring(1) : str;
    }


    public void addDigit(int digit){
        if(value.equals(INITIAL_VALUE)){
            this.value = "" + digit;
            return;
        }
        value += digit;
    }


    public void addDecimal(){
        if(value.contains(DECIMAL)){
            return;
        }
        value += DECIMAL;
    }


    public void deleteDigit(){
        value = value.length() <= 1 ? INITIAL_VALUE : removeEndDigit(value);
    }


    private String removeEndDigit(String str){
        return str.substring(0, str.length()-1);
    }


    public void negate(){
        if(value.equals(ZERO)){
            return;
        }
        isPositive = !isPositive;

    }


    public void setValueFrom(OperandString operandString){
        if(operandString.get().equals(ERROR)){
            set(ZERO);
            return;
        }
        set(operandString.get());
    }


    public void setAndDisplayError(){
        this.showError = true;
        value = ERROR;
    }

}
