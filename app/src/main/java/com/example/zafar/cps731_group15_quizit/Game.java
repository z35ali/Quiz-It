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
    private TextView howtoTV;

    private Button howToPlay;
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
        howToPlay=(Button)findViewById(R.id.howtobtn);
        howtoTV= (TextView)findViewById(R.id.howtoTV);

       username=(getIntent().getExtras().getString("username"));
        points=(getIntent().getExtras().getInt("points"));
        password=(getIntent().getExtras().getString("password"));

        pointsText.setText("Points: "+points+"  ");
        usernameText.setText("Username: "+username+"  ");
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


        //onclick listener for how to play button
        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (howtoTV.getText().equals("Welcome!\n" +
                        "\n" +
                        "Players can choose from 3 catogories: Math, Science, Geography.\n" +
                        "\n" +
                        "Each category can be played on difficulty Easy, Medium, Hard or Custom.\n" +
                        "\n" +
                        "For every correct answer provided, the player will gain 5 points and 1 score.\n" +
                        "\n" +
                        "Players can unlock hints by using up 5 of their points.\n" +
                        "\n" +
                        "If the player has selected the timer option, then the player will have 30 seconds to answer the question. Otherwise, it is counted as incorrect.\n" +
                        "\n" +
                        "Our Custom difficulty allows players to play the quizzes that have been created by other players. (You can create your own custom quiz through the main page.)\n" +
                        "\n" +
                        "Enjoy Our Game!")) {
                    howtoTV.setText("");
                } else {
                    howtoTV.setText("Welcome!\n" +
                            "\n" +
                            "Players can choose from 3 catogories: Math, Science, Geography.\n" +
                            "\n" +
                            "Each category can be played on difficulty Easy, Medium, Hard or Custom.\n" +
                            "\n" +
                            "For every correct answer provided, the player will gain 5 points and 1 score.\n" +
                            "\n" +
                            "Players can unlock hints by using up 5 of their points.\n" +
                            "\n" +
                            "If the player has selected the timer option, then the player will have 30 seconds to answer the question. Otherwise, it is counted as incorrect.\n" +
                            "\n" +
                            "Our Custom difficulty allows players to play the quizzes that have been created by other players. (You can create your own custom quiz through the main page.)\n" +
                            "\n" +
                            "Enjoy Our Game!");

                }
            }
        });
    }



}
