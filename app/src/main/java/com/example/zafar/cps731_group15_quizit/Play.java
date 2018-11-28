package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.Toast;

import java.util.ArrayList;


public class Play extends AppCompatActivity {

    //Layout variables
    private TextView timer;
    public TextView question;
    public static TextView category;
    public TextView difficulty;
    public TextView pointsTV;
    public TextView hintsTV;
    private TextView scoreTV;
    private Button logoutbtn;

    private int score=0;
    private int points=0;
    long millis;

    public boolean timerChoice;
    public String categoryChoice;
  public  String difficultyChoice;

  public static ArrayList <Integer> block= new ArrayList <Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        fetchData process= new fetchData();
        process.execute();



        //sets Layout variables to layout items
        logoutbtn = (Button) findViewById(R.id.logoutbtn);
        timer=(TextView)findViewById(R.id.timeTV);
        category=(TextView)findViewById(R.id.categoryTV);
        question=(TextView)findViewById(R.id.questionTV);
        difficulty=(TextView)findViewById(R.id.difficultyTV);
        hintsTV=(TextView)findViewById(R.id.hintTV);

        scoreTV=(TextView)findViewById(R.id.scoreTV);
        scoreTV.setText("Score: "+score);

        pointsTV=(TextView)findViewById(R.id.pointsTV);
        pointsTV.setText("Points: "+points);
        //Gets the variables passed from the QuizOptions activity
         timerChoice= getIntent().getExtras().getBoolean("timerChoice");
         categoryChoice= getIntent().getExtras().getString("category");
        difficultyChoice= getIntent().getExtras().getString("difficulty");
       process.setBlock(categoryChoice, difficultyChoice);

        //Checker that the variables were passed
       question.setText(""+process.block.get(0));
        if (timerChoice) {
            millis = 30000;
            startTimer(millis, 1000);
        }else{

        }

     //   difficulty.setText("Your Difficulty is: "+ process.block.get(0));


        //onclick listener for logout button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to Login
                Intent intent= new Intent(Play.this, Login.class);
                startActivity(intent);

            }
        });
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


