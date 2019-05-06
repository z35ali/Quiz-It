package com.zafar.quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionActivity extends AppCompatActivity {
    EditText addQuestion_question;
    EditText addQuestion_op1;
    EditText addQuestion_op2;
    EditText addQuestion_op3;
    EditText addQuestion_ansnr;
    EditText addQuestion_diff;
    EditText addQuestion_ID;

    TextView allCategories;

    List<Category> categoryList = new ArrayList<>();


    Button submitButton;
    Question question;
    
    String difficulty;
    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        addQuestion_question = (EditText)findViewById(R.id.addQuestion_question);
        addQuestion_op1 = (EditText)findViewById(R.id.addQuestion_op1);
        addQuestion_op2 = (EditText)findViewById(R.id.addQuestion_op2);
        addQuestion_op3 = (EditText)findViewById(R.id.addQuestion_op3);
        addQuestion_ansnr = (EditText)findViewById(R.id.addQuestion_ansnr);
        addQuestion_diff = (EditText)findViewById(R.id.addQuestion_diff);
        addQuestion_ID = (EditText)findViewById(R.id.addQuestion_ID);
        submitButton = (Button)findViewById(R.id.button_submitAdd);
        allCategories = (TextView)findViewById(R.id.allCategoryID);

        allCategories.setMovementMethod(new ScrollingMovementMethod());

        categoryList = QuizDbHelper.getInstance(this).getAllCategories();

        allCategories.setText("Categories in order from 1 and up are:\n" + categoryList.toString());


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(allFieldsFilled()){

                    String difficultyFirstLetter = addQuestion_diff.getText().toString().trim().toUpperCase().charAt(0)+"";

                    String difficultyRest = addQuestion_diff.getText().toString().toLowerCase().trim().substring(1,addQuestion_diff.getText().toString().trim().length());
                    difficulty = difficultyFirstLetter + difficultyRest;
                    question = new Question(addQuestion_question.getText().toString(),addQuestion_op1.getText().toString(),addQuestion_op2.getText().toString(),addQuestion_op3.getText().toString(),Integer.parseInt(addQuestion_ansnr.getText().toString()),difficulty,Integer.parseInt(addQuestion_ID.getText().toString()));

                    Add_Question(question);
                }

            }
        });



    }

    private boolean allFieldsFilled(){

        if(addQuestion_ID.getText().toString().equals("") || addQuestion_diff.getText().toString().equals("") || addQuestion_ansnr.getText().toString().equals("") || addQuestion_question.getText().toString().equals("") || addQuestion_op1.getText().toString().equals("") || addQuestion_op2.getText().toString().equals("") || addQuestion_op3.getText().toString().equals("")){
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }

    }

    private void Add_Question(Question question) {
        boolean invalidAnswernr = false;
        boolean invalidDiff = false;
        boolean invalidCategoryID = false;


        if (Integer.parseInt(addQuestion_ansnr.getText().toString())>3 || Integer.parseInt(addQuestion_ansnr.getText().toString())<1){
            invalidAnswernr = true;
        }

        if (!((difficulty.equals(Question.DIFFICULTY_EASY)) || (difficulty.equals(Question.DIFFICULTY_MEDIUM)) || (difficulty.equals(Question.DIFFICULTY_HARD)))){
            invalidDiff = true;
        }

        if (Integer.parseInt(addQuestion_ID.getText().toString())>QuizDbHelper.getInstance(this).getAllCategories().size() || Integer.parseInt(addQuestion_ID.getText().toString())==0){
            invalidCategoryID = true;
        }



        if (invalidAnswernr) {
            Toast.makeText(this, "Invalid Answer Number", Toast.LENGTH_SHORT).show();
        }else

        if (invalidDiff) {
            Toast.makeText(this, "Invalid Difficulty", Toast.LENGTH_SHORT).show();
        }else

        if (invalidCategoryID) {
            Toast.makeText(this, "Invalid Category ID", Toast.LENGTH_SHORT).show();
        }



        else {

            Toast.makeText(this, "Question Added", Toast.LENGTH_SHORT).show();
            QuizDbHelper.getInstance(this).addQuestion((question));
            Intent addIntent = new Intent(AddQuestionActivity.this, AddQuestionActivity.class);
            finish();
            startActivity(addIntent);


        }
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            LoginActivity.mTextPassword.setText("");
            LoginActivity.mTextUsername.setText("");
            LoginActivity.mTextPassword.clearFocus();
            LoginActivity.mTextUsername.clearFocus();
            finish();
        }else{
            Toast.makeText(this, "Press Back Again To Go Back to Login", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }
}
