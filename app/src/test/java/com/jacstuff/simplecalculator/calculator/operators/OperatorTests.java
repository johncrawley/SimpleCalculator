package com.jacstuff.simplecalculator.calculator.operators;

import static com.jacstuff.simplecalculator.calculator.operators.OperatorTestUtils.assertOperation;
import static com.jacstuff.simplecalculator.calculator.operators.OperatorTestUtils.assertOperatorThrows;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

import java.math.MathContext;

public class OperatorTests {

    private Operator addOperator;
    private MathContext mathContext;

    @Test
    public void canAddTwoNumbers(){
        addOperator = new Add();
        setMathContextOn(addOperator);
        assertOperation(addOperator, "1", "1", "2");
        assertOperation(addOperator, "1.001", "-1.000", "0.001");
        assertOperation(addOperator, "-10", "-10", "-20");
    }


    @Test
    public void canSubtractOneNumberFromAnother(){
        Operator subtractOperator = new Subtract();
        setMathContextOn(subtractOperator);

        assertOperation(subtractOperator, "1", "1", "0");
        assertOperation(addOperator, "10", "12", "-2");
        assertOperation(addOperator, "0", "0", "0");
        assertOperation(addOperator, "0.5", "1", "-0.5");
        assertOperation(addOperator, "1000", "150", "850");
    }


    @Test
    public void canMultiplyTwoNumbers(){
        Operator operator = new Multiply();
        setMathContextOn(operator);
        assertOperation(operator, "1", "1", "1");
        assertOperation(operator, "1", "0", "0");
        assertOperation(operator, "1990", "0", "0");
        assertOperation(operator, "0.5", "0.5", "0.25");
        assertOperation(operator, "12", "12", "144");
    }


    @Test
    public void canDivideOneNumberByAnother(){
        Operator operator = new Divide();
        setMathContextOn(operator);
        assertOperation(operator, "1", "1", "1");
        assertOperation(operator, "0", "1", "0");
        assertOperation(operator, "10", "5", "2");
        assertOperation(operator, "0.5", "0.25", "2");
        assertOperation(operator, "-100", "25", "-4");
        assertOperation(operator, "-100", "-25", "4");
        assertOperatorThrows(operator, "1", "0", ArithmeticException.class);
    }


    @Test
    public void canGetSquareRootOfANumber(){
        Operator operator = new SquareRoot();
        setMathContextOn(operator);
        assertOperation(operator, "1", "", "1.0000000000");
        assertOperation(operator, "4", "", "2.0000000000");
        assertOperatorThrows(operator, "-1", "", NumberFormatException.class);
        assertOperation(operator, "144", "", "12.0000000000");
    }


    private void setMathContextOn(Operator operator){
        if(mathContext == null){
            mathContext = new MathContext(14);
        }
        operator.setMathContext(mathContext);
    }
}
