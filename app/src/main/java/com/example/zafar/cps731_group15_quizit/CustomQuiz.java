package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONObject;

import java.util.ArrayList;

public class CustomQuiz extends AppCompatActivity {

    //category array that will be later replaced by SQL fetch

    public ArrayList<Quiz> customQuizzes;
    private String[] dummyArray = {"Math", "Science", "Geography"};

    private Button logoutbtn;
    private Button button_submit;
    private Spinner dropdown;
    private EditText question1;
    private EditText answer1;
    private EditText question2;
    private EditText answer2;
    private EditText question3;
    private EditText answer3;

    Play play= new Play();
   // FetchData fetchData = new FetchData();

    //chosen category from spinner gets saved in this variable
    public String categoryChoice = "";
    private String difficultyChoice="";




    //public ArrayList<Quiz> quizzes= new ArrayList<Quiz>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_quiz);


        logoutbtn = (Button) findViewById(R.id.logoutbtn);
        button_submit = (Button) findViewById(R.id.button_submit);
       question1 = (EditText) findViewById(R.id.questionET);
        question2 = (EditText) findViewById(R.id.questionET2);
        question3 = (EditText) findViewById(R.id.questionET3);
        answer1 = (EditText) findViewById(R.id.answerET);
        answer2 = (EditText) findViewById(R.id.answerET2);
        answer3= (EditText) findViewById(R.id.answerET3);


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


        //onclick listener for submit button
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             Quiz q = new Quiz("Custom", "Custom", categoryChoice, "Custom");

                q.addQuestion(question1.getText().toString(), "", answer1.getText().toString());
                q.addQuestion(question2.getText().toString(), "", answer2.getText().toString());
                q.addQuestion(question3.getText().toString(), "", answer3.getText().toString());
               Play.customQuizzes.add(q);





            }
        });
    }

}
