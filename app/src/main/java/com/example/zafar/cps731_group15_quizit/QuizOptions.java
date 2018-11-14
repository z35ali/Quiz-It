package com.example.zafar.cps731_group15_quizit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class QuizOptions extends AppCompatActivity {
    private String[] categories = {"Math","Science","Fun Facts"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_options);
        Spinner dropdown = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        dropdown.setAdapter(adapter);



    }
}
