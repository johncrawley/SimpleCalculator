package com.jacstuff.simplecalculator;

import com.jacstuff.simplecalculator.calculator.display.UpdatableDisplay;

public class MockDisplay implements UpdatableDisplay {

    private String contents;

    @Override
    public String getContents() {
        return this.contents;
    }

    @Override
    public void set(String str) {
        contents = str;
    }
}