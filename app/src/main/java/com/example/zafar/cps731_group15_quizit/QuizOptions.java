package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

public class QuizOptions extends AppCompatActivity {
    private String[] dummyArray = {"Math", "Science", "Geography", "Movies", "MISC"};
    private boolean timerChoice = false;
    private Button logoutbtn;
    private Button playbtn;
    private String categoryChoice = "";
    private CheckBox timer;
    Spinner dropdown;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_options);

        timer = (CheckBox) findViewById(R.id.timerOption);

        dropdown = findViewById(R.id.categorySpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dummyArray);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //use postion value

                switch (position)

                {

                    case 0:

                        categoryChoice=dummyArray[0];

                        break;

                    case 1:

                        categoryChoice = dummyArray[1];

                        break;

                    case 2:

                        categoryChoice = dummyArray[2];

                        break;

                    case 3:

                        categoryChoice = dummyArray[3];

                        break;

                    case 4:

                        categoryChoice = dummyArray[4];

                        break;



                }

            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });






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
                    intent.putExtra("category",categoryChoice);

                    startActivity(intent);
                }else{
                    Intent intent = new Intent(QuizOptions.this, Play.class);
                    intent.putExtra("timerChoice", false);
                    intent.putExtra("category",categoryChoice);
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
