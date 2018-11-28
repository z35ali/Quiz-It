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


public class Play extends AppCompatActivity {

    //Layout variables
    private TextView timer;
    public static TextView question;
    public static TextView category;
    public static TextView difficulty;
    public static TextView pointsTV;
    private TextView scoreTV;
    private TextView answerET;
    private Button logoutbtn;
    private Button submit;
    private Button hintsSubmit;
    private TextView hintsTV;

    int quizCount=0;
    int questionCount=0;
    private int score=0;
    private int points=0;
    long millis;
    boolean timerChoice;
    String categoryChoice;
    String difficultyChoice;

    FetchData fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

   fetch= new FetchData();
        fetch.fetchQuizzes();

        //sets Layout variables to layout items
        logoutbtn = (Button) findViewById(R.id.logoutbtn);
        timer=(TextView)findViewById(R.id.timeTV);
        category=(TextView)findViewById(R.id.categoryTV);
        question=(TextView)findViewById(R.id.questionTV);
        difficulty=(TextView)findViewById(R.id.difficultyTV);
        answerET = (TextView)findViewById(R.id.answerET);

        scoreTV=(TextView)findViewById(R.id.scoreTV);
        scoreTV.setText("Score: "+score);

        pointsTV=(TextView)findViewById(R.id.pointsTV);
        submit=(Button) findViewById(R.id.submitAnswerBtn);
        hintsSubmit= (Button)findViewById(R.id.hintBtn);
        hintsTV=(TextView)findViewById(R.id.hintTV);
        pointsTV.setText("Points: "+points);
        //Gets the variables passed from the QuizOptions activity
        timerChoice= getIntent().getExtras().getBoolean("timerChoice");
       categoryChoice= getIntent().getExtras().getString("category");
        difficultyChoice= getIntent().getExtras().getString("difficulty");

        //Checker that the variables were passed
       // category.setText("Your Category is: " + categoryChoice);




        //onclick listener for logout button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to Login
                Intent intent= new Intent(Play.this, Login.class);
                startActivity(intent);

            }
        });



        //onclick listener for submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((fetch.quizzes.get(quizCount).answers).equals((answerET.getText().toString().toLowerCase()))){
                    quizCount++;
                    score++;
                    points=score;

                }


            }
        });

        //onclick listener for hints button
        hintsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintsTV.setText(fetch.quizzes.get(quizCount).hints.get(questionCount));


            }
        });
        startGame();
    }



    public void startGame(){

        question.setText(fetch.quizzes.get(quizCount).category);
        /*while(questionCount<fetch.quizzes.get(quizCount).questions.size()){
            question.setText(fetch.quizzes.get(quizCount).questions.get(questionCount));
            difficulty.setText(fetch.quizzes.get(quizCount).difficulty);
            category.setText(fetch.quizzes.get(quizCount).category);
            scoreTV.setText(score);
            pointsTV.setText(points);

            if (timerChoice) {
                millis = 30000;
                startTimer(millis, 1000);
            }

            if(millis==0.0){
                millis = 30000;
                score=score-1;
                points=score;
                questionCount++;
            }




        }*/
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


