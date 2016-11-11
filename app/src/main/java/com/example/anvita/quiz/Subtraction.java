package com.example.anvita.quiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.lang.String;
import android.widget.ImageView;
import android.os.Handler;
import android.graphics.drawable.*;
import android.graphics.drawable.shapes.*;
import android.graphics.Color;
import android.view.Gravity;
import android.R.color.*;
import android.graphics.Color.*;
import android.os.CountDownTimer;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Subtraction extends AppCompatActivity {

    int diff;
    GlobalClass globalClass;
    String diffnum = "";

    String ansGlobal = "";
    boolean tablet;
    boolean phone;

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tablet = isDeviceTablet(getResources());
        phone = isDevicePhone(getResources());

        if(phone){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(R.layout.activity_subtraction);
        }else if(tablet){
            Display getScreen = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            int orientation = getScreen.getRotation();

            if(orientation == 0 || orientation == 2)
                setContentView(R.layout.activity_subtraction);
            else if(orientation == 1 || orientation == 3)
                setContentView(R.layout.activity_subtraction_landscape);
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        globalClass = (GlobalClass) getApplicationContext();

        globalClass.btnarr[0] = (TextView) findViewById(R.id.btn_Q1);
        globalClass.btnarr[1] = (TextView) findViewById(R.id.btn_Q2);
        globalClass.btnarr[2] = (TextView) findViewById(R.id.btn_Q3);
        globalClass.btnarr[3] = (TextView) findViewById(R.id.btn_Q4);
        globalClass.btnarr[4] = (TextView) findViewById(R.id.btn_Q5);
        globalClass.btnarr[5] = (TextView) findViewById(R.id.btn_Q6);
        globalClass.btnarr[6] = (TextView) findViewById(R.id.btn_Q7);
        globalClass.btnarr[7] = (TextView) findViewById(R.id.btn_Q8);
        globalClass.btnarr[8] = (TextView) findViewById(R.id.btn_Q9);
        globalClass.btnarr[9] = (TextView) findViewById(R.id.btn_Q10);


        for (int i = 0; i < 10; i++) {
            if (globalClass.color[i] == 1) {
                globalClass.btnarr[i].setBackgroundColor(0xff00ff00);
            } else if (globalClass.color[i] == 0) {
                globalClass.btnarr[i].setBackgroundColor(0xffff0000);
            } else {

            }
        }

        final TextView tv_timer = (TextView) findViewById(R.id.tv_counter_sub);

        timer = new CountDownTimer(6000, 1000) {

            int check = globalClass.getCounter();


            public void onTick(long secs) {
                tv_timer.setText(secs / 1000 + "");
            }

            public void onFinish() {

                int checknow = globalClass.getCounter();

                if (checknow == check) {

                    globalClass.color[checknow] = 0;

                    globalClass.setAnswer(0);

                    globalClass.num1 = 0;
                    globalClass.num2 = 0;

                    final ImageView wrong = (ImageView) findViewById(R.id.imageWrong);
                    wrong.setVisibility(View.VISIBLE);

                    if (checknow == 9) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            Log.d("exception", "E");
                        }


                        Intent x = new Intent(getApplicationContext(), Final.class);
                        startActivity(x);
                    } else {
                        Intent x = new Intent(getApplicationContext(), Subtraction.class);
                        startActivity(x);
                    }
                }
            }
        }.start();

        Random r = new Random();

        int n1 = 0, n2 = 0;

        if(globalClass.num1 == 0 && globalClass.num2 == 0){
            n1 = r.nextInt(10 - 0);
            n2 = r.nextInt((n1 + 1) - 0);

            globalClass.num1 = n1;
            globalClass.num2 = n2;
        }
        else{
            n1 = globalClass.num1;
            n2 = globalClass.num2;
        }

        diff = n1 - n2;

        final ImageView correct = (ImageView) findViewById(R.id.imageCorrect);
        final ImageView wrong = (ImageView) findViewById(R.id.imageWrong);

        correct.setVisibility(View.INVISIBLE);
        wrong.setVisibility(View.INVISIBLE);

        final TextView num1 = (TextView) findViewById(R.id.tv_subnum1);
        num1.setText(String.valueOf(n1));

        final TextView num2 = (TextView) findViewById(R.id.tv_subnum2);
        num2.setText(String.valueOf(n2));

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("ok","home clicked");

        switch (item.getItemId()) {
            case android.R.id.home:

                if(tablet){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Quit");
                    timer.cancel();
                    builder.setMessage("Do you want to Quit?").setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){

                            Intent x = new Intent(getApplicationContext(), SelectActivity.class);
                            startActivity(x);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            onResume();
                        }
                    }).show().getWindow().setLayout(850,400);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Quit");
                    timer.cancel();
                    builder.setMessage("Do you want to Quit?").setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){

                            Intent x = new Intent(getApplicationContext(), SelectActivity.class);
                            startActivity(x);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            onResume();
                        }
                    }).show().getWindow().setLayout(1050,600);
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        timer.cancel();
        Intent i = new Intent(getBaseContext(), SelectActivity.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        timer.cancel();
        super.onPause();

    }
    @Override
    protected void onStop() {
        timer.cancel();
        super.onStop();

    }
    @Override
    protected void onResume() {
        timer.start();
        super.onResume();
    }

    public void buttonPress(View v) {
        Button b = (Button) v;
        String buttonNum = b.getText().toString();

        if (!buttonNum.equals("Next")) {
            diffnum = diffnum + buttonNum;

            ansGlobal = diffnum;

            final EditText ansview = (EditText) findViewById(R.id.et_sub_answer);
            ansview.setText(diffnum);

            int ans = Integer.valueOf(diffnum);

            if (diffnum.length() == 2) {
                if (ans == diff) {
                    int qnum = globalClass.getCounter();
                    globalClass.color[qnum] = 1;

                    globalClass.setAnswer(1);

                    globalClass.num1 = 0;
                    globalClass.num2 = 0;

                    final ImageView correct = (ImageView) findViewById(R.id.imageCorrect);
                    correct.setVisibility(View.VISIBLE);
                } else {
                    int qnum = globalClass.getCounter();
                    globalClass.color[qnum] = 0;

                    globalClass.setAnswer(0);

                    globalClass.num1 = 0;
                    globalClass.num2 = 0;

                    final ImageView wrong = (ImageView) findViewById(R.id.imageWrong);
                    wrong.setVisibility(View.VISIBLE);
                }

                int check = globalClass.getCounter();

                if (check < 10) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(getApplicationContext(), Subtraction.class);
                            startActivity(i);
                        }
                    }, 500);
                } else {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent x = new Intent(getApplicationContext(), Final.class);
                            startActivity(x);
                        }
                    }, 500);
                }

            } else {
                if (ans == diff) {
                    int qnum = globalClass.getCounter();
                    globalClass.color[qnum] = 1;

                    globalClass.setAnswer(1);

                    globalClass.num1 = 0;
                    globalClass.num2 = 0;

                    final ImageView correct = (ImageView) findViewById(R.id.imageCorrect);
                    correct.setVisibility(View.VISIBLE);

                    int check = globalClass.getCounter();

                    if (check < 10) {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent i = new Intent(getApplicationContext(), Subtraction.class);
                                startActivity(i);
                            }
                        }, 500);
                    } else {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent x = new Intent(getApplicationContext(), Final.class);
                                startActivity(x);
                            }
                        }, 500);
                    }

                }
            }
        }else{          //
            int c = globalClass.getCounter();
            if(diffnum.length() == 0){

                globalClass.setAnswer(0);

                globalClass.num1 = 0;
                globalClass.num2 = 0;

                final ImageView wrong = (ImageView) findViewById(R.id.imageWrong);
                wrong.setVisibility(View.VISIBLE);

                globalClass.color[c] = 0;

                if(c < 9)
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent x = new Intent(getApplicationContext(), Subtraction.class);
                            startActivity(x);
                        }
                    }, 500);
                }
                else{
                    Intent x = new Intent(getApplicationContext(), Final.class);
                    startActivity(x);

                }
            }else{
                int ans = Integer.valueOf(diffnum);
                if(ans != diff) {

                    Toast.makeText(this, "Answer saved", Toast.LENGTH_SHORT).show();
                    globalClass.setAnswer(0);

                    globalClass.num1 = 0;
                    globalClass.num2 = 0;

                    globalClass.color[c] = 0;

                    onStop();

                    if(c < 9)
                    {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent x = new Intent(getApplicationContext(), Subtraction.class);
                                startActivity(x);
                            }
                        }, 500);
                    }
                    else{
                        Intent x = new Intent(getApplicationContext(), Final.class);
                        startActivity(x);

                    }
                }
            }

        }
    }
}

