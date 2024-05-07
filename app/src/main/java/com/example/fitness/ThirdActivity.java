package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {

    String buttonValue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextView;
    private boolean MTimeRunning;
    private long MTimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        buttonValue = intent.getStringExtra("value");

        int intValue = Integer.valueOf(buttonValue);

        switch (intValue) {
            case 1:
                setContentView(R.layout.activity_bow);
                View bowView = findViewById(R.id.main);
                inflateAndFindViews(bowView);
                break;
            case 2:
                setContentView(R.layout.activity_bridge);
                View bridgeView = findViewById(R.id.main);
                inflateAndFindViews(bridgeView);
                break;
            case 3:
                setContentView(R.layout.activity_chair);
                View chairView = findViewById(R.id.main);
                inflateAndFindViews(chairView);
                break;
            case 4:
                setContentView(R.layout.activity_child);
                View childView = findViewById(R.id.main);
                inflateAndFindViews(childView);
                break;
            case 5:
                setContentView(R.layout.activity_cobbler);
                View cobblerView = findViewById(R.id.main);
                inflateAndFindViews(cobblerView);
                break;
            case 6:
                setContentView(R.layout.activity_cow);
                View cowView = findViewById(R.id.main);
                inflateAndFindViews(cowView);
                break;
            case 7:
                setContentView(R.layout.activity_playji);
                View playjiView = findViewById(R.id.main);
                inflateAndFindViews(playjiView);
                break;
            case 8:
                setContentView(R.layout.activity_pauseji);
                View pausejiView = findViewById(R.id.main);
                inflateAndFindViews(pausejiView);
                break;
            case 9:
                setContentView(R.layout.activity_plank);
                View plankView = findViewById(R.id.main);
                inflateAndFindViews(plankView);
                break;
            case 10:
                setContentView(R.layout.activity_crunches);
                View crunchesView = findViewById(R.id.main);
                inflateAndFindViews(crunchesView);
                break;
            case 11:
                setContentView(R.layout.activity_situp);
                View situpView = findViewById(R.id.main);
                inflateAndFindViews(situpView);
                break;
            case 12:
                setContentView(R.layout.activity_rotation);
                View rotationView = findViewById(R.id.main);
                inflateAndFindViews(rotationView);
                break;
            case 13:
                setContentView(R.layout.activity_twist);
                View twistView = findViewById(R.id.main);
                inflateAndFindViews(twistView);
                break;
            case 14:
                setContentView(R.layout.activity_windmill);
                View windmillView = findViewById(R.id.main);
                inflateAndFindViews(windmillView);
                break;
            case 15:
                setContentView(R.layout.activity_legup);
                View legupView = findViewById(R.id.main);
                inflateAndFindViews(legupView);
                break;
        }
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MTimeRunning){
                    stopTimer();
                }else {
                    startTimer();
                }
            }
        });
    }

    private void startTimer(){
        final CharSequence value = mtextView.getText();
        String num1 = value.toString();
        String num2 = num1.substring(0,2);
        String num3 = num1.substring(3,5);

        final int number = Integer.valueOf(num2) * 60 + Integer.valueOf(num3);
        MTimeLeftInMillis = number * 1000;

        countDownTimer = new CountDownTimer(MTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                MTimeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                int newValue = Integer.valueOf(buttonValue) + 1;
                if(newValue <= 7){
                    Intent intent = new Intent(ThirdActivity.this, ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", String.valueOf(newValue));
                    startActivity(intent);
                }
                else{
                    newValue = 1;
                    Intent intent = new Intent(ThirdActivity.this, ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", String.valueOf(newValue));
                    startActivity(intent);
                }
            }
        }.start();
        startBtn.setText("Pause");
        MTimeRunning = true;
    }


    private void updateTimer(){
        int minutes = (int) MTimeLeftInMillis/60000;
        int seconds = (int) MTimeLeftInMillis%60000 / 1000;

        String timeLeftText = "";
        if(minutes<10){
            timeLeftText="0";
        }
        timeLeftText = timeLeftText+minutes+":";
        if(seconds<10){
            timeLeftText += "0";
        }
        timeLeftText += seconds;
        mtextView.setText(timeLeftText);
    }



    private void stopTimer(){
        countDownTimer.cancel();
        MTimeRunning = false;
        startBtn.setText("START");
    }

    private void inflateAndFindViews(View rootView) {
        startBtn = rootView.findViewById(R.id.startbutton);
        mtextView = rootView.findViewById(R.id.time);
    }
}