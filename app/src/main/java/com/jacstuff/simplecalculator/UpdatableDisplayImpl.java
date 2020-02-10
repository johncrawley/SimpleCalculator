package com.jacstuff.simplecalculator;

import android.widget.TextView;

public class UpdatableDisplayImpl implements UpdatableDisplay {

    private TextView textView;

    public UpdatableDisplayImpl(TextView textView){
        this.textView = textView;
    }

    public void update(String str){
        textView.setText(str);
    }
}
