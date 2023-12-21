package com.jacstuff.simplecalculator.calculator.memory;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsMemoryImpl implements Memory {

    private final SharedPreferences pref;
    private final String prefName = "MEM";

    public PrefsMemoryImpl(Context context){
        pref = context.getSharedPreferences("SimpleCalDefault", Context.MODE_PRIVATE);
    }

    @Override
    public void saveNumber(String str){
        save(str);
    }


    @Override
    public String recallNumber(){
        return pref.getString(prefName, "0");
    }


    private void save( String value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(prefName, value);
        editor.apply();
    }

}
