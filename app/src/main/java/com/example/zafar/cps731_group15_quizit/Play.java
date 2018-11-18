package com.example.zafar.cps731_group15_quizit;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class Play extends AppCompatActivity {

    //Layout variables
    private TextView timer;
    private TextView category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //sets Layout variables to layout items
        timer=(TextView)findViewById(R.id.timeTV);
        category=(TextView)findViewById(R.id.categoryTV);

        //Gets the variables passed from the QuizOptions activity
        Boolean timerChoice= getIntent().getExtras().getBoolean("timerChoice");
        String categoryChoice= getIntent().getExtras().getString("category");

        //Checker that the variables were passed
        category.setText(categoryChoice);
        if (timerChoice) {
            timer.setText("Timer is true");
        }else{
            timer.setText("Timer is false");
        }
    }
}
