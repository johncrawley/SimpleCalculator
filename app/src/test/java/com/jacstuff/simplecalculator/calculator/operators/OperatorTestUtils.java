package com.jacstuff.simplecalculator.calculator.operators;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

import java.math.BigDecimal;

public class OperatorTestUtils {

    public static void assertOperation(Operator operator, String str1, String str2, String expected){
        if(str2.isEmpty()){
            str2 = "0";
        }
        assertEquals(expected, operator.execute( new BigDecimal(str1), new BigDecimal(str2)).stripTrailingZeros().toPlainString());
    }


    public static void assertOperatorThrows(Operator operator, String str1, String str2, Class <? extends Exception> e){
        Assert.assertThrows(e, ()-> operator.execute( new BigDecimal(str1), new BigDecimal(str2)));
    }
}
