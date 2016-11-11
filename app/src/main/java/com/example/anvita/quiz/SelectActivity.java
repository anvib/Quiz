package com.example.anvita.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.app.Dialog;
import android.widget.ListView;
import java.util.*;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;


public class SelectActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        final boolean tablet = isDeviceTablet(getResources());
        final boolean phone = isDevicePhone(getResources());

        if(phone)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Log.d("check","in selectactivity class");

        final ListView list = (ListView) findViewById(R.id.lv_options);

        String arroptions[] = new String[3];
        arroptions[0] = "Addition";
        arroptions[1] = "Subtraction";
        arroptions[2] = "Multiplication";

        ArrayList<String> optionList = new ArrayList<String>();
        optionList.addAll(Arrays.asList(arroptions) );

       ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.list, optionList);

       list.setAdapter(listAdapter);

        list.setOnItemClickListener(new  OnItemClickListener() {
            public void onItemClick(AdapterView<?> adp, View view, int id, long l) {
                String option =(String) (list.getItemAtPosition(id));

                if(id == 0)
                {
                    final GlobalClass globalClass = (GlobalClass) getApplicationContext();
                    globalClass.initialize();

                    Log.d("counter on add", "counter is "+globalClass.getCounter());


                    if(phone)
                    {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        Intent i = new Intent(getApplicationContext(), Addition.class);
                        startActivity(i);
                    }

                    if(tablet)
                    {
                        Intent x = new Intent(getApplicationContext(), Addition.class);
                        startActivity(x);
                    }

                }else if(id == 1){

                    final GlobalClass globalClass = (GlobalClass) getApplicationContext();
                    globalClass.initialize();

                    Log.d("counter on sub", "counter is "+globalClass.getCounter());

                    if(phone)
                    {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        Intent i = new Intent(getApplicationContext(), Subtraction.class);
                        startActivity(i);
                    }

                    if(tablet)
                    {
                        Intent x = new Intent(getApplicationContext(), Subtraction.class);
                        startActivity(x);
                    }

                }else if(id == 2){
                    final GlobalClass globalClass = (GlobalClass) getApplicationContext();
                    globalClass.initialize();

                    Log.d("counter on mul", "counter is "+globalClass.getCounter());

                    if(phone)
                    {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        Intent i = new Intent(getApplicationContext(), Multiplication.class);
                        startActivity(i);
                    }

                    if(tablet)
                    {
                        Intent x = new Intent(getApplicationContext(), Multiplication.class);
                        startActivity(x);
                    }

                }
            }
        });
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
