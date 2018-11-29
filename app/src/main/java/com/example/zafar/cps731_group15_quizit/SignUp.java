package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SignUp extends AppCompatActivity {

    //Button and Textbox variables
    private EditText signUser1;
    private EditText signPass1;
    private EditText signPass2;
    private Button btnSignup1;
    public boolean dataSent=false;
    Login login = new Login();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //sets layout item to variable
        signUser1 = (EditText) findViewById(R.id.signuser);
        signPass1 = (EditText) findViewById(R.id.signpass1);
        signPass2 = (EditText) findViewById(R.id.signpass2);
        btnSignup1 = (Button)findViewById(R.id.btnsignup);


        //onclick listener for signup submit button
       btnSignup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        //calls validate method to check if two password fields are the same
        validate(signUser1.getText().toString(),signPass1.getText().toString(),signPass2.getText().toString());

            }
        });

    }


    private void validate(String username, String pass1, String pass2){

        //checks if passwords provided are the same
        if(pass1.equals(pass2)){
        dataSent=true;
            Intent intent = new Intent(SignUp.this, Login.class);
            intent.putExtra("username1", username);
            intent.putExtra("password1",pass1);
           intent.putExtra("dataSent",true);
            startActivity(intent);





        }

    }
    }

