package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    //layout variables
    private Button playbtn;
    private Button cqbtn;
    private Button reportbtn;
    private Button logoutbtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //sets layout variables to layout items from xml
        playbtn=(Button)findViewById(R.id.playbtn);
        cqbtn=(Button)findViewById(R.id.cqbtn);
        reportbtn=(Button)findViewById(R.id.reportbtn);
        logoutbtn=(Button)findViewById(R.id.logoutbtn);

        //onclick listener for play button
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to QuizOptions
                Intent intent= new Intent(Game.this, QuizOptions.class);
                startActivity(intent);

            }
        });


        //onclick listener for report button
        reportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to Report
                Intent intent= new Intent(Game.this, Report.class);
                startActivity(intent);

            }
        });

        //onclick listener for custom quizzes button
        cqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to CustomQuiz
                Intent intent= new Intent(Game.this, CustomQuiz.class);
                startActivity(intent);

            }
        });

        //onclick listener for logout button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to Login
                Intent intent= new Intent(Game.this, Login.class);
                startActivity(intent);

            }
        });
    }

}
