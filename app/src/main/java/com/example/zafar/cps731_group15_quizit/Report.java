package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Report extends AppCompatActivity {


    public static ArrayList<String> reportName= new ArrayList<>();
    public static ArrayList<String> reportDetails= new ArrayList<>();

    private Button logoutbtn;
    private Button submitbtn;
    public static EditText name;
    public static  EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        logoutbtn = (Button) findViewById(R.id.logoutbtn);
        submitbtn=(Button)(findViewById(R.id.button_submit));
        name= (EditText)findViewById(R.id.reportName);
        description= (EditText)findViewById(R.id.description);
        //onclick listener for logout button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to Login
                Intent intent= new Intent(Report.this, Login.class);
                startActivity(intent);

            }
        });



        //onclick listener for submit button
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportName.add(name.getText().toString());
                reportDetails.add(description.getText().toString());


            }
        });
    }
}
