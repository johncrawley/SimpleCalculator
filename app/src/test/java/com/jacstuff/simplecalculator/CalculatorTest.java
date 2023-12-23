package com.jacstuff.simplecalculator;


import static com.jacstuff.simplecalculator.calculator.InputSymbol.*;
import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import com.jacstuff.simplecalculator.calculator.Calculator;
import com.jacstuff.simplecalculator.calculator.InputSymbol;
import com.jacstuff.simplecalculator.calculator.display.UpdatableDisplay;
import com.jacstuff.simplecalculator.calculator.memory.Memory;

import java.util.Arrays;

public class CalculatorTest {

    private Calculator calculator;
    private UpdatableDisplay display;

    @Before
    public void init(){
        display = new MockDisplay();
        calculator = new Calculator(createMemory(), display);
    }


    private Memory createMemory(){
        return new Memory() {
            String str = "0";
            @Override
            public void saveNumber(String str) {
                this.str = str;
            }

            @Override
            public String recallNumber() {
                return str;
            }
        };
    }

    @Test
    public void canEnterNumbers(){
        assertDisplay("1", _1);
        assertDisplay("12", _2);
        assertDisplay("123", _3);
    }


    @Test
    public void canDeleteNumbers(){
        assertDisplay("5678", _5, _6, _7, _8);
        assertDisplay("567", BACKSPACE);
        assertDisplay("56", BACKSPACE);
        assertDisplay("5", BACKSPACE);
        assertDisplay("0", BACKSPACE);
        assertDisplay("0", BACKSPACE);
    }


    @Test
    public void addingNumbers(){
        assertDisplay("500", _5, _0, _0);
        assertDisplay("+", PLUS);
        assertDisplay("612", _6, _1, _2);
        assertDisplay("1112", EQUALS);

        assertDisplay("0", CLEAR);
    }


    @Test
    public void willShowErrorAfterDivideByZero(){
        assertDisplay("100", _1, _0, _0);
        assertDisplay("ERROR", DIVIDE, _0, EQUALS);
    }


    @Test
    public void canCalculatePowers(){
        assertDisplay("2", _2);
        assertDisplay("4", POWER_OF, _2, EQUALS);
        assertDisplay("16", POWER_OF, _2, EQUALS);

        assertDisplay("27", _3, POWER_OF, _3, EQUALS);

        //verifying that anything to the power of zero returns 1
        assertDisplay("1", _9, POWER_OF, _0, EQUALS);
        assertDisplay("1", _0, POWER_OF, _0, EQUALS);
        assertDisplay("1", _4, DECIMAL, _6, _8, POWER_OF, _0, EQUALS);
        assertDisplay("1", _6, _7, CHANGE_SIGN, POWER_OF, _0, EQUALS);
        assertDisplay("1", _3, _5, _8, POWER_OF, _0, EQUALS);
    }


    @Test
    public void canSaveAndRecallMemory(){
        assertDisplay("20", _2, _0);
        calculator.process(SET_MEMORY);
        assertThatDisplayShows("20");
        assertDisplay("0", CLEAR);
        assertDisplay("20", RECALL_MEMORY);
    }




    public void assertDisplay(String expected, InputSymbol... inputSymbols){
        Arrays.stream(inputSymbols).forEach(is -> calculator.process(is));
        assertThatDisplayShows(expected);
    }


    private void assertThatDisplayShows(String expected){
        assertEquals(expected, display.getContents());
    }
}
