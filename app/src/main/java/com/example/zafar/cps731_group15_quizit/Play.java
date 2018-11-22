package com.example.zafar.cps731_group15_quizit;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.Toast;


public class Play extends AppCompatActivity {

    //Layout variables
    private TextView timer;
    private TextView category;
    private TextView difficulty;
    long millis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //sets Layout variables to layout items
        timer=(TextView)findViewById(R.id.timeTV);
        category=(TextView)findViewById(R.id.categoryTV);
        difficulty=(TextView)findViewById(R.id.difficultyTV);
        //Gets the variables passed from the QuizOptions activity
        Boolean timerChoice= getIntent().getExtras().getBoolean("timerChoice");
        String categoryChoice= getIntent().getExtras().getString("category");
        String difficultyChoice= getIntent().getExtras().getString("difficulty");

        //Checker that the variables were passed
        category.setText("Your Category is: " + categoryChoice);
        if (timerChoice) {
            millis = 30000;
            startTimer(millis, 1000);
        }else{

        }

        difficulty.setText("Your Difficulty is: "+ difficultyChoice);

    }

    private void startTimer(final long finish, long tick){
    CountDownTimer t;
    t = new CountDownTimer(finish, tick) {


        public void onTick(long millisUntilFinished) {
            long remainedSecs = millisUntilFinished / 1000;
            timer.setText("Time Left: " + (remainedSecs / 60) + ":" + (remainedSecs % 60));// manage it according to you
        }

        public void onFinish() {
            timer.setText("00:00:00");
            Toast.makeText(Play.this, "Finish", Toast.LENGTH_SHORT).show();

            cancel();
        }
    }.start();
}
}


