package com.example.user.ppking_android_stopwatch;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button Left , Right;
    private TextView clock;
    private boolean isRunning;
    private int counter;
    private Timer timer;
    private ClockTask clockTask;
    private UIHandler uiHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uiHandler = new UIHandler();

        timer = new Timer();

        Left = (Button)findViewById(R.id.doLeft);
        Right = (Button)findViewById(R.id.doRight);

        clock = (TextView)findViewById(R.id.clock);
    }

    @Override
    public void finish() {
        if(timer!=null){
            timer.purge();
            timer.cancel();
            timer = null;
        }
        super.finish();
    }

    public void doLeft(View v){

    }
    public void doRight(View v){
        isRunning = !isRunning;
        Right.setText(isRunning?"Stop":"Start");
        Left.setText(isRunning?"Lap":"ReSet");
        if(isRunning){
            doStart();
        }else{
            doStop();
        }
    }
    public void doStart(){
        clockTask = new ClockTask();
        timer.schedule(clockTask,10,10);
    }
    public void doStop(){
        clockTask.cancel();
    }
    public void doReset(){

    }
    public void doLap(){

    }

    public class ClockTask extends TimerTask{
        @Override
        public void run() {
            counter ++ ;
            uiHandler.sendEmptyMessage(0);
        }
    }
    public class UIHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            clock.setText(""+counter);
        }
    }

}
