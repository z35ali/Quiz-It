package com.zafar.quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        categoryList = QuizDbHelper.getInstance(this).getAllCategories();

        allCategories.setText("Categories in order from 1 and up are:\n" + categoryList.toString());


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(AddQuestionActivity.this,AddQuestionActivity.class);
                question = new Question(addQuestion_question.getText().toString(),addQuestion_op1.getText().toString(),addQuestion_op2.getText().toString(),addQuestion_op3.getText().toString(),Integer.parseInt(addQuestion_ansnr.getText().toString()),addQuestion_diff.getText().toString(),Integer.parseInt(addQuestion_ID.getText().toString()));


                Add_Question(question);



                finish();
                startActivity(addIntent);
            }
        });



    }

    private void Add_Question(Question question){
        QuizDbHelper.getInstance(this).addQuestion((question));


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
