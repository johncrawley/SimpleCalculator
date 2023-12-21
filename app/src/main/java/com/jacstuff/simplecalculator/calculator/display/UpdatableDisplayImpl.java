package com.jacstuff.simplecalculator.calculator.display;

import android.widget.TextView;

import com.jacstuff.simplecalculator.view.MainViewModel;

public class UpdatableDisplayImpl implements UpdatableDisplay {

    private final TextView textView;
    private final MainViewModel viewModel;

    public UpdatableDisplayImpl(TextView textView, MainViewModel viewModel){
        this.textView = textView;
        this.viewModel = viewModel;
        textView.setText(viewModel.displayStr);
    }

    public void set(String str){
        viewModel.displayStr = str;
        textView.setText(str);
    }


    public String getContents(){
        return textView.getText().toString();
    }
}
