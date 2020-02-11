package com.jacstuff.simplecalculator;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class OperandStringTest {


    private OperandString operandStr;
    private final String INITIAL_VALUE = "0";
    private final String DECIMAL = ".";
    private final String MINUS = "-";

    @Before
    public void setup()
    {
        operandStr = new OperandString(new UpdatableDisplay() {
            @Override
            public void update(String str) {

            }
        });
    }

    @Test
    public void canSetDigit(){

        assertEquals("String should have a default value of zero", "0", operandStr.get());

        List<String> inputs = Arrays.asList("10", "-1", "-1000", "-10.101010", "1000000");
        for(String input: inputs){
            assertInputWasAssigned(operandStr, input);
        }
    }

    private void assertInputWasAssigned(OperandString operandString, String str){
        operandString.set(str);
        assertEquals("String does not match expected value", str, operandString.get());
    }



    @Test
    public void canAddDigitsAndDecimal(){
        assertEquals(INITIAL_VALUE, operandStr.get());
        Map<Integer, String> expectedMap = new HashMap<>();
        expectedMap.put(1, "1");
        expectedMap.put(2,"12");
        expectedMap.put(3,"123");

        for(Integer input : expectedMap.keySet()){
            operandStr.addDigit(input);
            assertEquals(expectedMap.get(input), operandStr.get());
        }

    }

    @Test
    public void canAddDecimal(){
        int number = 1;
        String expectedStr = INITIAL_VALUE + DECIMAL;
        operandStr.addDecimal();
        assertOperand(expectedStr);
        operandStr.addDigit(number);
        expectedStr += number;
        assertOperand(expectedStr);

        operandStr.addDecimal(); // should ignore 2nd decimal
    }

    private void assertOperand(String expectedStr){
        assertEquals(expectedStr, operandStr.get());
    }

    @Test
    public void ignoresSecondDecimal(){

        int number = 4;
        operandStr.addDigit(number);
        operandStr.addDecimal();
        String expectedStr = "" + number + DECIMAL;
        assertOperand(expectedStr);
        operandStr.addDecimal();
        assertOperand(expectedStr);

    }


    @Test
    public void canDeleteDigits(){
        List<Integer> numbers = Arrays.asList(4,5,6,7,8);
        String expectedStr = createFromNumbers(numbers);
        addDigitsToOperandStr(numbers);
        assertOperand(expectedStr);

        assertDeletion("4567");
        assertDeletion("456");
        assertDeletion("45");
        assertDeletion("4");
        assertDeletion("0");
        assertDeletion("0");

    }
    private void addDigitsToOperandStr(List<Integer> numbers){
        for(Integer number: numbers){
            operandStr.addDigit(number);
        }
    }

    private String createFromNumbers(List<Integer> numbers){
        StringBuilder str = new StringBuilder();
        for(Integer number : numbers){
            str.append(number);
        }
        return str.toString();
    }

    private void assertDeletion(String expectedStr){
        operandStr.deleteDigit();
        assertOperand(expectedStr);
    }


    @Test
    public void canNegate(){
        int number = 4;
        operandStr.addDigit(number);
        operandStr.negate();
        assertOperand(MINUS + number);
        operandStr.negate();
        assertOperand("" + number);

        String negativeNumber = "-10";
        String positiveNumber = negativeNumber.substring(1);
        operandStr.set(negativeNumber);
        assertOperand(negativeNumber);
        operandStr.negate();
        assertOperand(positiveNumber);
        operandStr.negate();
        assertOperand(negativeNumber);

    }


    @Test
    public void canResetState(){

        operandStr.set("123");
        operandStr.addDigit(4);
        assertOperand("1234");
        operandStr.init();
        assertOperand(INITIAL_VALUE);

    }


    @Test
    public void canGetSafeString(){
        String initialValue = "123";
        operandStr.set(initialValue);
        operandStr.addDecimal();
        String expected = initialValue + DECIMAL;
        assertOperand(expected);

        String expectedSafe = expected + "0";
        assertEquals(expectedSafe, operandStr.getLegalStr());


    }

}
