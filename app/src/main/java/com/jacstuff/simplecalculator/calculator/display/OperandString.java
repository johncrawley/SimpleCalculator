package com.jacstuff.simplecalculator.calculator.display;

import com.jacstuff.simplecalculator.calculator.Calculator;

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
        String displayValue = value.length() > maxLength ? getTruncatedValue() : value;
        return isValuePositive() ? displayValue : MINUS + displayValue;
    }


    private String getTruncatedValue(){
        int decimalIndex = value.indexOf(".");
        if(decimalIndex == -1 || decimalIndex >= maxLength - 1){
            return getScientificStrFrom(value);
        }
        return cutOffSomeDecimalsFrom(value, decimalIndex);
    }


    private boolean isValuePositive(){
        return isPositive || value.equals(ZERO);
    }


    private String cutOffSomeDecimalsFrom(String str, int decimalIndex){
        String decimalPart = str.substring(decimalIndex + 1);
        int numberOfDecimalsToTruncate = str.length() - maxLength;
        int maxFractionDigits = decimalPart.length() - numberOfDecimalsToTruncate;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(maxFractionDigits);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        Double d = Double.valueOf(str);
        return df.format(d);
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
