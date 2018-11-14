package com.example.zafar.cps731_group15_quizit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class Play extends AppCompatActivity {
    //QuizOptions qz= new QuizOptions();
    private TextView timer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        timer=(TextView)findViewById(R.id.timerTrue);
        Boolean timerChoice= getIntent().getExtras().getBoolean("timerChoice");
        if (timerChoice) {
            timer.setText("Timer is true");
        }else{
            timer.setText("Timer is false");
        }
    }
}
