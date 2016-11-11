package com.example.anvita.quiz;

/**
 * Created by anvita on 9/30/16.
 */

import android.app.Application;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GlobalClass extends Application {

    private int counter;
    private int[] answerarr;
    public TextView[] btnarr;
    public int[] color;

    public int num1;
    public int num2;

    public void initialize(){
        counter = 0;
        answerarr = new int[10];
        btnarr = new TextView[10];
        color = new int[10];

        for(int i = 0; i < 10; i++){
            color[i] = 2;
        }
    }

    public int getCounter(){
        return counter;
    }

    public void setAnswer(int ans){
        answerarr[counter] = ans;
        counter = counter + 1;
    }

    public int[] getAnswer(){
        return answerarr;
    }



}
