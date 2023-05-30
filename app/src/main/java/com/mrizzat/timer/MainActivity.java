package com.mrizzat.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int second=0;
    private boolean running = false;
    private TextView txtvTimer;
    private Button btnStart;
    private Button btnStop;
    private Button btnLap;
    private Button btnReset;
    private TextView txtvLaps;
    private String laps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkInstance(savedInstanceState);

        getSupportActionBar().setTitle("Timer");
        setupViews();
        timer();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running=true;
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running=false;
                second=0;
                laps="";
                txtvLaps.setText("");
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running= false;
            }
        });
        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laps+=txtvTimer.getText().toString()+"\n";
                txtvLaps.setText(laps);
            }
        });

    }


    private void checkInstance(Bundle savedInstanceState){
        if(savedInstanceState!=null) {
            second = savedInstanceState.getInt("SECONDS");
            running = savedInstanceState.getBoolean("RUNNING");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);
            outState.putInt("SECONDS",second);
            outState.putBoolean("RUNNING",running);
    }


    private  void timer(){

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int seconds = second%60;
                int minutes = second/60;
                int hours = second/3600;
                String sSeconds,sMinutes,sHours="";
                if(seconds<10){
                    sSeconds="0"+seconds;
                }else{
                    sSeconds=seconds+"";
                }
                if(minutes<10){
                    sMinutes="0"+minutes;
                }else{
                    sMinutes=minutes+"";
                }
                if(hours<10){
                    sHours="0"+hours;
                }else{
                    sHours=hours+"";
                }

                String time =  sHours+":"+sMinutes+":"+sSeconds;
                txtvTimer.setText(time);


                if(running){
                    second++;
                }
                handler.postDelayed(this,1000);
            }
        });


    }

    private  void setupViews(){

        laps="";
        txtvTimer = findViewById(R.id.Time);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnLap = findViewById(R.id.btnLap);
        btnReset = findViewById(R.id.btnReset);
        txtvLaps = findViewById(R.id.Laps);

    }





}