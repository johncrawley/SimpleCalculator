package com.jacstuff.simplecalculator.calculator;

import android.content.Context;
import android.content.SharedPreferences;

import com.jacstuff.simplecalculator.calculator.display.OperandString;

public class Memory {

    public enum Slot { FIRST_NUMBER, SECOND_NUMBER, RESULT, OPERATON, MEM}

    private Context context;
    SharedPreferences pref;

    public Memory(Context context){
        this.context = context;

        pref = context.getSharedPreferences("SimpleCalDefault", Context.MODE_PRIVATE);
    }

    public void saveNumber(String str){
        save(Slot.MEM.toString(), str);
    }

    public String recallNumber(){
        return pref.getString(Slot.MEM.toString(), "0");
    }



    private void save(String tag, String value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(tag, value);
        editor.apply();
    }

}
