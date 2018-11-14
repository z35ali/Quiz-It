package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

public class QuizOptions extends AppCompatActivity {
    private String[] dummyArray = {"Math", "Science", "Geography", "Movies", "MISC"};
    public boolean timerChoice = false;
    private Button logoutbtn;
    private Button playbtn;
    public String categoryChoice = "";
    private CheckBox timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_options);
        Spinner dropdown = findViewById(R.id.spinner1);
        timer = (CheckBox) findViewById(R.id.timerOption);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dummyArray);
        dropdown.setAdapter(adapter);
        categoryChoice = dropdown.getSelectedItem().toString();


        logoutbtn = (Button) findViewById(R.id.logoutbtn);
        playbtn = (Button) findViewById(R.id.playbtn);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizOptions.this, Login.class);
                startActivity(intent);

            }
        });

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerChoice) {
                    Intent intent = new Intent(QuizOptions.this, Play.class);
                    intent.putExtra("timerChoice", true);

                    startActivity(intent);
                }else{
                    Intent intent = new Intent(QuizOptions.this, Play.class);
                    intent.putExtra("timerChoice", false);
                    startActivity(intent);
                }


            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == timer) {
                    timerChoice = true;
                }else{
                    timerChoice=false;



                }
            }



        });


    }
}
