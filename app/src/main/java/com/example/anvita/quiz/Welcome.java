package com.example.anvita.quiz;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.app.Activity;
import android.content.res.Resources;


public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        isTabletDevice(getResources());

        ImageView start = (ImageView) findViewById(R.id.img_start);

        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                final GlobalClass globalClass = (GlobalClass) getApplicationContext();
                globalClass.initialize();

                Intent i = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(i);
            }
        });
    }

    private void isTabletDevice(Resources resources) {
        int screenLayout = resources.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        boolean isScreenLarge = (screenLayout == Configuration.SCREENLAYOUT_SIZE_LARGE);
        boolean isScreenPhone = (screenLayout == Configuration.SCREENLAYOUT_SIZE_NORMAL);
        boolean isScreenXlarge = (screenLayout == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        Log.d("tablet"," is "+isScreenLarge);
        Log.d(" large tablet"," is "+isScreenXlarge);
        Log.d("phone"," is "+isScreenPhone);
        //return (isScreenLarge || isScreenXlarge);

        if(isScreenPhone ==  true)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

}
