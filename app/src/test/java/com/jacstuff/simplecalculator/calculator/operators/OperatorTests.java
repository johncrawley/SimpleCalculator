package com.jacstuff.simplecalculator.calculator.operators;

import static com.jacstuff.simplecalculator.calculator.operators.OperatorTestUtils.assertOperation;
import static com.jacstuff.simplecalculator.calculator.operators.OperatorTestUtils.assertOperatorThrows;


import org.junit.Test;

import java.math.MathContext;

public class OperatorTests {

    private MathContext mathContext;

    @Test
    public void canAddTwoNumbers(){
        Operator addOperator = setup(new Add());
        assertOperation(addOperator, "1", "1", "2");
        assertOperation(addOperator, "1.001", "-1.000", "0.001");
        assertOperation(addOperator, "-10", "-10", "-20");
    }


    @Test
    public void canSubtractOneNumberFromAnother(){
        Operator operator = setup(new Subtract());

        assertOperation(operator, "1", "1", "0");
        assertOperation(operator, "10", "12", "-2");
        assertOperation(operator, "0", "0", "0");
        assertOperation(operator, "0.5", "1", "-0.5");
        assertOperation(operator, "1000", "150", "850");
    }


    @Test
    public void canMultiplyTwoNumbers(){
        Operator operator = setup(new Multiply());

        assertOperation(operator, "1", "1", "1");
        assertOperation(operator, "1", "0", "0");

        assertOperation(operator, "1990", "0", "0");
        assertOperation(operator, "0.5", "0.5", "0.25");
        assertOperation(operator, "12", "12", "144");
    }


    @Test
    public void canDivideOneNumberByAnother(){
        Operator operator = setup(new Divide());
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
        Operator operator = setup(new SquareRoot());

        assertOperation(operator, "1", "", "1");
        assertOperation(operator, "4", "", "2");

        assertOperatorThrows(operator, "-1", "", NumberFormatException.class);
        assertOperatorThrows(operator, "-14", "", NumberFormatException.class);

        assertOperation(operator, "144", "", "12");
    }


    @Test
    public void canGetPowerOfANumber(){
        Operator operator = setup(new PowerOf());

        assertOperation(operator, "1", "0", "1");
        assertOperation(operator, "15", "0", "1");
        assertOperation(operator, "-2", "0", "1");

        assertOperation(operator, "1", "1", "1");
        assertOperation(operator, "1", "10", "1");

        assertOperation(operator, "2", "2", "4");
        assertOperation(operator, "2", "4", "16");
        assertOperation(operator, "2", "10", "1024");

        assertOperation(operator, "16", "0.5", "4");
        assertOperation(operator, "25", "0.5", "5");

        assertOperation(operator, "8", "-1", "0.125");
    }


    private Operator setup(Operator operator){
        if(mathContext == null){
            mathContext = new MathContext(14);
        }
        operator.setMathContext(mathContext);
        return operator;
    }
}
