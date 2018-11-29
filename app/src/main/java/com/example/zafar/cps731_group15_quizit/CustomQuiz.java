package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.json.JSONObject;

import java.util.ArrayList;

public class CustomQuiz extends AppCompatActivity {

    //category array that will be later replaced by SQL fetch

    public ArrayList<Quiz> customQuizzes;
    private String[] dummyArray = {"Math", "Science", "Geography"};

    private Button logoutbtn;
    private Spinner dropdown;
    private Spinner difficulty;
    //chosen category from spinner gets saved in this variable
    private String categoryChoice = "";
    private String difficultyChoice="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_quiz);


        logoutbtn = (Button) findViewById(R.id.logoutbtn);

        //sets spinner to the array
        dropdown = findViewById(R.id.categorySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dummyArray);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
/*
createQuiz () {
    Quiz q = new Quiz();
    q.questions.add("Question 1");
    q.answers.add("Answer 1");
    customQuizzes.add(q);
    FetchData fetchData = new FetchData();
    customQuizzes = fetchData.customQuizzes;

}
    */       /* public void createJSON() {
                Quiz quizObject = new Quiz();

                JSONObject quiz = new JSONObject();
                quiz
            } */

            //Called when a spinner item is selected and stores the selection in categoryChoice
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //use position value

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




        //onclick listener for logout button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to Login
                Intent intent= new Intent(CustomQuiz.this, Login.class);
                startActivity(intent);

            }
        });
    }

}
