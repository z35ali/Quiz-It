package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Game extends AppCompatActivity {

    //layout variables
    private Button playbtn;
    private Button cqbtn;
    private Button reportbtn;
    private Button logoutbtn;
    private String username;
    private String password;

    private TextView usernameText;
    private TextView pointsText;
    private int points=0;

    Login login = new Login();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //sets layout variables to layout items from xml
        playbtn=(Button)findViewById(R.id.playbtn);
        cqbtn=(Button)findViewById(R.id.cqbtn);
        reportbtn=(Button)findViewById(R.id.reportbtn);
        logoutbtn=(Button)findViewById(R.id.logoutbtn);
        usernameText=(TextView)findViewById(R.id.usernameTV) ;
        pointsText= (TextView)findViewById(R.id.pointsTV);


       username=(getIntent().getExtras().getString("username"));
        points=(getIntent().getExtras().getInt("points"));
        password=(getIntent().getExtras().getString("password"));

        pointsText.setText(""+points);
        usernameText.setText(username);
        //onclick listener for play button
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to QuizOptions
                Intent intent= new Intent(Game.this, QuizOptions.class);
                intent.putExtra("username", username);
                intent.putExtra("password",password);
                intent.putExtra("points",points);
                startActivity(intent);

            }
        });


        //onclick listener for report button
        reportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (login.youAdmin) {
                    //changes activity from current to Report
                    Intent intent = new Intent(Game.this, ReportAdmin.class);
                    startActivity(intent);

                } else {


                    Intent intent = new Intent(Game.this, Report.class);
                    startActivity(intent);
                }
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
