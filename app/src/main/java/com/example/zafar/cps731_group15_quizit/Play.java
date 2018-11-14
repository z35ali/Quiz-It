package com.example.zafar.cps731_group15_quizit;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class Play extends AppCompatActivity {
    //QuizOptions qz= new QuizOptions();
    private TextView timer;
    private TextView category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        timer=(TextView)findViewById(R.id.timeTV);
        category=(TextView)findViewById(R.id.categoryTV);
        Boolean timerChoice= getIntent().getExtras().getBoolean("timerChoice");
        String categoryChoice= getIntent().getExtras().getString("category");
        category.setText(categoryChoice);


        if (timerChoice) {
            timer.setText("Timer is true");
        }else{
            timer.setText("Timer is false");
        }
    }
}
