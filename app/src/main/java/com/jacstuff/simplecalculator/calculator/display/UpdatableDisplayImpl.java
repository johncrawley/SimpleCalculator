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

    public void update(String str){
        viewModel.displayStr = str;
        textView.setText(str);
    }
}
