package com.example.anvita.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;
import android.app.Activity;

public class Final extends AppCompatActivity {

    int count = 0;
    boolean tablet;
    boolean phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        tablet = isDeviceTablet(getResources());
        phone = isDevicePhone(getResources());

        if(phone)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        final GlobalClass globalClass = (GlobalClass) getApplicationContext();

        int[] result = globalClass.getAnswer();

        int counter = globalClass.getCounter();

        for(int i = 0; i < counter; i++){
            if(result[i]==1)
                count = count + 1;
        }
        Log.d("final","done in final class");
    }


    @Override
    protected void onStart(){
        super.onStart();

        if(tablet){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Score");
            builder.setMessage("Your score is "+(count*10)+"% !").setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    Intent x = new Intent(getApplicationContext(), Welcome.class);
                    startActivity(x);
                }
            }).show().getWindow().setLayout(850,450);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Score");
            builder.setMessage("Your score is "+(count*10)+"% !").setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    Intent x = new Intent(getApplicationContext(), Welcome.class);
                    startActivity(x);
                }
            }).show().getWindow().setLayout(1050,650);
        }


    }

    private boolean isDeviceTablet(Resources resources) {
        int screen = resources.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        boolean isTablet = (screen == Configuration.SCREENLAYOUT_SIZE_LARGE);

        if(isTablet ==  true)
            return true;
        else
            return false;

    }

    private boolean isDevicePhone(Resources resources) {
        int screen = resources.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        boolean isPhone = (screen == Configuration.SCREENLAYOUT_SIZE_NORMAL);

        if(isPhone ==  true)
            return true;
        else
            return false;
    }

}

