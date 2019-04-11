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
    String categoryFormatted;
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

                if(allFieldsFilled()) {
                    String categoryFirstLetter = addCategory.getText().toString().trim().toUpperCase().charAt(0)+"";

                    String categoryRest = addCategory.getText().toString().toLowerCase().trim().substring(1,addCategory.getText().toString().trim().length());
                    categoryFormatted = categoryFirstLetter + categoryRest;
                    category = new Category(categoryFormatted);

                    addCategory(category);
                }

            }
        });



    }

    private boolean allFieldsFilled(){

        if(addCategory.getText().toString().equals("")){
            Toast.makeText(this, "Please Fill The Category Field", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }

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
