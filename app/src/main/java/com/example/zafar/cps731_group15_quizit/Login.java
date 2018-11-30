package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


public class Login extends AppCompatActivity {

    //layout variables
    private EditText name;
    private EditText password;

    private Button login;
    private Button signup;
    private Button btnskip;
    private boolean userFound=false;
    private boolean passFound=false;
   public  SignUp sign;
   public int index;
   boolean loggedOut=false;
    //variable to keep track of login attempts
    private int count=5;
    public static boolean youAdmin;

        public static ArrayList <String> username = new ArrayList<>();
        public static ArrayList <String> pass= new ArrayList<>();
        public static ArrayList <Integer> points= new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sign= new SignUp();
        //sets layout variables to layout items in xml
        name=(EditText)findViewById(R.id.etName);
        password=(EditText)findViewById(R.id.etPassword);

        login = (Button)findViewById(R.id.btnLogin);

        signup= (Button)findViewById(R.id.btnsignup);
        btnskip=(Button)findViewById(R.id.btnskip);


        youAdmin=false;







                try {
                  loggedOut=(getIntent().getExtras().getBoolean("loggedOut"));

                    //callback to update points
                if(loggedOut){

                    String playuser=(getIntent().getExtras().getString("usernameFromPlay"));
                    int pointsuser=(getIntent().getExtras().getInt("pointsFromPlay"));
                    String playpass=(getIntent().getExtras().getString("passwordFromPlay"));

                    for(String user: username) {
                        if (playuser.equals(user)) {

                            index=username.indexOf(user);
                        }
                    }


                    points.add(index,pointsuser);
                    pass.add(index,playpass);
                    username.add(index,playuser);



                }


        }catch ( Exception e) {
                    username.add("admin");
                    pass.add("1234");
                    points.add(99999);
        }


        try {

            //Sign up variables
            if (getIntent().getExtras().getBoolean("dataSent")) {

                username.add(getIntent().getExtras().getString("username1"));
                pass.add(getIntent().getExtras().getString("password1"));
                if(!loggedOut){
                    points.add(0);
                }


            }
        }catch ( NullPointerException e){

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

                intent.putExtra("username", "Unknown User");

                intent.putExtra("points",0);
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


        for(String user: username) {
            if (userName.equals(user)) {
                userFound = true;
                if(user.equals("admin")){
                    youAdmin=true;
                }

                index=username.indexOf(user);

            }
        }

        for(String pass1: pass) {
            if (userPassword.equals(pass1)) {
                passFound = true;

            }
        }

        if(userFound&&passFound) {
            Intent intent = new Intent(Login.this, Game.class);


            if(!userName.equals("")) {
                intent.putExtra("username", username.get(index));
                intent.putExtra("points", points.get(index));
                intent.putExtra("password", pass.get(index));
                startActivity(intent);
            }

        }
        else{

            //decrease number of attemps remaining
            count--;
            Toast.makeText(Login.this, "Number of Attempts Left: "+String.valueOf(count), Toast.LENGTH_SHORT).show();


            //if too many attempts used then disable login
            if(count==0){
                login.setEnabled(false);
            }
        }

    }
}
