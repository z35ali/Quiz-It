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

public class AddCategoryActivity extends AppCompatActivity {


    TextView addCategory;
    Button submitButton;
    Category category;
    private long backPressedTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        addCategory = (EditText)findViewById(R.id.addCategory);
        submitButton = (Button)findViewById(R.id.button_submitAdd);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                category = new Category(addCategory.getText().toString().trim());
                addCategory(category);

            }
        });



    }

    private void addCategory(Category category){
        boolean categoryExists = false;
        List<Category> categoryList = new ArrayList<>();
        categoryList = QuizDbHelper.getInstance(this).getAllCategories();
        for (Category currentCategory: categoryList) {
            if(currentCategory.getName().equals(category.getName())){
               categoryExists = true;
            }
        }

        if(categoryExists){
            Toast.makeText(this, "Current Category Already Exists", Toast.LENGTH_SHORT).show();
        }else {
            QuizDbHelper.getInstance(this).addCategory((category));
            Toast.makeText(this, "Category Added", Toast.LENGTH_SHORT).show();
            Intent addIntent = new Intent(AddCategoryActivity.this,AddCategoryActivity.class);
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
