package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    private Button playbtn;
    private Button cqbtn;
    private Button reportbtn;
    private Button purchasebtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        playbtn=(Button)findViewById(R.id.playbtn);
        cqbtn=(Button)findViewById(R.id.cqbtn);
        reportbtn=(Button)findViewById(R.id.reportbtn);
       



        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Game.this, Play.class);
                startActivity(intent);

            }
        });


        reportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Game.this, Report.class);
                startActivity(intent);

            }
        });
        cqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Game.this, CustomQuiz.class);
                startActivity(intent);

            }
        });
    }

}
