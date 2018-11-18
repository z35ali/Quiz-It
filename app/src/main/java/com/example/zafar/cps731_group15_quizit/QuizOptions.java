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

    //category array that will be later replaced by SQL fetch
    private String[] dummyArray = {"Math", "Science", "Geography", "Movies", "MISC"};

   //timer selection
    private boolean timerChoice = false;

    //Layout items
    private Button logoutbtn;
    private Button playbtn;
    private CheckBox timer;
    Spinner dropdown;

    //chosen category from spinner gets saved in this variable
    private String categoryChoice = "";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_options);


        //Sets layout items to variables
        timer = (CheckBox) findViewById(R.id.timerOption);
        logoutbtn = (Button) findViewById(R.id.logoutbtn);
        playbtn = (Button) findViewById(R.id.playbtn);

        //onclick for logout button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to Login page
                Intent intent = new Intent(QuizOptions.this, Login.class);
                startActivity(intent);

            }
        });

        //onclick for play button
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (timerChoice) {

                    //Changes the activity from current to Play page and passes the category and timer true
                    Intent intent = new Intent(QuizOptions.this, Play.class);
                    intent.putExtra("timerChoice", true);
                    intent.putExtra("category",categoryChoice);
                    startActivity(intent);


                }else{

                    //Changes the activity from current to Play page and passes the category and timer false
                    Intent intent = new Intent(QuizOptions.this, Play.class);
                    intent.putExtra("timerChoice", false);
                    intent.putExtra("category",categoryChoice);
                    startActivity(intent);
                }


            }
        });

        //onclick for timer selection checkbox
        timer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == timer) {
                    timerChoice = true;
                }else{
                    timerChoice=false;



                }
            }



        });

        //sets spinner to the array
        dropdown = findViewById(R.id.categorySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dummyArray);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            //Called when a spinner item is selected and stores the selection in categoryChoice
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


        }
    }
