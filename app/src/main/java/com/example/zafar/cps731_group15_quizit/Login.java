package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


public class Login extends AppCompatActivity {

    //layout variables
    private EditText name;
    private EditText password;
    private TextView info;
    private Button login;
    private Button signup;
    private Button btnskip;
    private boolean userFound=false;
    private boolean passFound=false;
   public  SignUp sign;
    //variable to keep track of login attempts
    private int count=5;

        public ArrayList <String> username = new ArrayList<>();
        public ArrayList <String> pass= new ArrayList<>();
        public ArrayList <Integer> points= new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sign= new SignUp();
        //sets layout variables to layout items in xml
        name=(EditText)findViewById(R.id.etName);
        password=(EditText)findViewById(R.id.etPassword);
        info=(TextView)findViewById(R.id.tvInfo);
        login = (Button)findViewById(R.id.btnLogin);
        info.setText("No of attempts remaining: 5");
        signup= (Button)findViewById(R.id.btnsignup);
        btnskip=(Button)findViewById(R.id.btnskip);
        username.add("admin");
        pass.add("1234");
        points.add(9999);


        try {
            if (getIntent().getExtras().getBoolean("dataSent")) {
                username.add(getIntent().getExtras().getString("username1"));
                pass.add(getIntent().getExtras().getString("password1"));
                points.add(0);
            }
        }catch ( NullPointerException e) {

            }
        //onclick listener for login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validates username and password
                validate(name.getText().toString(),password.getText().toString());

            }
        });

        //onclick listener to skip login button
        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to Game activity
                Intent intent= new Intent(Login.this, Game.class);
                startActivity(intent);


            }
        });

        //onclick listener for signup button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to SignUp activity
                Intent intent= new Intent(Login.this, SignUp.class);
                startActivity(intent);

            }
        });

    }


    private void validate(String userName, String userPassword){

        //checks if admin credentials are correct
        for(String user: username) {
            if (userName.equals(user)) {
                userFound = true;
            }
        }

        for(String pass1: pass) {
            if (userPassword.equals(pass1)) {
                passFound = true;
            }
        }

        if(userFound&&passFound) {
            Intent intent = new Intent(Login.this, Game.class);
            startActivity(intent);
        }
        else{

            //decrease number of attemps remaining
            count--;
            info.setText("No of attempts remaining:" + String.valueOf(count));

            //if too many attempts used then disable login
            if(count==0){
                login.setEnabled(false);
            }
        }

    }
}
